package com.raven.all;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Database URL, username, and password
    private static final String URL = "jdbc:mysql://localhost:3306/waterstation";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Single instance of the connection
    private static Connection connection = null;

    // Method to establish a connection to the database
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Register the JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Open a connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully.");
            } catch (ClassNotFoundException e) {
                System.out.println("MySQL JDBC Driver not found.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Failed to connect to the database.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Method to close the connection (if needed)
    public static void closeConnection() {
        if (connection!= null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close the database connection.");
                e.printStackTrace();
            }
        }
    }

    // Method to manually reconnect if needed
    public static void reconnect() {
        closeConnection();
        getConnection();
    }
}