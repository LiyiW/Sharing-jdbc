package com.xbz.xdemo.shardingjdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xbz.xdemo.shardingjdbc.entity.User;
import com.xbz.xdemo.shardingjdbc.service.UserService;

/**
 * @title 用户(User)表控制层
 * @author Xingbz
 * @createDate 2018-11-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/selectSelective")
    public List<User> selectSelective(User record) {
        // 因为没有做主从复制，所以slave里面没数据
        return userService.selectSelectiveMaster(record);
    }

    @PostMapping("/insertUser")
    public int insertUser(User record) {
        return userService.insertSelective(record);
    }

}