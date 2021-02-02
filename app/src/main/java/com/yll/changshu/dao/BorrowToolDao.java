package com.yll.changshu.dao;

import com.yll.changshu.entity.BorrowTool;
import com.yll.changshu.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowToolDao {
    private Connection conn=null; //打开数据库对象
    private PreparedStatement ps=null;//操作整合sql语句的对象
    private ResultSet rs=null;//查询结果的集合

    //DBService 对象
    public static BorrowToolDao borrowToolDao = null;

    /**
     * 构造方法 私有化
     * */
    private BorrowToolDao(){

    }

    /**
     * 获取MySQL数据库单例类对象
     * */
    public static BorrowToolDao getBorrowToolDao(){
        if(borrowToolDao==null){
            borrowToolDao=new BorrowToolDao();
        }
        return borrowToolDao;
    }

    public List<BorrowTool> getBorrowtoolByList_id(int list_id){
        List<BorrowTool> list = new ArrayList<>();
        String sql="select * from Changshu.borrow_tool where list_id = ?";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,list_id);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            BorrowTool borrowTool = new BorrowTool();
                            borrowTool.setList_id(rs.getInt("list_id"));
                            borrowTool.setToolname_id(rs.getInt("toolname_id"));
                            borrowTool.setToolname(rs.getString("toolname"));
                            borrowTool.setType(rs.getString("type"));
                            borrowTool.setNumber(rs.getInt("number"));
                            borrowTool.setState(rs.getInt("state"));
                            list.add(borrowTool);
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

    public int insertBorrowTool(BorrowTool borrowTool){
        int result = -1;
        String sql = "insert into Changshu.borrow_tool (list_id,toolname_id,toolname,type,number,state) VALUES (?,?,?,?,?,?)";
        conn = DBOpenHelper.getConn();
        try{
            boolean closed=conn.isClosed();
            if(conn!=null&&(!closed)){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,borrowTool.getList_id());
                ps.setInt(2,borrowTool.getToolname_id());
                ps.setString(3,borrowTool.getToolname());
                ps.setString(4,borrowTool.getType());
                ps.setInt(5,borrowTool.getNumber());
                ps.setInt(6,borrowTool.getState());
                result=ps.executeUpdate();//返回1 执行成功
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return result;
    }

    public int updateBorrowToolStateByList_idAndToolname_id(int state, int list_id, int toolname_id){
        int result = -1;
        String sql = "update Changshu.borrow_tool set state = ? where list_id = ? and toolname_id = ?";
        conn = DBOpenHelper.getConn();
        try{
            boolean closed=conn.isClosed();
            if(conn!=null&&(!closed)){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,state);
                ps.setInt(2,list_id);
                ps.setInt(3,toolname_id);
                result=ps.executeUpdate();//返回1 执行成功
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return result;
    }

    public int updateBorrowToolNumberByList_idAndToolname_id(int number, int list_id, int toolname_id){
        int result = -1;
        String sql = "update Changshu.borrow_tool set state = ? where list_id = ? and number = ?";
        conn = DBOpenHelper.getConn();
        try{
            boolean closed=conn.isClosed();
            if(conn!=null&&(!closed)){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1,number);
                ps.setInt(2,list_id);
                ps.setInt(3,toolname_id);
                result=ps.executeUpdate();//返回1 执行成功
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return result;
    }
}
