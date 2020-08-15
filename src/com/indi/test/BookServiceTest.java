package com.indi.test;

import com.indi.pojo.Book;
import com.indi.pojo.Page;
import com.indi.service.BookService;
import com.indi.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {
    BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "未命名书籍", new BigDecimal(9999),"无名氏",  156165, 165632, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22, "人人都能当产品经理", new BigDecimal(9999), "人人", 156165, 165632, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        bookService.queryBooks().forEach(System.out::println);
    }

    @Test
    public void Page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
    @Test
    public void PageByPrice() {
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,10,50));
    }
}