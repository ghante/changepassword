package functional.com.thoughtworks.university.selenium;

import com.thoughtworks.university.selenium.Screens.LoginScreen;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomeTest {
    static WebDriver driver;

    @BeforeClass
    public static void before() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void after() {
        driver.close();
    }

    //TODO: goto screen methods need to be extracted
    @Before
    public void setup() throws SQLException {
        logout();
        DatabaseTestUtil.clean();
        driver.get("http://localhost:8080/trunk/");
    }

    private void logout() {
        driver.get("http://localhost:8080/trunk/logout");
        driver.findElement(By.linkText("Logout")).click();
    }

    @Test
    public void  shouldTakeUserToHomeScreen(){
        driver.findElement(By.linkText("Home")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/trunk/"));
    }

    @Test
    public void  shouldTakeUserToLoginScreen(){
        driver.findElement(By.linkText("Admin Profile")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/trunk/login"));
    }

    @Test
    public void shouldShowListOfItemsOnHomeScreen() throws SQLException {
        DatabaseTestUtil.insertIntoItems(111, "frame1", "14.99", "I should see this item", "FRAME");
        //refresh screen
        driver.get("http://localhost:8080/trunk/");
        assertEquals("frame1", driver.findElement(By.xpath("//tbody//tr[1]//td[1]")).getText());
        assertEquals("14.99", driver.findElement(By.xpath("//tbody//tr[1]//td[2]")).getText());
        assertEquals("I should see this item", driver.findElement(By.xpath("//tbody//tr[1]//td[3]")).getText());
        assertEquals("FRAME", driver.findElement(By.xpath("//tbody//tr[1]//td[4]")).getText());
        assertEquals("1", driver.findElement(By.xpath("//tbody//tr[1]//td[5]")).getText());
    }

    @Test
    public void userShouldBeDirectedToLoginPageBeforeOrderPageWhenReservingItem() throws SQLException {
        DatabaseTestUtil.insertIntoItems(111, "frame1", "14.99", "I should see this item", "FRAME");
        //refresh screen
        driver.get("http://localhost:8080/trunk/");
        driver.findElement(By.id("reserve")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/trunk/login"));
        LoginScreen.loginAs("UserCat", "user", driver);
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/trunk/reserve"));

    }

    @Test
    public void itemsWithZeroCountShouldNotBeDisplayed() throws SQLException {
        DatabaseTestUtil.insertIntoItems(111, "frame1", "14.99", "I should see this item", "FRAME");
        DatabaseTestUtil.insertIntoItems(222, "frame2", "14.99", "I should see this item", "FRAME");
        //refresh screen
        driver.get("http://localhost:8080/trunk/");
        driver.findElement(By.id("reserve")).click();
        LoginScreen.loginAs("UserCat", "user", driver);
        driver.get("http://localhost:8080/trunk/");

        assertEquals("frame2", driver.findElement(By.xpath("//tbody//tr[1]//td[1]")).getText());
        assertEquals("1", driver.findElement(By.xpath("//tbody//tr[1]//td[5]")).getText());
    }
}
