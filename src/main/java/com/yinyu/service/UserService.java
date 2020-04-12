package com.yinyu.service;


import com.yinyu.entity.User;
import com.yinyu.mapper.UserMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserService {

    static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserMapper userMapper;


    @GetMapping(path = "/adduser")
    String addUser() {

        try {
            User user = new User();
            user.setName("zhangsan");
            userMapper.addUser(user);
        } catch (DataAccessException sqlException) {
            logger.warn(sqlException.toString());
            return "failed";
        }

        return "success";
    }

    @GetMapping(path = "/queryuser")
    String queryuser() {
        logger.info("queryuser ");

        User user = new User();
        user.setName("zhangsan");
        List<User> userList = userMapper.queryUser(user);

        return userList.toString();
    }

}
