package com.flight.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource ds;
    static{
        Properties pro = new Properties();
        String fileUrl = JDBCUtils.class.getClassLoader().getResource("jdbc.properties").getPath();
        fileUrl = fileUrl.replaceAll("%20", " ");
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(fileUrl));
            pro.load(is);
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取数据库
    public static DataSource getDataSource(){
        return ds;
    }
    //获取数据库连接
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
