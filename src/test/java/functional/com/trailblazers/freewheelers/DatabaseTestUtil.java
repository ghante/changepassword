package functional.com.trailblazers.freewheelers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public final class DatabaseTestUtil {

    public static void clean() throws SQLException {
        executeUpdate("DELETE FROM item;");
        executeUpdate("DELETE FROM reserve_order;");
    }

    public static void insertIntoItems(Integer item_id, String name, String price, String description, String type) throws SQLException {
        executeUpdate("INSERT INTO item(item_id, name, price, description, type, quantity) values ( " + item_id + ", '" + name + "','" + price + "','" + description + "','" + type + "', 1);");
    }

    public static void reserveOrder(Integer order_id, Integer item_id, Integer account_id, String status, String note, Date timestamp) throws SQLException {
        executeUpdate("INSERT INTO reserve_order(order_id, item_id, account_id, status, note, reservation_timestamp) values ( " + order_id + "," + item_id + ", '" + account_id + "','" + status + "','" + status + "','" + timestamp.toString() + "');");
    }

    public static void insertIntoAccount(int id, String name, String email, String password, String phone, String address, String isEnabled, String role) throws SQLException {
        executeUpdate("DELETE FROM account WHERE account_id='" + id + "';");
        executeUpdate("INSERT INTO account(account_id, account_name, email_address, password, phone_number, address, enabled) values ( " +
                id + ",'" + name + "','" + email + "','" + password + "','" + phone + "','" + address + "','" + isEnabled + "');");

        executeUpdate("INSERT INTO account_role(account_name, role) values ('" + name + "', '" + role + "');");
    }

    private static void executeUpdate(String query) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = createConnection();
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
        }
    }

    private static Connection createConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/trailblazers";
        String user = "postgres";
        String password = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not find driver");
        }
        return DriverManager.getConnection(url, user, password);
    }

}