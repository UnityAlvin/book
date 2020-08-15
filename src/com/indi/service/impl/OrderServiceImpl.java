package com.indi.service.impl;

import com.indi.dao.BookDao;
import com.indi.dao.OrderDao;
import com.indi.dao.OrderItemDao;
import com.indi.dao.impl.BookDaoImpl;
import com.indi.dao.impl.OrderDaoImpl;
import com.indi.dao.impl.OrderItemDaoImpl;
import com.indi.pojo.*;
import com.indi.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    private int begin = 0;

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号生成
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId, "");
        orderDao.saveOrder(order);
        //遍历购物车中每一个商品项转化成订单项保存到数据库
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(),
                    cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);

            //重新设置商品的库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }

        //清空购物车
        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> myOrders(Integer userId) {
        return orderDao.queryMyOrders(userId);
    }

    @Override
    public List<OrderItem> orderDetails(String orderId) {
        return orderItemDao.queryOrderDetailByOrderId(orderId);
    }

    @Override
    public List<Order> allOrders() {
        return orderDao.queryAllOrders();
    }

    @Override
    public int sendOrder(String orderId) {
        return orderDao.changeOrderStatus(1, orderId);
    }

    @Override
    public int receiveOrder(String orderId) {
        return orderDao.changeOrderStatus(2, orderId);
    }

    @Override
    public Page<Order> pageByMyOrder(int userId, int pageNo, int pageSize) {
        int pageTotalCount = orderDao.queryForMyPageTotalCount(userId);
        Page<Order> page = setPageArgs(new Page<>(), pageTotalCount, pageNo, pageSize);
        List<Order> items = orderDao.queryForMyPageItems(userId, begin, pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Order> pageByAllOrder(int pageNo, int pageSize) {
        int pageTotalCount = orderDao.queryForAllPageTotalCount();
        Page<Order> page = setPageArgs(new Page<>(), pageTotalCount, pageNo, pageSize);
        List<Order> items = orderDao.queryForAllPageItems(begin, pageSize);
        page.setItems(items);
        return page;
    }

    public Page<Order> setPageArgs(Page<Order> page, Integer pageTotalCount, int pageNo, int pageSize) {
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        //设置总页码
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);

        //求当前页数据的开始索引
        begin = (page.getPageNo() - 1) * pageSize;
        //求当前页数据
        return page;
    }
}
