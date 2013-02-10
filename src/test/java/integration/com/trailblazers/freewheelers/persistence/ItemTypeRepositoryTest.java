package integration.com.trailblazers.freewheelers.persistence;

import com.trailblazers.freewheelers.persistence.DataAccess;
import com.trailblazers.freewheelers.persistence.DatabaseConnectionProvider;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

import static junit.framework.TestCase.fail;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemTypeRepositoryTest {

    @Test
    public void shouldGetAllItemTypes() {

        try {
            ArrayList names = new DataAccess(new DatabaseConnectionProvider()).getItemTypes();

            ArrayList expectedNames = new ArrayList();
            expectedNames.add("Frames");
            expectedNames.add("Accessories");

            assertThat(names, is(expectedNames));

        } catch (SQLException e) {
            fail(e.toString());
        }
    }
}
