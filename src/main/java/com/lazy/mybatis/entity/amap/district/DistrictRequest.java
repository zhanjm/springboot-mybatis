package com.lazy.mybatis.entity.amap.district;

import lombok.Data;

@Data
public class DistrictRequest {
    private String key;//请求服务权限标识
    private String keywords;//查询关键字
    private String subdistrict;//子级行政区
    private String page;//第几页数据
    private String offset;//返回个数
    private String extensions;//返回结果
    private String filter;
    private String callback;
    private String output;
}
