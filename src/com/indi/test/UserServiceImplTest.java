package com.indi.test;

import com.indi.pojo.User;
import com.indi.service.UserService;
import com.indi.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "bbj666", "666666", "bbj@168.com",0));
        userService.registUser(new User(null, "abc666", "666666", "bbj@168.com",0));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "bbj666", "666666", null,0)));
    }

    @Test
    public void existUsername() {
        if (userService.existUsername("bbj6666") ) {
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }
}