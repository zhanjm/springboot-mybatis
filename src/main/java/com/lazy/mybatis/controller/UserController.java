package com.lazy.mybatis.controller;


import com.lazy.mybatis.entity.User;
import com.lazy.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("findall")
    @ResponseBody
    public List<User> findAll(){
        return  userService.findAll();
    }

    @RequestMapping("finduser")
    @ResponseBody
    public List<User> findUser(){
        User user = new User();
        user.setUsername("test");
        user.setRealName("test");
        user.setPassword("test");

        return  userService.findUsers(user);
    }
    @RequestMapping("index")
    public String  index(Model model){
        User user = new User();
        user.setUsername("test");
        user.setRealName("test");
        user.setPassword("test");
        model.addAttribute("user",user);
        return "index";
    }
}
