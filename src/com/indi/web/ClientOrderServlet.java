package com.indi.web;

import com.indi.pojo.*;
import com.indi.service.OrderService;
import com.indi.service.impl.OrderServiceImpl;
import com.indi.utils.JDBCUtils;
import com.indi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClientOrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        //判断用户是否登录
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = user.getId();
        //返回订单编号给前台
        String orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        //判断用户是否登录
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        //1.获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Order> page = orderService.pageByMyOrder(user.getId(), pageNo, pageSize);
        page.setUrl("client/orderServlet?action=page");
        //3.保存Page对象到Request域中
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    protected void orderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        int status = WebUtils.parseInt(req.getParameter("status"), 0);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        List<OrderItem> orderItems = orderService.orderDetails(orderId);
        req.setAttribute("orderItems", orderItems);
        req.setAttribute("status", status);
        req.setAttribute("orderId", orderId);
        req.setAttribute("pageNo", pageNo);
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);
    }

    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.receiveOrder(orderId);
        resp.sendRedirect(req.getContextPath() + "/client/orderServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

}
