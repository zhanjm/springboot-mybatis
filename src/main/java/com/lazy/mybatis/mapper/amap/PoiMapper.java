package com.lazy.mybatis.mapper.amap;

import com.lazy.mybatis.entity.amap.common.Poi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoiMapper {

    public void insert(List<Poi> pois);
}
