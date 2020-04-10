package com.lazy.mybatis.service.impl;

import com.lazy.mybatis.entity.User;
import com.lazy.mybatis.mapper.UserMapper;
import com.lazy.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> findUsers(User user) {
        return userMapper.findUsers(user);
    }
}
