package functional.com.trailblazers.freewheelers;

import functional.com.trailblazers.freewheelers.Screens.AdminScreen;
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
        driver.get("http://localhost:8080/admin");
    }

    @Test
    public void shouldTakeUserToItemPageFromAdminScreen() {
        driver.findElement(By.id("manageItems")).click();
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
        insertIntoAccount(42, "SomeName", "somebody@web.de", "secretPassword", "004945542741", "TRUE", "ROLE_USER");
        reserveOrder(1, 1, 42, "NEW", "", new Date());

        driver.findElement(By.linkText("Admin Profile")).click();
        driver.findElement(By.linkText("SomeName")).click();

        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/userProfile/SomeName"));
    }

    @Test
    public void shouldSeeStatusOnOrdersPage() throws SQLException {
        insertIntoItems(1, "Some Frame", "500.00", "some frame", "FRAME");
        insertIntoAccount(42, "SomeName", "somebody@web.de", "secretPassword", "004945542741", "TRUE", "ROLE_USER");
        reserveOrder(1, 1, 42, "NEW", "", new Date());

        driver.findElement(By.linkText("Admin Profile")).click();

        assertEquals("Status", driver.findElements(By.xpath("//thead/tr/th")).get(3).getText());
        assertEquals("NEW", driver.findElement(By.xpath("//tr/td/select/option[@selected=\"selected\"]")).getText());
    }

    @Test
    public void shouldUpdateOrderInformation() throws SQLException {
        insertIntoItems(1, "Some Frame", "500.00", "some frame", "FRAME");
        insertIntoAccount(42, "SomeName", "somebody@web.de", "secretPassword", "004945542741", "TRUE", "ROLE_USER");
        reserveOrder(1, 1, 42, "NEW", "", new Date());

        AdminScreen.changeStatus(driver, "IN_PROGRESS");
        AdminScreen.addNote(driver, "This note is very important!");

        assertEquals("IN_PROGRESS", AdminScreen.selectedStatusName(driver));
        assertEquals("This note is very important!", AdminScreen.getNote(driver));
    }
}
