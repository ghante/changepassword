package com.trailblazers.freewheelers.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionProvider {
    public DatabaseConnectionProvider() {
    }

    public Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "postgres");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/trailblazers", properties);
    }
}