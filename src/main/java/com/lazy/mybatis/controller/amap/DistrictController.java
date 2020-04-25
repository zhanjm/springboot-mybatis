package com.lazy.mybatis.controller.amap;

import com.lazy.mybatis.entity.amap.district.DistrictRequest;
import com.lazy.mybatis.entity.amap.district.DistrictResponse;
import com.lazy.mybatis.service.amap.DistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "行政区划代码相关接口")
public class DistrictController {

    @Autowired
    private DistrictService districtService;


    @RequestMapping(value = "/districtRequest",method = RequestMethod.GET)
    @ApiOperation(value="获取行政区划信息", notes="根据请求参数获取行政区划信息")
    public DistrictResponse districtResponse(DistrictRequest districtRequest){
        return districtService.districtResponse(districtRequest);
    }
}
