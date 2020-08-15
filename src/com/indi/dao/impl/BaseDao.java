package com.indi.dao.impl;

import com.indi.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public abstract class BaseDao {

    private QueryRunner queryRunner = new QueryRunner();


    /**
     * 增删改
     *
     * @param sql
     * @param args
     * @return 返回-1表示操作失败
     */
    public int update(String sql, Object... args) {
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一个JavaBean的SQL
     *
     * @param type 返回的对象类型
     * @param sql  执行的SQL语句
     * @param args SQL对应的参数值
     * @param <T>  返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回多个JavaBean的SQL语句
     *
     * @param type 返回的对象类型
     * @param sql  执行的SQL语句
     * @param args SQL对应的参数值
     * @param <T>  返回的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一行一列的SQL
     *
     * @param sql  执行的SQL语句
     * @param args SQL对应的参数值
     * @return
     */
    public Object queryForSingleValue(String sql, Object... args) {
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
