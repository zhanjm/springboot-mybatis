package com.lazy.mybatis.entity.amap.place;

import com.lazy.mybatis.entity.amap.common.Address;
import com.lazy.mybatis.entity.amap.common.BaseBean;
import com.lazy.mybatis.entity.amap.common.Poi;
import com.lazy.mybatis.entity.amap.common.Suggestion;
import lombok.Data;

import java.util.List;
@Data
public class PlaceResponse extends BaseBean {
    private Suggestion suggestion;
    private Address sug_address;
    private List<Poi> pois;
}
