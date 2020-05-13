package com.lazy.mybatis.service;

import com.lazy.mybatis.common.AddressConstants;
import com.lazy.mybatis.common.Constants;
import com.lazy.mybatis.entity.AdministrativeDivision;
import com.lazy.mybatis.entity.ProvinceURL;
import com.lazy.mybatis.mapper.AdministrativeDivisionMapper;
import com.lazy.mybatis.mapper.ProvinceURLMapper;
import com.lazy.mybatis.strategy.address.AddressStrategyFactory;
import com.lazy.mybatis.strategy.address.IAddressStrategy;
import com.lazy.mybatis.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DataCollectionService {
    @Autowired
    private ProvinceURLMapper provinceURLMapper;

    @Autowired
    private AdministrativeDivisionMapper administrativeDivisionMapper;




    public String getData(){
        //test(Constants.INDEX_URL);
       // String str = HttpUtil.httpGet(Constants.INDEX_URL,"gbk");
        //HttpResponse httpResponse = HttpUtil.httpGetHttpResponse(NationalStatisticsUtil.GOV_URL);
        //Document doc = Jsoup.parse(str);
        //Elements elements = doc.select("a");
        //elements.forEach(el->{
            //String tail = el.attr("href");
            //log.info(tail);
        //});

        //log.info(str);
        //getProvincesURL();
        //insert();
        bb(Constants.INDEX_URL,"0",0);
        //bb("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/13/09/130983.html","130983000000",0);
        //bb("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/13/09/130984.html","130984000000",0);
        return null;//str;
    }


    public void bb(String url,String pid,int n){
        String str = HttpUtil.httpGet(url,"gbk");
        Document doc = Jsoup.parse(str);
        IAddressStrategy strategy = AddressStrategyFactory.getStrategy(doc);
        if(strategy==null){
            if(n>=10){
                throw new IllegalArgumentException("超出范围");
            }
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            //if(Integer.parseInt(pid)>Integer.parseInt("130982000000"))
            bb(url, pid,++n);
        }else{
            strategy.analysisDoc(doc, pid,url.substring(0,url.lastIndexOf("/")+1));
            List<AdministrativeDivision> addressList = strategy.getAddressList();
            log.info(pid);
            administrativeDivisionMapper.insert(addressList);
            //Random random = new Random();
            //int i = (random.nextInt(10)+1) * 1000;
            /*try {
                //Thread.sleep(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            if(addressList!=null&&addressList.size()>0 && !AddressConstants.VILLAGE_LV.equals(addressList.get(0).getLevel())){
                addressList.forEach(address-> {
                    if (StringUtils.isNotEmpty(address.getUrl())
                            &&!"110000000000".equals(address.getId())
                            &&!"120000000000".equals(address.getId())
                            &&!"130000000000".equals(address.getId()))
                        bb(address.getUrl(), address.getId(), 0);
                });
            }
        }



    }


    public void getProvincesURL(){
        String provinceDoc = HttpUtil.httpGet(Constants.INDEX_URL, "gbk");
        Document doc = Jsoup.parse(provinceDoc);
        Elements elements = doc.select(".provincetr");
        List<ProvinceURL> list = new ArrayList<>();
        elements.forEach(el->{
            Elements els = el.select("a");
            els.forEach(e->{
                String href = Constants.GOV_URL+"/"+e.attr("href");
                //String id = href.substring(0, href.indexOf("."))+"";
                String province = e.text();
                ProvinceURL provincesURL = new ProvinceURL();
                provincesURL.setUrl(href);
                provincesURL.setProvince(province);
                log.info(provincesURL.toString());
                list.add(provincesURL);
            });
        });
        provinceURLMapper.insert(list);

    }
    public void  test(String url){
        String str = HttpUtil.httpGet(url,"gbk");
        Document doc = Jsoup.parse(str);
        Elements elements = doc.select("a");
        elements.forEach(el->{
            //Elements els = el.select("a");
            //els.forEach(e->{
                String tail = el.attr("href");
                log.info(el.text());
               // String urlstr = Constants.GOV_URL+"/"+tail;
                test(Constants.GOV_URL+"/"+tail);
           // });

        });
    }

    public void insert(){
        List<ProvinceURL> urlList = provinceURLMapper.findAll();
        aa(urlList.get(19).getUrl(),null);
        /*urlList.forEach(url->{
            //String docStr = HttpUtil.httpGet(url.getUrl(), "gbk");
            String urlStr = url.getUrl();
            String pid = urlStr.substring(urlStr.lastIndexOf("/")+1, urlStr.lastIndexOf("."))+"0000000000";
            aa(url.getUrl(),pid);
        });*/
    }

    public void aa(String url,String pid){
        /*List<AdministrativeDivision> list = new ArrayList<>();
        String docStr = HttpUtil.httpGet(url, "gbk");
        Document doc = Jsoup.parse(docStr);
        Elements elements = doc.select("a");
        int row = elements.size() / 2;
        for (int i = 0;i<row;i++){
            String urlstr = url.substring(0,url.lastIndexOf("/")+1)+elements.get(2*i).attr("href");
            //log.info(url.substring(0,url.lastIndexOf("/")+1));
            String id = elements.get(2 * i).text();
            String name = elements.get(2*i+1).text();
            log.info(id+"==>"+name+"==>"+urlstr);
            aa(urlstr,id);
            AdministrativeDivision bean = new AdministrativeDivision();
            bean.setId(id);
            bean.setName(name);
            if(StringUtils.isNotEmpty(pid)){
                bean.setPid(pid);
            }else{
                bean.setPid("0");
            }
            list.add(bean);
        }
        if(list.size()>0)
            administrativeDivisionMapper.insert(list);*/



            List<AdministrativeDivision> list = new ArrayList<>();
            String docStr = HttpUtil.httpGet(url, "gbk");
            Document doc = Jsoup.parse(docStr);
            Elements elements = doc.select("a");
            int row = elements.size() / 2;
            for (int i = 0;i<row;i++){
                String urlstr = url.substring(0,url.lastIndexOf("/")+1)+elements.get(2*i).attr("href");
                //log.info(url.substring(0,url.lastIndexOf("/")+1));
                String id = elements.get(2 * i).text();
                String name = elements.get(2*i+1).text();
                log.info(id+"==>"+name+"==>"+urlstr);

                AdministrativeDivision bean = new AdministrativeDivision();
                bean.setId(id);
                bean.setName(name);
                if(StringUtils.isNotEmpty(pid)){
                    bean.setPid(pid);
                }else{
                    bean.setPid("0");
                }
                list.add(bean);
                aa(urlstr,id);
            }
        if(list.size()>0)
        administrativeDivisionMapper.insert(list);
    }


}
