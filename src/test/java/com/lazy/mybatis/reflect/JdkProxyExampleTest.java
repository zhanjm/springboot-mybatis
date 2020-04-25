package com.lazy.mybatis.reflect;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JdkProxyExampleTest {
    @Test
    public void testProxy(){
        JdkProxyExample proxy = new JdkProxyExample();
        Helloworld bing = (Helloworld)proxy.bing(new HelloworldImpl());
        bing.sayHello("科比");

    }
}
