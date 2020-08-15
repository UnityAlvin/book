package com.indi.test;

import com.indi.dao.UserDao;
import com.indi.dao.impl.UserDaoImpl;
import com.indi.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if (userDao.queryUserByUsername("admin") == null) {
            System.out.println("用户名可用");
        } else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUserameAndPassword() {
        if (userDao.queryUserByUserameAndPassword("admin","admin") == null) {
            System.out.println("用户名或密码错误");
        }else {
            System.out.println("查询成功");
        }
    }

    @Test
    public void saveUser() {
        userDao.saveUser(new User(null,"new","new","123@new.com",0));
    }
}