package functional.com.trailblazers.freeriders;

import functional.com.trailblazers.freeriders.Screens.LoginScreen;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.SQLException;

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
        functional.com.trailblazers.freeriders.DatabaseTestUtil.clean();
        driver.get("http://localhost:8080/login");
        LoginScreen.loginAs("AdminCat", "admin", driver);
    }

    @Test
     public void  shouldTakeUserToItemPageFromAdminScreen(){
        driver.findElement(By.linkText("Add a item")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/item"));
    }

    @Test
    public void  shouldTakeUserToHomeScreen(){
        driver.findElement(By.linkText("Home")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/"));
    }

    @Test
    public void  shouldTakeUserToAdminScreen(){
        driver.findElement(By.linkText("Admin Profile")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/admin"));
    }

    @Test
    public void shouldLogOutUserBackToHomePageWhenLogOutLinkIsClicked(){
        driver.findElement(By.linkText("Logout")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/"));
    }
}
