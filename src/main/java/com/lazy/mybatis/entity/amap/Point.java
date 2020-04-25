package com.lazy.mybatis.entity.amap;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 点坐标
 */
@Data
@AllArgsConstructor
public class Point {
    private BigDecimal lng;//经度
    private BigDecimal lat;//纬度

    public Point(String lng,String lat){
        this.lng = new BigDecimal(lng);
        this.lat = new BigDecimal(lat);
    }
    public Point(double lng,double lat){
        this.lng = BigDecimal.valueOf(lng);
        this.lat = BigDecimal.valueOf(lat);
    }



    @Override
    public String toString() {
        return  lng + "," +lat ;
    }
}
