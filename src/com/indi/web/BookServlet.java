package com.indi.web;

import com.indi.pojo.Book;
import com.indi.pojo.Page;
import com.indi.service.BookService;
import com.indi.service.impl.BookServiceImpl;
import com.indi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        //1.获取请求的参数，封装成Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        //2.调用BookService.addBook()保存图书
        bookService.addBook(book);

        //3.跳到图书列表页面
        //当用户提交完请求，浏览器会记录下最后一次请求的全部信息，当用户按F5，就会发起浏览器记录的最后一次请求。
        //所以需要使用重定向
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="
                + pageNo);

    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        Book book = bookService.queryBookById(id);

        req.setAttribute("book", book);

        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);

    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }


    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        bookService.deleteBookById(id);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把数据保存到request域中
        req.setAttribute("books", books);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService.page(pageNo, pageSize): Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3.保存Page对象到Request域中
        req.setAttribute("page", page);
        //4.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
