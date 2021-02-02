package com.yll.changshu.dao;

import com.yll.changshu.entity.ToolInventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToolInventoryDao {
    private Connection conn=null; //打开数据库对象
    private PreparedStatement ps=null;//操作整合sql语句的对象
    private ResultSet rs=null;//查询结果的集合

    //DBService 对象
    public static ToolInventoryDao toolInventoryDao = null;

    /**
     * 构造方法 私有化
     * */
    private ToolInventoryDao(){

    }

    /**
     * 获取MySQL数据库单例类对象
     * */
    public static ToolInventoryDao getToolInventoryDao(){
        if(toolInventoryDao==null){
            toolInventoryDao=new ToolInventoryDao();
        }
        return toolInventoryDao;
    }

    public List<ToolInventory> getAllToolInventory(){
        List<ToolInventory> list = new ArrayList<>();
        String sql = "select * from Changshu.toolinventory";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            ToolInventory toolInventory = new ToolInventory();
                            toolInventory.setToolname_id(rs.getInt("toolname_id"));
                            toolInventory.setToolname(rs.getString("toolname"));
                            toolInventory.setTooltype(rs.getString("tooltype"));
                            toolInventory.setParameter(rs.getString("parameter"));
                            toolInventory.setSum_number(rs.getInt("sum_number"));
                            toolInventory.setNow_number(rs.getInt("now_number"));
                            toolInventory.setSafetime(rs.getInt("safetime"));
                            list.add(toolInventory);
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

    public List<ToolInventory> getToolInventoryByToolname_id(int toolname_id){
        List<ToolInventory> list = new ArrayList<>();
        String sql = "select * from Changshu.toolinventory where toolname_id = ?";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,toolname_id);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            ToolInventory toolInventory = new ToolInventory();
                            toolInventory.setToolname_id(rs.getInt("toolname_id"));
                            toolInventory.setToolname(rs.getString("toolname"));
                            toolInventory.setTooltype(rs.getString("tooltype"));
                            toolInventory.setParameter(rs.getString("parameter"));
                            toolInventory.setSum_number(rs.getInt("sum_number"));
                            toolInventory.setNow_number(rs.getInt("now_number"));
                            toolInventory.setSafetime(rs.getInt("safetime"));
                            list.add(toolInventory);
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

    public ToolInventory getToolInventoryByToolname_idAndTooltype(int toolname_id, String toolType){
        String sql = "select * from Changshu.toolinventory where toolname_id = ? and tooltype = ?";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        ToolInventory toolInventory = new ToolInventory();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,toolname_id);
                ps.setString(2,toolType);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        if(rs.next()){
                            toolInventory.setToolname_id(rs.getInt("toolname_id"));
                            toolInventory.setToolname(rs.getString("toolname"));
                            toolInventory.setTooltype(rs.getString("tooltype"));
                            toolInventory.setParameter(rs.getString("parameter"));
                            toolInventory.setSum_number(rs.getInt("sum_number"));
                            toolInventory.setNow_number(rs.getInt("now_number"));
                            toolInventory.setSafetime(rs.getInt("safetime"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps,rs);//关闭相关操作
        return toolInventory;
    }

    public int updateToolInventoryNow_numberByToolname_idAndTooltype(int now_number, int toolname_id, int tooltype){
        int result = -1;
        String sql = "update Changshu.toolinventory set now_number = ? where toolname_id = ? and tooltype = >";
        conn = DBOpenHelper.getConn();
        try{
            boolean closed=conn.isClosed();
            if(conn!=null&&(!closed)){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,now_number);//第一个参数psw 一定要和上面SQL语句字段顺序一致
                ps.setInt(2,toolname_id);//第二个参数userId 一定要和上面SQL语句字段顺序一致
                ps.setInt(3,tooltype);
                result=ps.executeUpdate();//返回1 执行成功
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return result;
    }
}
