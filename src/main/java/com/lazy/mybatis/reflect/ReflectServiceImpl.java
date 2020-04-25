package com.lazy.mybatis.reflect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectServiceImpl {
    private String name;


    public ReflectServiceImpl(String name){
        this.name = name;
    }

    public void sayHello(String name){
        log.info("Hello "+name);
    }

    public void getName(){
        log.info("我的名字叫"+this.name);
    }
}
