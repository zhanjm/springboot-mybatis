package com.lazy.mybatis.service.amap;

import com.alibaba.fastjson.JSON;
import com.lazy.mybatis.entity.amap.place.polygon.PolygonRequest;
import com.lazy.mybatis.entity.amap.place.polygon.PolygonResponse;
import com.lazy.mybatis.util.AmapContant;
import com.lazy.mybatis.util.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PolygonService {

    public PolygonResponse polygonResponse(PolygonRequest request){
        String url = AmapContant.POLYGON_URL+requestArgs(request);
        String httpResult = HttpUtil.httpGet(url);
        PolygonResponse polygonResponse = null;
        if("XML".equals(request.getOutput())){

        }else{
            polygonResponse = jsonStringToBean(httpResult);
        }

        return  polygonResponse;
    }

    private String requestArgs(PolygonRequest request){
        StringBuilder  requestArgs = new StringBuilder();
        String key = StringUtils.isEmpty(request.getKey())?AmapContant.KEY:request.getKey();
        requestArgs.append("?key="+key);
        requestArgs.append("&polygon="+request.getPolygon());
        if(!StringUtils.isEmpty(request.getKeywords())){
            requestArgs.append("&keywords="+request.getKeywords());
        }
        if(!StringUtils.isEmpty(request.getTypes())){
            requestArgs.append("&types="+request.getTypes());
        }

        if(!StringUtils.isEmpty(request.getPage())){
            requestArgs.append("&page="+request.getPage());
        }
        if(!StringUtils.isEmpty(request.getOffset())){
            requestArgs.append("&offset="+request.getOffset());
        }
        if(!StringUtils.isEmpty(request.getExtensions())){
            requestArgs.append("&extensions="+request.getExtensions());
        }
        if(!StringUtils.isEmpty(request.getSig())){
            requestArgs.append("&sig="+request.getSig());
        }
        if(!StringUtils.isEmpty(request.getCallback())){
            requestArgs.append("&callback="+request.getCallback());
        }
        if(!StringUtils.isEmpty(request.getOutput())){
            requestArgs.append("&output="+request.getOutput());
        }
        return requestArgs.toString();
    }

    private PolygonResponse jsonStringToBean(String jsonString){
        return  JSON.parseObject(jsonString,PolygonResponse.class);
    }
}
