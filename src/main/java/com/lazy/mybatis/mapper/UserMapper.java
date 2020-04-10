package com.lazy.mybatis.mapper;

import com.lazy.mybatis.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> findAll();

    List<User> findUsers(User user);

}
