package com.lazy.mybatis.reflect;



import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class JdkProxyExample implements InvocationHandler {

    Object target;

    public Object bing(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("执行动态代理逻辑方法");
        Object obj = method.invoke(target, args);
        log.info("执行代理方法之后");
        return obj;
    }
}
