package com.indi.service;

import com.indi.pojo.Book;
import com.indi.pojo.Page;

import java.util.List;

public interface BookService {
    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
