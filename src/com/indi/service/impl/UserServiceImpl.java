package com.indi.service.impl;

import com.indi.dao.UserDao;
import com.indi.dao.impl.UserDaoImpl;
import com.indi.pojo.User;
import com.indi.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
        public User login(User user) {
        return userDao.queryUserByUserameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if (userDao.queryUserByUsername(username) == null) {
            return false;
        }else {
            return true;
        }
    }
}
