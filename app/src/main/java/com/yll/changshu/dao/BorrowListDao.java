package com.yll.changshu.dao;


import com.yll.changshu.entity.BorrowList;
import com.yll.changshu.entity.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowListDao {
    private Connection conn=null; //打开数据库对象
    private PreparedStatement ps=null;//操作整合sql语句的对象
    private ResultSet rs=null;//查询结果的集合

    //DBService 对象
    public static BorrowListDao borrowListDao = null;

    /**
     * 构造方法 私有化
     * */
    private BorrowListDao(){

    }

    /**
     * 获取MySQL数据库单例类对象
     * */
    public static BorrowListDao getBorrowListDao(){
        if(borrowListDao==null){
            borrowListDao=new BorrowListDao();
        }
        return borrowListDao;
    }

    public List<BorrowList> getAllBorrowList(){
        List<BorrowList> list = new ArrayList<>();
        //MySQL 语句
        String sql="select * from Changshu.borrowlist";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        try {

            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            BorrowList borrowList=new BorrowList();
                            borrowList.setList_id(rs.getInt("list_id"));
                            borrowList.setCustomId(rs.getInt("customid"));
                            borrowList.setApply_out(rs.getDate("apply_out"));
                            borrowList.setApply_return(rs.getDate("apply_return"));
                            borrowList.setActual_out(rs.getDate("actual_out"));
                            borrowList.setActual_return(rs.getDate("actual_return"));
                            borrowList.setState(rs.getInt("state"));
                            borrowList.setApprover_id(rs.getInt("approver_id"));
                            borrowList.setManager_id(rs.getInt("manager_id"));
                            borrowList.setCancel_reason(rs.getString("cancle_reason"));
                            list.add(borrowList);
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

    public BorrowList getBorrowListByList_id(int list_id){
        BorrowList borrowList=new BorrowList();
        //MySQL 语句
        String sql="select * from Changshu.borrowlist where list_id = ?";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        try {

            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,list_id);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        if(rs.next()){
                            borrowList.setList_id(rs.getInt("list_id"));
                            borrowList.setCustomId(rs.getInt("customid"));
                            borrowList.setApply_out(rs.getDate("apply_out"));
                            borrowList.setApply_return(rs.getDate("apply_return"));
                            borrowList.setActual_out(rs.getDate("actual_out"));
                            borrowList.setActual_return(rs.getDate("actual_return"));
                            borrowList.setState(rs.getInt("state"));
                            borrowList.setApprover_id(rs.getInt("approver_id"));
                            borrowList.setManager_id(rs.getInt("manager_id"));
                            borrowList.setCancel_reason(rs.getString("cancle_reason"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps,rs);//关闭相关操作
        return borrowList;
    }

    public List<BorrowList> getBorrowListByCustomid(int customid){
        List<BorrowList> list = new ArrayList<>();
        //MySQL 语句
        String sql="select * from Changshu.borrowlist where customid = ?";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        try {

            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,customid);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            BorrowList borrowList=new BorrowList();
                            borrowList.setList_id(rs.getInt("list_id"));
                            borrowList.setCustomId(rs.getInt("customid"));
                            borrowList.setApply_out(rs.getDate("apply_out"));
                            borrowList.setApply_return(rs.getDate("apply_return"));
                            borrowList.setActual_out(rs.getDate("actual_out"));
                            borrowList.setActual_return(rs.getDate("actual_return"));
                            borrowList.setState(rs.getInt("state"));
                            borrowList.setApprover_id(rs.getInt("approver_id"));
                            borrowList.setManager_id(rs.getInt("manager_id"));
                            borrowList.setCancel_reason(rs.getString("cancle_reason"));
                            list.add(borrowList);
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

    public List<BorrowList> getBorrowListByState(int state){
        List<BorrowList> list = new ArrayList<>();
        //MySQL 语句
        String sql="select * from Changshu.borrowlist where state = ?";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        try {

            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,state);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            BorrowList borrowList=new BorrowList();
                            borrowList.setList_id(rs.getInt("list_id"));
                            borrowList.setCustomId(rs.getInt("customid"));
                            borrowList.setApply_out(rs.getDate("apply_out"));
                            borrowList.setApply_return(rs.getDate("apply_return"));
                            borrowList.setActual_out(rs.getDate("actual_out"));
                            borrowList.setActual_return(rs.getDate("actual_return"));
                            borrowList.setState(rs.getInt("state"));
                            borrowList.setApprover_id(rs.getInt("approver_id"));
                            borrowList.setManager_id(rs.getInt("manager_id"));
                            borrowList.setCancel_reason(rs.getString("cancle_reason"));
                            list.add(borrowList);
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

    public List<BorrowList> getBorrowListByUser_id(int user_id){
        List<BorrowList> list = new ArrayList<>();
        //MySQL 语句
        String sql="select * from Changshu.borrowlist where customid = ?";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        try {

            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,user_id);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            BorrowList borrowList=new BorrowList();
                            borrowList.setList_id(rs.getInt("list_id"));
                            borrowList.setCustomId(rs.getInt("customid"));
                            borrowList.setApply_out(rs.getDate("apply_out"));
                            borrowList.setApply_return(rs.getDate("apply_return"));
                            borrowList.setActual_out(rs.getDate("actual_out"));
                            borrowList.setActual_return(rs.getDate("actual_return"));
                            borrowList.setState(rs.getInt("state"));
                            borrowList.setApprover_id(rs.getInt("approver_id"));
                            borrowList.setManager_id(rs.getInt("manager_id"));
                            borrowList.setCancel_reason(rs.getString("cancle_reason"));
                            list.add(borrowList);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps,rs);//关闭相关操作
//        System.out.println(list.size());
        return list;
    }

    public int insertBorrowList(BorrowList borrowList){
        int result = -1;
        String sql = "insert into Changshu.borrowlist (list_id,customid,apply_out,apply_return,state) VALUES (?,?,?,?,?)";
        conn = DBOpenHelper.getConn();
        try{
            boolean closed=conn.isClosed();
            if(conn!=null&&(!closed)){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,borrowList.getList_id());//第一个参数psw 一定要和上面SQL语句字段顺序一致
                ps.setInt(2,borrowList.getCustomId());//第二个参数userId 一定要和上面SQL语句字段顺序一致
                ps.setDate(3, new Date(borrowList.getApply_out().getTime()));
                ps.setDate(4, new Date(borrowList.getApply_return().getTime()));
                ps.setInt(5,borrowList.getState());
                result=ps.executeUpdate();//返回1 执行成功
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return result;
    }

    public int updateBorrowListStateByList_id(int state, int list_id){
        int result = -1;
        String sql = "update Changshu.borrowlist set state = ? where list_id = ?";
        conn = DBOpenHelper.getConn();
        try{
            boolean closed=conn.isClosed();
            if(conn!=null&&(!closed)){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,state);//第一个参数psw 一定要和上面SQL语句字段顺序一致
                ps.setInt(2,list_id);//第二个参数userId 一定要和上面SQL语句字段顺序一致
                result=ps.executeUpdate();//返回1 执行成功
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return result;
    }

    public int updateBorrowListCancel_reasonByList_id(int list_id, String cancel_reason){
        int result = -1;
        String sql = "update Changshu.borrowlist set cancle_reason = ? where list_id = ?";
        conn = DBOpenHelper.getConn();
        try{
            boolean closed=conn.isClosed();
            if(conn!=null&&(!closed)){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setString(1,cancel_reason);//第一个参数psw 一定要和上面SQL语句字段顺序一致
                ps.setInt(2,list_id);//第二个参数userId 一定要和上面SQL语句字段顺序一致
                result=ps.executeUpdate();//返回1 执行成功
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return result;
    }

    public int updateBorrowListApprover_idByList_id(int approver_id, int list_id){
        int result = -1;
        String sql = "update Changshu.borrowlist set approver_id = ? where list_id = ?";
        conn = DBOpenHelper.getConn();
        try{
            boolean closed=conn.isClosed();
            if(conn!=null&&(!closed)){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,approver_id);//第一个参数psw 一定要和上面SQL语句字段顺序一致
                ps.setInt(2,list_id);//第二个参数userId 一定要和上面SQL语句字段顺序一致
                result=ps.executeUpdate();//返回1 执行成功
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return result;
    }

}
