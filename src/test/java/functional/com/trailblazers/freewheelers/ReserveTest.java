package functional.com.trailblazers.freewheelers;

import functional.com.trailblazers.freewheelers.Screens.ReserveScreen;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReserveTest {

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
        DatabaseTestUtil.clean();
    }


    @Test
    public void shouldLogOutUserBackToHomePageWhenLogOutLinkIsClicked(){
        ReserveScreen.loginIntoReserveScreenAsUser(driver);
        driver.findElement(By.linkText("Logout")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/"));
    }

    @Test
    public void shouldShowReservedItemOnReservePage() throws SQLException {
        ReserveScreen.userReservesANewItem(driver);
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/reserve"));
        assertEquals("frame1", driver.findElement(By.xpath("//tbody//tr[1]//td[1]")).getText());
        assertEquals("14.99", driver.findElement(By.xpath("//tbody//tr[1]//td[2]")).getText());
        assertEquals("I should see this item", driver.findElement(By.xpath("//tbody//tr[1]//td[3]")).getText());
        assertEquals("FRAME", driver.findElement(By.xpath("//tbody//tr[1]//td[4]")).getText());

    }


}