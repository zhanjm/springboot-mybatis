package com.lazy.mybatis.entity;

import lombok.Data;


@Data
public class User {

    private Long id;
    private String username;
    private String password;
    private String realName;
}
