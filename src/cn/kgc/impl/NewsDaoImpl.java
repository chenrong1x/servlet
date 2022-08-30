package cn.kgc.impl;

import cn.kgc.dao.BaseDao;
import cn.kgc.dao.NewsDao;

import java.sql.*;

public class NewsDaoImpl extends BaseDao implements NewsDao {

    Connection connection = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    //增加数据
    public void addData(int id, String name) {
        String sql = "INSERT INTO Table1(id,NAME) VALUES(?,?)";
        Object[] params = {id, name};
        int i = this.executeUpdate(sql, params);
        if (i > 0) {
            System.out.println("插入新闻成功");
        }
        this.closeResource();
    }

    //删除数据
    public void deleteData(int id){
        String sql = "DELETE FROM Table1 WHERE id=?";
        Object[] params = {id};
        int i = this.executeUpdate(sql, params);
        if (i > 0) {
            System.out.println("删除新闻成功");
        }
        this.closeResource();
    }

    //修改数据
    public void updateData(int id, String name){
        String sql = "UPDATE Table1 SET NAME=? WHERE id=?";
        Object[] params = {name,id};
        int i = this.executeUpdate(sql, params);
        if (i > 0) {
            System.out.println("修改新闻成功");
        }
        this.closeResource();
    }

    //获取数据库数据
    public void getData(){
        ResultSet rs = null;
        try {
            String sql = "SELECT id, name FROM Table1";
            Object[] params = {};
            rs = this.executeSQL(sql, params);
            while(rs.next()){
                int id = rs.getInt("id");
                String resName = rs.getString("name");
                System.out.println(id + "\t" + resName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            this.closeResource();
        }
    }

    //根据名字获取数据库数据
    public void getData(String name){
        ResultSet rs = null;
        try {
            String sql = "SELECT id, name FROM Table1 WHERE NAME=?";
            Object[] params = {name};
            rs = this.executeSQL(sql, params);
            while(rs.next()){
                int id = rs.getInt("id");
                String resName = rs.getString("name");
                System.out.println(id + "\t" + resName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            this.closeResource();
        }
    }

    public static void main(String[] args) {
        NewsDao dao = new NewsDaoImpl();

        dao.updateData(1, "chenrong");
        dao.getData();
    }
}
