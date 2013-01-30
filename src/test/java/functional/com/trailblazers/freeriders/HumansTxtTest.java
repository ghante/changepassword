package functional.com.trailblazers.freeriders;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.SQLException;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class HumansTxtTest {

    static WebDriver driver;

    @BeforeClass
    public static void before() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void after() {
        driver.close();
    }

    @Test
    public void setup() throws SQLException {
        driver.get("http://localhost:8080/humans.txt");
        String pageContents = driver.getPageSource();
        assertThat(pageContents, containsString("Nishitha Ningegowda"));

    }

}
