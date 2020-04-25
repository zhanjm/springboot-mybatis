package com.lazy.mybatis.reflect;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloworldImpl implements Helloworld {


    @Override
    public void sayHello(String name) {
        log.info("你好吗？ "+name);
    }
}