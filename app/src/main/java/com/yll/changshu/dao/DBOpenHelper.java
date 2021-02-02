package com.yll.changshu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBOpenHelper {
    private static String driver = "com.mysql.jdbc.Driver";//MySQL 驱动
//    private static String url = "jdbc:mysql://192.168.0.103:3306/Changshu?serverTimezone=GMT&useUnicode=true&characterEncoding=utf8&zeroDate";//MYSQL数据库连接Url
    private static String url = "jdbc:mysql://139.198.187.71:3306/Changshu?serverTimezone=GMT&useUnicode=true&characterEncoding=utf8&zeroDate";//MYSQL数据库连接Url
    private static String user = "root";//用户名
    private static String password = "zhouroot";//密码
//    private static String password = "123456";//密码

    /**
     * 连接数据库
     * */

    public static Connection getConn(){
        Connection conn = null;
        try {
            Class.forName(driver);//获取MYSQL驱动
            conn = (Connection) DriverManager.getConnection(url, user, password);//获取连接
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库
     * */

    public static void closeAll(Connection conn, PreparedStatement ps){
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
     * 关闭数据库
     * */

    public static void closeAll(Connection conn, Statement sm){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (sm != null) {
            try {
                sm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭数据库
     * */

    public static void closeAll(Connection conn, Statement sm, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (sm != null) {
            try {
                sm.close();
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
         * 关闭数据库
         * */

    public static void closeAll(Connection conn, Statement sm, PreparedStatement ps, ResultSet rs){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (sm != null) {
            try {
                sm.close();
            } catch (SQLException e){
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

}
