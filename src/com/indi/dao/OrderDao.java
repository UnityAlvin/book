package com.indi.dao;

import com.indi.pojo.Order;

import java.util.List;

public interface OrderDao {
    public int saveOrder(Order order);

    public List<Order> queryMyOrders(Integer userId);

    public List<Order> queryAllOrders();

    public int changeOrderStatus(Integer status, String orderId);

    Integer queryForMyPageTotalCount(int userId);

    List<Order> queryForMyPageItems(int userId, int begin, int pageSize);

    Integer queryForAllPageTotalCount();

    List<Order> queryForAllPageItems(int begin, int pageSize);


}
