package com.lazy.mybatis.controller;


import com.lazy.mybatis.entity.User;
import com.lazy.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("findall")
    public List<User> findAll(){
        return  userService.findAll();
    }

    @RequestMapping("finduser")
    public List<User> findUser(){
        User user = new User();
        user.setUsername("test");
        user.setRealName("test");
        user.setPassword("test");

        return  userService.findUsers(user);
    }
}
