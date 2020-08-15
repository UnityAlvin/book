package com.indi.test;

import com.indi.pojo.Cart;
import com.indi.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {
    Cart cart = new Cart();

    {
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到星门", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "java从入门到月门", 1, new BigDecimal(100), new BigDecimal(100)));
    }

    @Test
    public void addItem() {
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        cart.updateCount(1,20);
        System.out.println(cart);
    }
}