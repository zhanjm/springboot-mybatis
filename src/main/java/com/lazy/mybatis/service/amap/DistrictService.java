package com.lazy.mybatis.service.amap;

import com.alibaba.fastjson.JSON;
import com.lazy.mybatis.entity.amap.district.DistrictRequest;
import com.lazy.mybatis.entity.amap.district.DistrictResponse;
import com.lazy.mybatis.util.AmapContant;
import com.lazy.mybatis.util.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DistrictService {

    public DistrictResponse districtResponse(DistrictRequest request){
        String url = AmapContant.DISTRICT_URL+requestArgs(request);
        String httpResult = HttpUtil.httpGet(url);
        DistrictResponse districtResponse = null;
        if("XML".equals(request.getOutput())){

        }else{
            districtResponse = jsonStringToBean(httpResult);
        }
        return districtResponse;
    }

    private String requestArgs(DistrictRequest request){
        StringBuilder  requestArgs = new StringBuilder();


        String key = StringUtils.isEmpty(request.getKey())?AmapContant.KEY:request.getKey();
        requestArgs.append("?key="+key);
        if(!StringUtils.isEmpty(request.getKeywords())){
            requestArgs.append("&keywords="+request.getKeywords());
        }
        if(!StringUtils.isEmpty(request.getSubdistrict())){
            requestArgs.append("&subdistrict="+request.getSubdistrict());
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
        if(!StringUtils.isEmpty(request.getFilter())){
            requestArgs.append("&filter="+request.getFilter());
        }
        if(!StringUtils.isEmpty(request.getCallback())){
            requestArgs.append("&callback="+request.getCallback());
        }
        if(!StringUtils.isEmpty(request.getOutput())){
            requestArgs.append("&output="+request.getOutput());
        }

        return requestArgs.toString();

    }

    private DistrictResponse jsonStringToBean(String jsonString){
        return  JSON.parseObject(jsonString,DistrictResponse.class);
    }
}
