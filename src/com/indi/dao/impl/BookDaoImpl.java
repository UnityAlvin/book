package com.indi.dao.impl;

import com.indi.dao.BookDao;
import com.indi.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name` , `price` ,  `author` ,`sales` , `stock` , " +
                "`imgPath`) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "DELETE FROM t_book WHERE id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "UPDATE t_book SET name = ?,price = ?,author = ?,sales = ?,stock = ?,imgPath = ? where id = ?";
        return update(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(),
                book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "SELECT id,name,price,author,sales,stock,imgPath FROM t_book WHERE id = ?";
        return queryForOne(Book.class, sql, id);

    }

    @Override
    public List<Book> queryBooks() {
        String sql = "SELECT id,name,price,author,sales,stock,imgPath FROM t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        return ((Number) queryForSingleValue(sql)).intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select id,name,price,author,sales,stock,imgPath from t_book limit ?,?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        return ((Number) queryForSingleValue(sql, min, max)).intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select id,name,price,author,sales,stock,imgPath from t_book " +
                "where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }
}
