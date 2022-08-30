package cn.kgc.dao;

import cn.kgc.util.ConfigManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

//数据库操作基类
public class BaseDao {
    Connection connection = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    //获取数据库连接
    public boolean getConnection()  {
        try {
            //加载不同数据库驱动
            Class.forName(ConfigManager.getInstance().getSring("jdbc.driver"));
            //创建链接
            String url = ConfigManager.getInstance().getSring("jdbc.connection.url");
            String username = ConfigManager.getInstance().getSring("jdbc.connection.username");
            String password = ConfigManager.getInstance().getSring("jdbc.connection.password");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    //获取数据库连接2
    public boolean getConnection2()  {
        //初始化上下文
        try {
            Context cxt = new InitialContext();
            //获取与逻辑名称相关的数据源对象
            DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/news");
            //通过数据源获取数据库连接
            ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //增删改
    public int executeUpdate(String sql, Object[]params){
        int updateRows = 0;
        if(this.getConnection()){
            try {
                pstmt = connection.prepareStatement(sql);
                //填充占位符
                for(int i=0; i<params.length; i++){
                    pstmt.setObject(i+1,params[i]);
                }
                updateRows = pstmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return updateRows;
    }
    //查
    public ResultSet executeSQL(String sql, Object[] params){
        if(this.getConnection2()){
            try {
                pstmt = connection.prepareStatement(sql);
                //填充占位符
                for(int i=0; i<params.length; i++){
                    pstmt.setObject(i+1, params[i]);
                }
                rs = pstmt.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return rs;
    }

    //释放资源
    public boolean closeResource(){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        if(pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
