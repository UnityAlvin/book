package com.indi.dao.impl;

import com.indi.dao.OrderItemDao;
import com.indi.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item (name,count,price,totalPrice,orderId) VALUES(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(),
                orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    public List<OrderItem> queryOrderDetailByOrderId(String orderId) {
        String sql = "SELECT id,name,count,price,totalPrice,orderId FROM t_order_item WHERE orderId =?";
        return queryForList(OrderItem.class, sql, orderId);
    }
}
