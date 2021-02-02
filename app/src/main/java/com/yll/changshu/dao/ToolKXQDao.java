package com.yll.changshu.dao;

import com.yll.changshu.entity.ToolKXQ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToolKXQDao {
    private Connection conn=null; //打开数据库对象
    private PreparedStatement ps=null;//操作整合sql语句的对象
    private ResultSet rs=null;//查询结果的集合

    //DBService 对象
    public static ToolKXQDao toolKXQDao = null;

    /**
     * 构造方法 私有化
     * */
    private ToolKXQDao(){

    }

    /**
     * 获取MySQL数据库单例类对象
     * */
    public static ToolKXQDao getToolKXQDao(){
        if(toolKXQDao==null){
            toolKXQDao=new ToolKXQDao();
        }
        return toolKXQDao;
    }

    public List<ToolKXQ> getAllToolKXQ(){
        List<ToolKXQ> list = new ArrayList<>();
        //MySQL 语句
        String sql="select * from Changshu.tool_kxq";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            ToolKXQ toolKXQ = new ToolKXQ();
                            toolKXQ.setType(rs.getString("type"));
                            toolKXQ.setParameter(rs.getString("parameter"));
                            toolKXQ.setA(rs.getInt("a"));
                            toolKXQ.setB(rs.getInt("b"));
                            toolKXQ.setLoad(rs.getInt("load"));
                            list.add(toolKXQ);
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
