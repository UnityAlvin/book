package com.indi.service;

import com.indi.pojo.Cart;
import com.indi.pojo.Order;
import com.indi.pojo.OrderItem;
import com.indi.pojo.Page;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);

    public List<Order> myOrders(Integer userId);

    public List<OrderItem> orderDetails(String orderId);

    public List<Order> allOrders();

    public int sendOrder(String orderId);

    public int receiveOrder(String orderId);

    Page<Order> pageByMyOrder(int userId, int pageNo, int pageSize);

    Page<Order> pageByAllOrder(int pageNo, int pageSize);
}
