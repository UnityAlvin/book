package com.indi.service;

import com.indi.pojo.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return null说明登录失败，否则登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return true表示已存在，false表示可用
     */
    public boolean existUsername(String username);
}
