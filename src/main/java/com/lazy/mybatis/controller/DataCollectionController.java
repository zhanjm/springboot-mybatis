package com.lazy.mybatis.controller;

import com.lazy.mybatis.service.DataCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataCollectionController {

    @Autowired
    private DataCollectionService dataCollectionService;

    @RequestMapping("getData")
    public String getData(){
        return dataCollectionService.getData();
    }
}
