package com.lazy.mybatis.entity.amap.common;

import lombok.Data;

import java.util.List;

@Data
public class Poi {
    private String id;
    private String parent;
    private String name;
    private String type;
    private String typecode;
    private String biz_type;
    private String address;
    private String location;
    private String distance;
    private String tel;
    private String postcode;
    private String website;
    private String email;
    private String pcode;
    private String pname;
    private String citycode;
    private String cityname;
    private String adcode;
    private String adname;
    private String entr_location;
    private String exit_location;
    private String navi_poiid;
    private String gridcode;
    private String alias;
    private String business_area;
    private String parking_type;
    private String tag ;
    private String indoor_map;
    private IndoorData indoor_data;
    private String groupbuy_num;
    private String discount_area;
    private String discount_num;
    private BizExt biz_ext;
    private List<Photo> photos;




}
