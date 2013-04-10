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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LoginTest {
    static WebDriver driver;
    private final String USER = "UserCat";
    private final String USERPASSWORD = "user";

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
        logout();
        DatabaseTestUtil.clean();
        driver.get("http://localhost:8080/login");
    }

    private void logout() {
        driver.get("http://localhost:8080/logout");
        driver.findElement(By.linkText("Logout")).click();
    }

    @Test
    public void shouldLetUserLogoutBackToHomePageAfterBeingDeniedAcsess(){
        LoginScreen.loginAs(USER, USERPASSWORD, driver);
        driver.get("http://localhost:8080/admin");
        assertTrue(TestUtils.isElementPresent(driver, By.id("http_403")));
        driver.findElement(By.linkText("Logout")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/"));
    }

    @Test
    public void shouldLogoutAndNotGiveAccessToAdmin(){
        logout();
        driver.get("http://localhost:8080/admin");
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/login"));
    }

}
