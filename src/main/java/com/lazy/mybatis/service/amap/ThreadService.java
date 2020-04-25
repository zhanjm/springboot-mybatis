package com.lazy.mybatis.service.amap;

import com.lazy.mybatis.entity.amap.Point;
import com.lazy.mybatis.entity.amap.Rectangle;
import com.lazy.mybatis.entity.amap.common.District;
import com.lazy.mybatis.entity.amap.common.Poi;
import com.lazy.mybatis.entity.amap.district.DistrictRequest;
import com.lazy.mybatis.entity.amap.district.DistrictResponse;
import com.lazy.mybatis.entity.amap.place.polygon.PolygonRequest;
import com.lazy.mybatis.entity.amap.place.polygon.PolygonResponse;
import com.lazy.mybatis.mapper.amap.PoiMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ThreadService {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private PolygonService polygonService;
    @Autowired
    private PoiMapper poiMapper;

    public void polygon(String keyword){
        double x = 0.1;
        final double y = 0.2;
        DistrictRequest districtRequest = new DistrictRequest();
        districtRequest.setKeywords(keyword);
        districtRequest.setExtensions("all");//返回边界
        districtRequest.setSubdistrict("0");//不返回下级行政区
        DistrictResponse districtResponse = districtService.districtResponse(districtRequest);
        List<District> districts = districtResponse.getDistricts();
        District district = districts.get(0);
        String polyline = district.getPolyline();
        Map<String, Double> result = getMinAndMaxLngLat(polyline);
        Point maxPoint = new Point(result.get("maxLng"),result.get("maxLat"));
        Point minPoint = new Point(result.get("minLng"),result.get("minLat"));
        BigDecimal jdc = maxPoint.getLng().subtract(minPoint.getLng());//经度差
        int jdfg = (int)Math.ceil(jdc.doubleValue() / x);//经度分割
        //log.info("======================切割行"+jdfg);
        int jdcell = 0;
        BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(200);// 创建数组型缓冲等待队列
        // ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为3，允许最大的线程数为6
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(15, 20, 50, TimeUnit.MILLISECONDS, bq);
        while (jdcell < jdfg){
            BigDecimal min = BigDecimal.valueOf(jdcell).multiply(BigDecimal.valueOf(x));
            BigDecimal max = BigDecimal.valueOf(jdcell+1).multiply(BigDecimal.valueOf(x));
            //最小经度
            BigDecimal minLng = minPoint.getLng().add(min);
            //最大经度
            BigDecimal maxLng = minPoint.getLng().add(max);
            //纬度不变，经度变
            final Point xPoint = new Point(minLng,minPoint.getLat());
            final Point yPoint = new Point(maxLng,maxPoint.getLat());
            final int threadNum = jdcell+1;
            tpe.execute(new Runnable() {
                @Override
                public void run() {
                    BigDecimal wdc = yPoint.getLat().subtract(xPoint.getLat());//纬度差
                    int wdfg = (int)Math.ceil(wdc.doubleValue()/y);//纬度分割
                    //log.info("======================切割列"+wdfg);
                    int wdrow = 0;
                    while(wdrow < wdfg){
                        long startTime = System.currentTimeMillis();    //获取开始时间
                        BigDecimal min1 = BigDecimal.valueOf(wdrow).multiply(BigDecimal.valueOf(y));
                        BigDecimal max1 = BigDecimal.valueOf(wdrow+1).multiply(BigDecimal.valueOf(y));
                        //最小纬度
                        BigDecimal minlat = xPoint.getLat().add(min1);
                        //最大纬度
                        BigDecimal maxLat = xPoint.getLat().add(max1);

                        //纬度变，经度不变
                        Point wPoint = new Point(xPoint.getLng(),minlat);
                        Point zPoint = new Point(yPoint.getLng(),maxLat);
                        savePoi(new Rectangle(wPoint,zPoint));
                        ++wdrow;
                        long endTime = System.currentTimeMillis();    //获取结束时间
                        //log.info("======================切割第"+wdrow+"列耗时"+(endTime-startTime)+"====线程"+Thread.currentThread().getName());
                    }
                    log.info("=============结束第"+String.valueOf(threadNum)+"条线程======================>");
                }
            });
            jdcell++;
        }
        //log.info("==========================================================>"+bq.size());
    }


    public void savePoi(Rectangle rectangle){
        int curPagrow = 1;
        int pagenum = 0;
        int offset = 25;
        int count=0;

        while(true){
            PolygonRequest polygonRequest= new PolygonRequest();
            polygonRequest.setPolygon(rectangle.toString());
            polygonRequest.setTypes("190108");
            polygonRequest.setOffset(String.valueOf(offset));
            polygonRequest.setPage(String.valueOf(curPagrow));
            polygonRequest.setPage(String.valueOf(curPagrow));
            polygonRequest.setExtensions("all");
            PolygonResponse polygonResponse = polygonService.polygonResponse(polygonRequest);
            List<Poi> pois = polygonResponse.getPois();
            if(pois!=null&& pois.size()>0){
                poiMapper.insert(pois);
            }
            if(count == 0){
                count = Integer.parseInt(polygonResponse.getCount());
                if(count >= 900){
                    log.error("===============采集到的POI数为==================="+count+"=====超出范围，把范围分小店====================="+rectangle.toString());
                }
            }
            pagenum= count / offset + (count % offset > 0 ? 1 : 0);
            if(count==0||pagenum==0||curPagrow>=pagenum)break;
            curPagrow++;
        }
    }

    /**
     * 解析边界坐标返回最值经纬度
     * @param polyline
     * @return
     */
    private Map<String,Double> getMinAndMaxLngLat(String polyline){
        String[] lnglats = polyline.replace("|",";").split(";");
        String[] jwzb =  lnglats[0].split(",");
        double minLng = Double.parseDouble(jwzb[0]);//
        double maxLng = Double.parseDouble(jwzb[0]);
        double minLat = Double.parseDouble(jwzb[1]);//
        double maxLat = Double.parseDouble(jwzb[1]);
        for (String lnglat : lnglats) {
            String[] jwd = lnglat.split(",");
            double jd = Double.parseDouble(jwd[0]);
            double wd = Double.parseDouble(jwd[1]);
            minLng = jd>minLng?minLng:jd;
            maxLng = jd>maxLng?jd:maxLng;
            minLat = wd>minLat?minLat:wd;
            maxLat = wd>maxLat?wd:maxLat;
        }
        Map<String,Double> result = new HashMap<>();
        result.put("minLng",minLng);
        result.put("maxLng",maxLng);
        result.put("minLat",minLat);
        result.put("maxLat",maxLat);
        return result;
    }
}
