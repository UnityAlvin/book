package com.indi.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    /**
     * 使用Druid数据库连接池
     */
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    static {
        try {
            Properties pros = new Properties();

            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pros.load(is);

            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = conns.get();

        if (conn == null) {
            try {
                conn = dataSource.getConnection();

                //保存到ThreadLocal对象中，为后面的JDBC使用
                conns.set(conn);

                //设置为手动管理事务
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务并关闭释放连接
     */
    public static void commitAndClose(){
        Connection connection = conns.get();

        //如果不为空，则说明之前使用过
        if (connection != null) {
            try {
                //提交事务
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    //关闭连接，释放资源
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //一定要执行remove操作，否则就会出错（因为Tomcat服务器底层使用了线程池）。
        conns.remove();
    }

    /**
     * 回滚事务并关闭释放连接
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();

        //如果不为空，则说明之前使用过
        if (connection != null) {
            try {
                //回滚事务
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    //关闭连接，释放资源
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //一定要执行remove操作，否则就会出错（因为Tomcat服务器底层使用了线程池）。
        conns.remove();
    }

    /**
     * 增删改资源的关闭
     *
     * @param conn
     * @param ps
     */
    public static void closeResource(Connection conn, PreparedStatement ps) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 查资源的关闭
     *
     * @param conn
     * @param ps
     */
    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭IO流资源
     *
     * @param targets
     */
    public static void closeIO(Closeable... targets) {
        for (Closeable target :
                targets) {
            try {
                if (targets != null) {
                    target.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
