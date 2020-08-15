package com.indi.dao.impl;

import com.indi.dao.UserDao;
import com.indi.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select id,username,password,email,type from t_user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUserameAndPassword(String username, String password) {
        String sql = "select id,username,password,email,type from t_user where username = ?" +
                "and password = ?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email,type) values(?,?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getType());
    }
}
