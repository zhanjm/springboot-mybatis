package com.lazy.mybatis.entity.amap.common;

import lombok.Data;

import java.util.List;

@Data
public class Suggestion {
    private String keywords;
    private List<City> cities;
}
