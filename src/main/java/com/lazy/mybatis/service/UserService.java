package com.lazy.mybatis.service;

import com.lazy.mybatis.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    List<User> findUsers(User user);
}
