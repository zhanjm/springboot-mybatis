package com.lazy.mybatis.entity.amap.place.polygon;

import lombok.Data;

@Data
public class PolygonRequest {
    private String key;//请求服务权限标识
    private String polygon;//经纬度坐标对
    private String keywords;//查询关键字
    private String types;//POI类型
    private String page;//第几页数据
    private String offset;//返回个数
    private String extensions;//返回结果
    private String sig;//数字签名
    private String callback;
    private String output;
}
