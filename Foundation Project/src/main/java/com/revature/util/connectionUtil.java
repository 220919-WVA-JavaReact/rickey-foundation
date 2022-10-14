package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class connectionUtil {
    // going to make a singleton instance connection
    // limit the number of connections we can make
    // to make this a singleton we need; private static instance, private constructor; public static getInstance() method
    private static Connection conn = null;

    private connectionUtil() {


    }

    public static Connection getConnection() {
//         check if we have a connection; if exist return connection
//         if no connection create new connection
        try {
            if (conn != null && !conn.isClosed()) {
                // checks that the connection exist and is not closed
                System.out.println("using previously made connection");
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

//
//        // Not secure delete later
        String url = "jdbc:postgresql://database-1.cd4xb7fxatmk.us-east-1.rds.amazonaws.com:5432/postgres";
        String username = "postgres";
        String password = "postgres";
//
//        // use these credentials to create a connection
//
//        try {
//            conn = DriverManager.getConnection(url, username, password);
//            System.out.println("new connection");
//        } catch (SQLException e) {
//            System.out.println(url);
//            System.out.println("no connection");
//            e.printStackTrace();
//
//        }
//        String url = System.getenv("url");
//        String username = System.getenv("username");
//        String password = System.getenv("password");

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection has been made");
        } catch (SQLException e) {
            System.out.println("connection can't be made");
            e.printStackTrace();
        }
        return conn;
    }


}
