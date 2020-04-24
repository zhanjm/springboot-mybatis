package com.lazy.mybatis.entity.amap.common;

import lombok.Data;

import java.util.List;

@Data
public class District  {

    private String citycode;
    private String adcode;
    private String name;
    private String polyline;
    private String center;
    private String level;
    private List<District> districts;
}
