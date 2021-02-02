package com.yll.changshu.dao;

import com.yll.changshu.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection conn=null; //打开数据库对象
    private PreparedStatement ps=null;//操作整合sql语句的对象
    private ResultSet rs=null;//查询结果的集合

    //DBService 对象
    public static UserDao userDao = null;

    /**
     * 构造方法 私有化
     * */
    public UserDao(){

    }

    /**
     * 获取MySQL数据库单例类对象
     * */
    public static UserDao getUserDao(){
        if(userDao==null){
            userDao=new UserDao();
        }
        return userDao;
    }

    public List<User> getAllUser(){
        List<User> list = new ArrayList<>();
        //MySQL 语句
        String sql="select * from Changshu.user";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            User u=new User();
                            u.setUserId(rs.getInt("userid"));
                            u.setUser_name(rs.getString("user_name"));
                            u.setPsw(rs.getString("psw"));
                            u.setCompany_name(rs.getString("company_name"));
                            u.setPhone_number(rs.getString("phone_number"));
                            u.setUser_rank(rs.getInt("user_rank"));
                            u.setUser_email(rs.getString("user_email"));
                            list.add(u);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps,rs);//关闭相关操作
        return list;
    }

    public int updateUserPsw(String psw, int userId){
        int result = -1;
        String sql = "update Changshu.user set psw = ? where userid = ?";
        conn = DBOpenHelper.getConn();
        try{
            boolean closed=conn.isClosed();
            if(conn!=null&&(!closed)){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setString(1,psw);//第一个参数psw 一定要和上面SQL语句字段顺序一致
                ps.setInt(2,userId);//第二个参数userId 一定要和上面SQL语句字段顺序一致
                result=ps.executeUpdate();//返回1 执行成功
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return result;
    }

    public User getUserById(int userId){
        String sql = "select * from Changshu.user where userid = ?";
        conn = DBOpenHelper.getConn();
        User user = new User();
        try{
            boolean closed=conn.isClosed();
            if(conn!=null&&(!closed)){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,userId);//第一个参数userId 一定要和上面SQL语句字段顺序一致
                rs = ps.executeQuery();
                if(rs!=null){
                    if (rs.next()){
                        user.setUserId(rs.getInt("userid"));
                        user.setUser_name(rs.getString("user_name"));
                        user.setPsw(rs.getString("psw"));
                        user.setCompany_name(rs.getString("company_name"));
                        user.setPhone_number(rs.getString("phone_number"));
                        user.setUser_rank(rs.getInt("user_rank"));
                        user.setUser_email(rs.getString("user_email"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return user;
    }

    public User getUserByName(String user_name){
        String sql = "select * from Changshu.user where user_name = ?";
        conn = DBOpenHelper.getConn();
        User user = new User();
        try{
            boolean closed=conn.isClosed();
            if(conn!=null&&(!closed)){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setString(1,user_name);//第一个参数userId 一定要和上面SQL语句字段顺序一致
                rs = ps.executeQuery();
                if(rs!=null){
                    if (rs.next()){
                        user.setUserId(rs.getInt("userid"));
                        user.setUser_name(rs.getString("user_name"));
                        user.setPsw(rs.getString("psw"));
                        user.setCompany_name(rs.getString("company_name"));
                        user.setPhone_number(rs.getString("phone_number"));
                        user.setUser_rank(rs.getInt("user_rank"));
                        user.setUser_email(rs.getString("user_email"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return user;
    }
}
