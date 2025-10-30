package com.example.hospitalmanagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    // Update these values for your database
    private static final String URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String USER = "jeriks";
    private static final String PASSWORD = "jeriks";
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

    public static void main(String[] args) {
        // Test connection
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.close();
                System.out.println("🔒 Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
