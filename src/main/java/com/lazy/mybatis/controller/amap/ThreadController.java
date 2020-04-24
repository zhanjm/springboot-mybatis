package com.lazy.mybatis.controller.amap;

import com.lazy.mybatis.service.amap.ThreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用多线程采集数据")
public class ThreadController {

    @Autowired
    private ThreadService threadService;


    @RequestMapping(value = "/thread",method = RequestMethod.GET)
    @ApiOperation(value="根据行政区划关键字获取边界坐标采集poi数据", notes="根据行政区划关键字获取边界坐标采集poi数据")
    public void districtResponse(String keyword){
        threadService.polygon(keyword);
    }
}
