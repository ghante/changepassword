package integration.com.trailblazers.freeriders.persistence;

import junit.framework.Assert;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemTypeRepositoryTest {

    @Test
    public void shouldGetAllItemTypes() {

        try {
            Properties properties = new Properties();
            properties.setProperty("user", "postgres");
            properties.setProperty("password", "postgres");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/trailblazers", properties);

            PreparedStatement preparedStatement = connection.prepareStatement("select name from item_type");
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList names = new ArrayList();
            while (resultSet.next()) {
                names.add(resultSet.getString("name"));
            }

            ArrayList expectedNames = new ArrayList();
            expectedNames.add("Frames");
            expectedNames.add("Accessories");

            assertThat(names, is(expectedNames));
            connection.close();

        } catch (SQLException e) {
            Assert.fail(e.toString());
        }
    }
}
