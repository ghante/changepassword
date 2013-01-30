package functional.com.trailblazers.freeriders.Screens;

import functional.com.trailblazers.freeriders.DatabaseTestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.SQLException;

public class ReserveScreen {

    public static void userReservesANewItem(WebDriver driver) throws SQLException {
        DatabaseTestUtil.insertIntoItems(111, "frame1", "14.99", "I should see this item", "FRAME");
        //refresh screen
        driver.get("http://localhost:8080/trunk/");
        driver.findElement(By.id("reserve")).click();
        LoginScreen.loginAs("UserCat", "user", driver);
    }

    public static void loginIntoReserveScreenAsUser(WebDriver driver) {
        driver.get("http://localhost:8080/trunk/reserve");
        LoginScreen.loginAs("UserCat", "user", driver);
    }
}
