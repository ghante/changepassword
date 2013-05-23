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

public class AccountTest {

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
    public void testCreateAccount() throws Exception {
        String jan = "Jan Plewka";

        admin
                .there_is_no_account_for(jan);

        user
                .is_logged_out()
                .logs_in_with(jan, SOME_PASSWORD);

        screen
                .shows_error_alert("login attempt was not successful");

        user
                .creates_an_account(jan, SOME_EMAIL, SOME_PASSWORD, EMPTY_PASSWORD);

        screen
                .shows_error_alert("There were errors");

        user
                .creates_an_account(jan, SOME_EMAIL, SOME_PASSWORD, SOME_PHONE_NUMBER);

        screen
                .shows_message("account has been created");

        user
                .is_logged_out()
                .logs_in_with(jan, SOME_PASSWORD);

        screen
                .shows_in_navbar("Welcome " + jan);
    }

    @Test
    public void testAccessRights() throws Exception {
        String user = "User";
        String admin = "Admin";

        AccountTest.admin
                .there_is_a_user(user, SOME_PASSWORD)
                .there_is_an_admin(admin, SOME_PASSWORD);

        AccountTest.user
                .is_logged_out()
                .visits_his_profile();
        screen
                .shows_login();

        AccountTest.user
                .logs_in_with(user, SOME_PASSWORD)
                .visits_his_profile();
        screen
                .shows_profile_for(user);

        AccountTest.user
                .visits_admin_profile();
        screen
                .shows_error_alert("access is denied");

        AccountTest.user
                .logs_in_with(admin, SOME_PASSWORD)
                .visits_admin_profile();
        screen
                .shows_admin_profile();

        AccountTest.user
                .visits_profile_for(user);
        screen
                .shows_profile_for(user);
    }


}
