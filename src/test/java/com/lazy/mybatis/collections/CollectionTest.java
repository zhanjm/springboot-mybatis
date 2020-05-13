package com.lazy.mybatis.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CollectionTest {

    @Test
    public void test(){
        /*Set<String> set = new HashSet<>();
        set.add(null);
        set.forEach(s->{
            log.info(s);
        });

        User user1 = new User();
        User user2 = new User();
        user1.setUsername("zjm");
        user2.setUsername("zjm");
        Set<User> userSet = new HashSet<>();
        userSet.add(user1);
        userSet.add(user2);
        userSet.forEach(s->{
            log.info(s.toString());
        });

        HashMap<User, User> userHashMap = new HashMap<>();
        userHashMap.put(user1,user1);
        userHashMap.put(user2,user2);
        userHashMap.forEach((k,v)->{
            log.info(k.toString()+"  "+v.toString());
        });
        ArrayList<String> list = new ArrayList<>();
        list.add("123");
        list.remove(0);
        //log.info(set.);*/

        Map<String,String> map = new HashMap<>();
        map.put("tt","tt");
        map.put("aa","tt");
        map.put("bb","tt");
        map.put("cc","tt");
        map.forEach((k,v)->{
            log.info(k+v);
        });

    }
}
