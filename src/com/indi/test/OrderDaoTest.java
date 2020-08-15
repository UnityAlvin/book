package com.indi.test;

import com.indi.dao.OrderDao;
import com.indi.dao.impl.OrderDaoImpl;
import com.indi.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {
    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("123456",new Date(),new BigDecimal(100),0,1,""));
    }

    @Test
    public void queryMyOrders() {
        orderDao.queryMyOrders(1).forEach(System.out::println);

    }

    @Test
    public void queryAllOrders() {
        orderDao.queryAllOrders().forEach(System.out::println);
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus(1,"123456");
    }

    @Test
    public void queryForMyPageTotalCount() {
        System.out.println(orderDao.queryForMyPageTotalCount(1));
    }

    @Test
    public void  queryForMyPageItems() {
        orderDao.queryForMyPageItems(1,1,4).forEach(System.out::println);
    }

    @Test
    public void queryForAllPageTotalCount() {
        System.out.println(orderDao.queryForAllPageTotalCount());
    }

    @Test
    public void queryForAllPageItems() {
        orderDao.queryForAllPageItems(1,4).forEach(System.out::println);

    }
}