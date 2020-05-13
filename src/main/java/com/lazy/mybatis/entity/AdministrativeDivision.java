package com.lazy.mybatis.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AdministrativeDivision {
    private String id;
    private String name;
    private String pid;
    private String level;
    private String url;
    private Timestamp createTime;
    private Timestamp updateTime;
}
