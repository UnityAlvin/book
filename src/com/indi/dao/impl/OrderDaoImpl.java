package com.indi.dao.impl;

import com.indi.dao.OrderDao;
import com.indi.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order (orderId,createTime,price,status,userId) VALUES(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(),
                order.getStatus(), order.getUserId());
    }

    @Override
    public List<Order> queryMyOrders(Integer userId) {
        String sql = "select orderId,createTime,price,status,userId from t_order where userId = ?";
        return queryForList(Order.class, sql, userId);
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql = "select orderId,createTime,price,status,userId from t_order";
        return queryForList(Order.class, sql);
    }

    @Override
    public int changeOrderStatus(Integer status, String orderId) {
        String sql = "update t_order set status = ? where orderId = ?";
        return update(sql, status, orderId);
    }

    @Override
    public Integer queryForMyPageTotalCount(int userId) {
        String sql = "select count(*) from t_order where userId = ?";
        return ((Number) queryForSingleValue(sql,userId)).intValue();
    }

    @Override
    public List<Order> queryForMyPageItems(int userId,int begin, int pageSize) {
        String sql = "select orderId,createTime,price,status,userId from t_order where userId = ? order by createTime desc limit ?,?";
        return queryForList(Order.class, sql,userId, begin, pageSize);
    }

    @Override
    public Integer queryForAllPageTotalCount() {
        String sql = "select count(*) from t_order";
        return ((Number) queryForSingleValue(sql)).intValue();
    }

    @Override
    public List<Order> queryForAllPageItems(int begin, int pageSize) {
        String sql = "select orderId,createTime,price,status,userId,username\n" +
                "from t_order as t\n" +
                "INNER JOIN t_user AS u\n" +
                "ON t.userId = u.id\n" +
                "order by createTime desc limit ?,?";
        return queryForList(Order.class, sql, begin, pageSize);
    }
}
