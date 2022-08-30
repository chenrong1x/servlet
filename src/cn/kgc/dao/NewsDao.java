package cn.kgc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public interface NewsDao {

    //增加数据
    public void addData(int id, String name);

    //删除数据
    public void deleteData(int id);

    //修改数据
    public void updateData(int id, String name);

    //获取数据库数据
    public void getData();

    //根据特定信息获取数据库数据
    public void getData(String name);

}
