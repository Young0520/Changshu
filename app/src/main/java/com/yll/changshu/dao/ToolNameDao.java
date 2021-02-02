package com.yll.changshu.dao;

import com.yll.changshu.entity.ToolKXQ;
import com.yll.changshu.entity.ToolName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToolNameDao {
    private Connection conn=null; //打开数据库对象
    private PreparedStatement ps=null;//操作整合sql语句的对象
    private ResultSet rs=null;//查询结果的集合

    //DBService 对象
    public static ToolNameDao toolNameDao = null;

    /**
     * 构造方法 私有化
     * */
    public ToolNameDao(){

    }

    /**
     * 获取MySQL数据库单例类对象
     * */
    public static ToolNameDao getToolNameDao(){
        if(toolNameDao==null){
            toolNameDao=new ToolNameDao();
        }
        return toolNameDao;
    }

    public List<ToolName> getAllToolName(){
        List<ToolName> list = new ArrayList<>();
        //MySQL 语句
        String sql="select * from Changshu.tool_name";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            ToolName toolName = new ToolName();
                            toolName.setNode_id(rs.getInt("node_id"));
                            toolName.setFather_id(rs.getInt("father_id"));
                            toolName.setNode_name_id(rs.getInt("node_name_id"));
                            toolName.setNode_name(rs.getString("node_name"));
                            toolName.setName_rank(rs.getInt("name_rank"));
                            toolName.setTool_picture(rs.getString("tool_picture"));
                            list.add(toolName);
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

    public List<ToolName> getToolNameByFather_id(int father_id){
        List<ToolName> list = new ArrayList<>();
        //MySQL 语句
        String sql="select * from Changshu.tool_name where father_id = ?";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,father_id);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            ToolName toolName = new ToolName();
                            toolName.setNode_id(rs.getInt("node_id"));
                            toolName.setFather_id(rs.getInt("father_id"));
                            toolName.setNode_name_id(rs.getInt("node_name_id"));
                            toolName.setNode_name(rs.getString("node_name"));
                            toolName.setName_rank(rs.getInt("name_rank"));
//                            toolName.setTool_picture(rs.getString("tool_picture"));
//                            System.out.println(toolName.getNode_name());
                            list.add(toolName);
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
}
