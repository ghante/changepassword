package functional.com.trailblazers.freewheelers;

import functional.com.trailblazers.freewheelers.apis.AdminApi;
import functional.com.trailblazers.freewheelers.apis.ScreenApi;
import functional.com.trailblazers.freewheelers.apis.UserApi;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

public class Order_UserJourneyTest {

    private static WebDriver driver;
    private static AdminApi admin;
    private static UserApi user;
    private static ScreenApi screen;

    @BeforeClass
    public static void before() {
        driver = new FirefoxDriver();

        admin = new AdminApi();
        user = new UserApi(driver);
        screen = new ScreenApi(driver);
    }

    @AfterClass
    public static void after() {
        driver.close();
    }

    @Test
    public void testOrderProcess() throws Exception {
        admin
                .there_is_an_admin("Arno Admin", password_is(SOME_PASSWORD))
                .there_is_a_user("Bob Buyer", password_is(SOME_PASSWORD))
                .there_is_a_frame("Simplon Pavo 3 Ultra", ONLY_ONE_LEFT);

        user
                .logs_in_with("Bob Buyer", password_is(SOME_PASSWORD))
                .visits_home_page();

        screen
                .should_list_item("Simplon Pavo 3 Ultra");

        user
                .reserves_item("Simplon Pavo 3 Ultra")
                .visits_home_page();

        screen
                .should_not_list_item("Simplon Pavo 3 Ultra");

        user
                .logs_in_with("Arno Admin", SOME_PASSWORD)
                .visits_admin_profile();

        screen
                .there_should_be_an_order("Simplon Pavo 3 Ultra", "NEW");

        user
                .changes_order_status("Simplon Pavo 3 Ultra", "IN_PROGRESS");

        screen
                .there_should_be_an_order("Simplon Pavo 3 Ultra", "IN_PROGRESS");
    }

}
