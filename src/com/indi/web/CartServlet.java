package com.indi.web;

import com.google.gson.Gson;
import com.indi.pojo.Book;
import com.indi.pojo.Cart;
import com.indi.pojo.CartItem;
import com.indi.service.BookService;
import com.indi.service.impl.BookServiceImpl;
import com.indi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();


//    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //获取请求的商品Id
//        int id = WebUtils.parseInt(req.getParameter("id"), 0);
//
//        //调用bookService.queryBookById(id)获取图书的信息
//        Book book = bookService.queryBookById(id);
//
//        //把图书信息，转换成CartItem商品项
//        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
//
//        //调用Cart.addItem(CartItem)添加商品项
//        Cart cart = (Cart) req.getSession().getAttribute("cart");
//        if (cart == null) {
//            cart = new Cart();
//            req.getSession().setAttribute("cart", cart);
//        }
//        cart.addItem(cartItem);
//
//        //重定向回到原来商品所在的页面，
//        resp.sendRedirect(req.getHeader("Referer"));
//
//        //最后一个添加的商品名称
//        req.getSession().setAttribute("lastName",cartItem.getName());
//    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的商品Id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //调用bookService.queryBookById(id)获取图书的信息
        Book book = bookService.queryBookById(id);

        //把图书信息，转换成CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        //调用Cart.addItem(CartItem)添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);

        req.getSession().setAttribute("lastName",cartItem.getName());


        //返回购物车总商品数量和最后一个添加的商品名称
        Map<String,Object> resultMap = new HashMap<String, Object>();

        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String resultMapJson = gson.toJson(resultMap);

        resp.getWriter().write(resultMapJson);


    }

        protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"),1);
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }


}
