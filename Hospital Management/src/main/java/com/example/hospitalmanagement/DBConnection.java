package com.example.hospitalmanagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    // Update these values for your database
    private static final String HOST = "java-hospital.cdiwkuk62ztz.ap-southeast-2.rds.amazonaws.com";
    private static final String PORT = "3306";
    private static final String DB_NAME = "hospital";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?useSSL=false&serverTimezone=UTC";
    private static final String USER = "jeriks";
    private static final String PASSWORD = "jeriksgiftson";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load MySQL JDBC Driver (optional for newer Java versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
        return connection;
    }

}
