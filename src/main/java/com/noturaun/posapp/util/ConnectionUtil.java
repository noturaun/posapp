package com.noturaun.posapp.util;

import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionUtil {

    //using singleton pattern

    private static Connection connection = null;


    static {
        ResourceBundle rb = ResourceBundle.getBundle("db");
        String jdbcUrl = rb.getString("jdbcUrl");
        String username = rb.getString("username");
        String password = rb.getString("password");
        try{
            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e){
            System.out.println("Unable to connect : " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    //using connection pool
    private static final HikariDataSource datasource;
    static {
        String conf = "src/main/resources/db.properties";
        HikariConfig config = new HikariConfig(conf);
        datasource = new HikariDataSource(config);
    }

    public static HikariDataSource getDatasource(){ return datasource; }
}
