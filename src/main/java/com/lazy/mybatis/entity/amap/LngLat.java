package com.lazy.mybatis.entity.amap;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LngLat {
    private BigDecimal maxLng;
    private BigDecimal minLng;
    private BigDecimal maxLat;
    private BigDecimal minLat;


    public void setMaxAndMinLngLat(String polyline){
        String[] lnglats = polyline.replace("|",";").split(";");
        String[] jwzb =  lnglats[0].split(",");
        double minLng = Double.parseDouble(jwzb[0]);//
        double maxLng = Double.parseDouble(jwzb[0]);
        double minLat = Double.parseDouble(jwzb[1]);//
        double maxLat = Double.parseDouble(jwzb[1]);
        for (String lnglat : lnglats) {
            String[] jwd = lnglat.split(",");
            double jd = Double.parseDouble(jwd[0]);
            double wd = Double.parseDouble(jwd[1]);
            minLng = jd>minLng?minLng:jd;
            maxLng = jd>maxLng?jd:maxLng;
            minLat = wd>minLat?minLat:wd;
            maxLat = wd>maxLat?wd:maxLat;
        }
        //System.out.println(maxLng+","+maxLat+"|"+minLng+","+minLat);
        this.maxLng = new BigDecimal(maxLng);
        this.minLng = new BigDecimal(minLng);
        this.maxLat = new BigDecimal(maxLat);
        this.minLat = new BigDecimal(minLat);
    }



}
