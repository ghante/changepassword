package functional.com.trailblazers.freewheelers;


import functional.com.trailblazers.freewheelers.apis.AdminApi;
import functional.com.trailblazers.freewheelers.apis.ScreenApi;
import functional.com.trailblazers.freewheelers.apis.UserApi;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static functional.com.trailblazers.freewheelers.SyntaxSugar.*;

public class Account_UserJourneyTest {

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
        admin
                .there_is_no_user("Jan Plewka");

        user
                .is_logged_out()
                .logs_in_with("Jan Plewka", "My S3cret Passw0rd");

        screen
                .shows_error("login attempt was not successful");

        user
                .creates_an_account(
                        email_is("jan.plewka@selig.eu"),
                        password_is("My S3cret Passw0rd"),
                        name_is("Jan Plewka"),
                        phone_number_is(EMPTY)
                );

        screen
                .shows_error("Must enter a phone number");

        user
                .creates_an_account(
                        email_is("jan.plewka@selig.eu"),
                        password_is("My S3cret Passw0rd"),
                        name_is("Jan Plewka"),
                        phone_number_is("04087870753")
                );

        screen
                .shows_message("account has been created");

        user
                .is_logged_out()
                .logs_in_with("Jan Plewka", "My S3cret Passw0rd");

        screen
                .shows_in_navbar("Welcome Jan");
    }


}
