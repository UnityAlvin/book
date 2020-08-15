package com.indi.test;

import com.indi.pojo.Cart;
import com.indi.pojo.CartItem;
import com.indi.pojo.Order;
import com.indi.service.OrderService;
import com.indi.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {
    OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        {
            cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
            cart.addItem(new CartItem(1, "java从入门到星门", 1, new BigDecimal(1000), new BigDecimal(1000)));
            cart.addItem(new CartItem(2, "java从入门到月门", 1, new BigDecimal(100), new BigDecimal(100)));
        }

        System.out.println(orderService.createOrder(cart,1));
    }

    @Test
    public void orderDetails(){
        orderService.orderDetails("15864459352041").forEach(System.out::println);
    }

    @Test
    public void pageByMyOrder(){
        System.out.println(orderService.pageByMyOrder(1,1,4));
    }

    @Test
    public void pageByAllOrder(){
        System.out.println(orderService.pageByAllOrder(1,4));
    }
}