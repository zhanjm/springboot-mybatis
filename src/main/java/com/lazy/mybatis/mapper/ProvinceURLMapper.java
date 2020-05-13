package com.lazy.mybatis.mapper;

import com.lazy.mybatis.entity.ProvinceURL;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProvinceURLMapper {
    public void insert(List<ProvinceURL> provincesURLs);

    public List<ProvinceURL> findAll();
}
