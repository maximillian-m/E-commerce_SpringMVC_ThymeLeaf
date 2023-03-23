package com.example.ecommerceweek6.CONNECTION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {

    private static Connection connection = null;
    private static String username = "root";
    private static String classname = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/ifey";
    private static String password = "08033896535Aa*";

    static {
        try {
            Class.forName(classname);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static java.sql.Connection getConnection() {
        return connection;
    }
}

