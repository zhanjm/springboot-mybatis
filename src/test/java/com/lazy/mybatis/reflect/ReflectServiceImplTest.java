package com.lazy.mybatis.reflect;


import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectServiceImplTest {

    @Test
    public void getInstance()  {

        ReflectServiceImpl reflect = null;
        try {
            //reflect = (ReflectServiceImpl)Class.forName("com.lazy.mybatis.reflect.ReflectServiceImpl").newInstance();

            reflect = (ReflectServiceImpl)Class.forName("com.lazy.mybatis.reflect.ReflectServiceImpl").getConstructor(String.class).newInstance("张三");
            //reflect.getName();
            //Class.forName("com.lazy.mybatis.reflect.ReflectServiceImpl").getm
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        reflect.sayHello("占建明");
        reflect.getName();

        try {
            Method sayHello = ReflectServiceImpl.class.getMethod("sayHello", String.class);
            sayHello.invoke(reflect,"李四");

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
