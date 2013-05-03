package com.trailblazers.freewheelers.persistence;

import com.trailblazers.freewheelers.persistence.DatabaseConnectionProvider;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DataAccess {

    public static final String ITEM_TYPES_QUERY = "select name from item_type";
    private DatabaseConnectionProvider connectionProvider;

    public DataAccess(DatabaseConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public ArrayList getItemTypes() throws SQLException, IOException {
        Connection connection = connectionProvider.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(ITEM_TYPES_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList names = new ArrayList();
        while (resultSet.next()) {
            names.add(resultSet.getString("name"));
        }
        connection.close();
        return names;
    }

}