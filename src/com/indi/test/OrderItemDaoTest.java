package com.indi.test;

import com.indi.dao.OrderDao;
import com.indi.dao.impl.OrderDaoImpl;
import com.indi.dao.impl.OrderItemDaoImpl;
import com.indi.pojo.Order;
import com.indi.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDaoTest {
    private OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null, "java从入门到入土", 1, new BigDecimal(100), new BigDecimal(100), "123456"));
    }

    @Test
    public void queryOrderDetailByOrderId() {
        orderItemDao.queryOrderDetailByOrderId("15864459352041").forEach(System.out::println);
    }

}