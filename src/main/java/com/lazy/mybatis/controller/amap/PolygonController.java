package com.lazy.mybatis.controller.amap;

import com.lazy.mybatis.entity.amap.place.polygon.PolygonRequest;
import com.lazy.mybatis.entity.amap.place.polygon.PolygonResponse;
import com.lazy.mybatis.service.amap.PolygonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "多边行搜索相关接口")
public class PolygonController {
    @Autowired
    private PolygonService polygonService;

    @RequestMapping(value = "/polygonRequest",method = RequestMethod.GET)
    @ApiOperation(value="获取行政区划信息", notes="根据请求参数获取行政区划信息")
    public PolygonResponse districtResponse(PolygonRequest polygonRequest){
        return polygonService.polygonResponse(polygonRequest);
    }
}
