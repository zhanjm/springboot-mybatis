package com.lazy.mybatis.entity.amap.district;

import com.lazy.mybatis.entity.amap.common.BaseBean;
import com.lazy.mybatis.entity.amap.common.District;
import com.lazy.mybatis.entity.amap.common.Suggestion;
import lombok.Data;

import java.util.List;

@Data
public class DistrictResponse extends BaseBean {

    private Suggestion suggestion;
    private List<District> districts;

}
