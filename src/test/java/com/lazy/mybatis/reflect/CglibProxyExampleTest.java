package com.lazy.mybatis.reflect;

import org.junit.jupiter.api.Test;

public class CglibProxyExampleTest {

    @Test
    public void cglib(){

        CglibProxyExample cglibProxyExample = new CglibProxyExample();
        HelloworldImpl proxy = (HelloworldImpl)cglibProxyExample.getProxy(HelloworldImpl.class);
        proxy.sayHello("张三");

    }
}
