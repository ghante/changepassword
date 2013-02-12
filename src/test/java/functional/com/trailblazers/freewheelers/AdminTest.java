package functional.com.trailblazers.freewheelers;

import functional.com.trailblazers.freewheelers.Screens.LoginScreen;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.sql.SQLException;
import java.util.Date;

import static functional.com.trailblazers.freewheelers.DatabaseTestUtil.*;
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

    @Test
    @Ignore // wip
    public void shouldShowUserDetailsWhenClickingOnUserInOrderList() throws SQLException {
        // given
        insertIntoItems(1, "Some Frame", "500.00", "some frame", "FRAME");
        insertIntoAccount(42, "SomeName", "somebody@web.de", "secretPassword", "004945542741", "Some Street 1, Some Town", "TRUE", "ROLE_USER");
        reserveOrder(1, 1, 42, new Date());

        // when
        driver.findElement(By.linkText("Admin Profile")).click();
        driver.findElement(By.linkText("SomeName")).click();

        // then
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/userProfile/SomeName"));

        // TODO: Assert user details:
        //assertTrue(driver.getTitle().contains("http://localhost:8080/userProfile"));
    }


}
