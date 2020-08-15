package com.indi.dao;

import com.indi.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);
    public List<OrderItem> queryOrderDetailByOrderId(String orderId);
}
