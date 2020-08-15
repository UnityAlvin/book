package com.indi.service.impl;

import com.indi.dao.BookDao;
import com.indi.dao.impl.BookDaoImpl;
import com.indi.pojo.Book;
import com.indi.pojo.Page;
import com.indi.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();
    int begin;


    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public int deleteBookById(Integer id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();

        Page<Book> page = setPageArgs(new Page<>(),pageTotalCount,pageNo,pageSize);

        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        //设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);

        Page<Book> page = setPageArgs(new Page<>(),pageTotalCount,pageNo,pageSize);

        //求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize,min,max);
        //设置当前页数据
        page.setItems(items);
        return page;
    }

    public Page<Book> setPageArgs(Page<Book> page,Integer pageTotalCount,int pageNo, int pageSize){
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
