package functional.com.trailblazers.freewheelers;

import functional.com.trailblazers.freewheelers.Screens.LoginScreen;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.SQLException;
import java.util.Date;

import static functional.com.trailblazers.freewheelers.DatabaseTestUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminTest {

    static WebDriver driver;

    @BeforeClass
    public static void before() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void after() {
        driver.close();
    }

    @Before
    public void setup() throws SQLException {
        clean();
        driver.get("http://localhost:8080/login");
        LoginScreen.loginAs("AdminCat", "admin", driver);
    }

    @Test
    public void shouldTakeUserToItemPageFromAdminScreen() {
        driver.findElement(By.linkText("Add a item")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/item"));
    }

    @Test
    public void shouldTakeUserToHomeScreen() {
        driver.findElement(By.linkText("Home")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/"));
    }

    @Test
    public void shouldTakeUserToAdminScreen() {
        driver.findElement(By.linkText("Admin Profile")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/admin"));
    }

    @Test
    public void shouldLogOutUserBackToHomePageWhenLogOutLinkIsClicked() {
        driver.findElement(By.linkText("Logout")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/"));
    }

    @Test
    public void shouldShowUserDetailsWhenClickingOnUserInOrderList() throws SQLException {
        insertIntoItems(1, "Some Frame", "500.00", "some frame", "FRAME");
        insertIntoAccount(42, "SomeName", "somebody@web.de", "secretPassword", "004945542741", "Some Street 1, Some Town", "TRUE", "ROLE_USER");
        reserveOrder(1, 1, 42, "NEW", new Date());

        driver.findElement(By.linkText("Admin Profile")).click();
        driver.findElement(By.linkText("SomeName")).click();

        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/userProfile/SomeName"));
        assertTrue(driver.findElement(By.id("userDetails")).getText().contains("SomeName"));
        assertTrue(driver.findElement(By.id("userDetails")).getText().contains("somebody@web.de"));
        assertTrue(driver.findElement(By.id("userDetails")).getText().contains("Some Street 1, Some Town"));
    }


    @Test
    public void shouldSeeStatusOnOrdersPage() throws SQLException {
        insertIntoItems(1, "Some Frame", "500.00", "some frame", "FRAME");
        insertIntoAccount(42, "SomeName", "somebody@web.de", "secretPassword", "004945542741", "Some Street 1, Some Town", "TRUE", "ROLE_USER");
        reserveOrder(1, 1, 42, "NEW", new Date());

        driver.findElement(By.linkText("Admin Profile")).click();

        assertEquals("Status", driver.findElements(By.cssSelector("#prettyTable thead tr th")).get(3).getText());
        assertEquals("NEW", driver.findElements(By.cssSelector("#prettyTable tr td")).get(3).getText());
    }
}
