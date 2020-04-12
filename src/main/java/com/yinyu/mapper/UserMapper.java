package com.yinyu.mapper;


import com.yinyu.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {
    List<User> queryUser(User user);
    void addUser(User user);
    void updateUser(User user);
}
