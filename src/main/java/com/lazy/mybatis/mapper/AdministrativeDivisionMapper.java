package com.lazy.mybatis.mapper;

import com.lazy.mybatis.entity.AdministrativeDivision;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministrativeDivisionMapper {
    public void insert(List<AdministrativeDivision> list);

}
