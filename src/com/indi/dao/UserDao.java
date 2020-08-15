package com.indi.dao;

import com.indi.pojo.User;

public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 返回null，说明没有这个用户
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return
     */
    public User queryUserByUserameAndPassword(String username,String password);


    /**
     * 保存用户信息
     * @param user
     * @return 返回-1表示操作失败
     */
    public int saveUser(User user);


}
