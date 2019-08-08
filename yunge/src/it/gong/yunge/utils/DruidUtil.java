package it.gong.yunge.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DruidUtil {
    private static Properties pos = new Properties();
    private static DataSource ds =null;
    static{
        InputStream is = DruidUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            pos.load(is);
            ds = DruidDataSourceFactory.createDataSource(pos);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getconn() throws SQLException {
            return ds.getConnection();

    }
    public static void close(Statement sta,Connection conn){
        close(null,sta,conn);
    }
    public static void close(PreparedStatement sta,Connection conn){
        close(null,sta,conn);
    }
    public static void close(ResultSet rs, Statement sta, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(sta!=null){
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(ResultSet rs, PreparedStatement sta, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(sta!=null){
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static DataSource getDs(){
        return ds;
    }
}
