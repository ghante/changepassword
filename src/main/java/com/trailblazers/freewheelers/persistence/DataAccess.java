package com.trailblazers.freewheelers.persistence;

import com.trailblazers.freewheelers.persistence.DatabaseConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DataAccess {

    public static final String ITEM_TYPES_QUERY = "select name from item_type";
    private DatabaseConnectionProvider connectionProvider;

    public DataAccess(DatabaseConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public ArrayList getItemTypes() throws SQLException {
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

    public void createAccount(String email, String password, String name, String phoneNumber, String address) throws SQLException {
        Connection connection = connectionProvider.getConnection();

        String sql = "insert into account (email_address, account_name, password, phone_number, address, enabled) ";
        sql +=       "values ('" + email + "', '" + name + "', '" + password + "', '" + phoneNumber + "', '" + address + "', true)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();

        sql  = "insert into account_role (account_name, role) values ('" + name + "', 'ROLE_USER')";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();

        connection.close();
    }
}