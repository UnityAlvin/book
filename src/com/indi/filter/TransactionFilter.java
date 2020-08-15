package com.indi.filter;

import com.indi.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);

            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();
            e.printStackTrace();
            //抛给Tomcat展示统一错误页面
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
