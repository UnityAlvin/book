package com.indi.test;

import com.indi.dao.BookDao;
import com.indi.dao.impl.BookDaoImpl;
import com.indi.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "未命名书籍", new BigDecimal(9999),"无名氏",  156165, 165632, null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "人人都能当产品经理", new BigDecimal(9999), "人人", 156165, 165632, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(10));
    }

    @Test
    public void queryBooks() {
        bookDao.queryBooks().forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        bookDao.queryForPageItems(8,4).forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
    }

    @Test
    public void queryForPageItemsByPrice() {
        bookDao.queryForPageItemsByPrice(1,4,10,50).forEach(System.out::println);
    }
}