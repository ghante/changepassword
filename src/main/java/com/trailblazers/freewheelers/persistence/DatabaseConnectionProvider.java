package com.trailblazers.freewheelers.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionProvider {
    public DatabaseConnectionProvider() {
    }

    public Connection getConnection() throws SQLException, IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("database.properties");

        Properties properties = new Properties();
        properties.load(inputStream);

        return DriverManager.getConnection(properties.getProperty("jdbc.url"),
                                           properties.getProperty("jdbc.username"),
                                           properties.getProperty("jdbc.password"));
    }
}