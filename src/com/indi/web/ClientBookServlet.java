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

public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用BookService.page(pageNo, pageSize): Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3.保存Page对象到Request域中
        req.setAttribute("page", page);
        //4.请求转发到/pages/client/index.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        //2.调用BookService.page(pageNo, pageSize): Page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");

        //解决第一页之后不带价格区间的分页问题
        //如果有最小价格的参数，追加到分页条的地址请求参数中
        if (req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }

        //如果有最大价格的参数，追加到分页条的地址请求参数中
        if (req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        //3.保存Page对象到Request域中
        req.setAttribute("page", page);
        //4.请求转发到/pages/client/index.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
