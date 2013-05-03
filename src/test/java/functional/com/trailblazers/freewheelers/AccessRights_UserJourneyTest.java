package functional.com.trailblazers.freewheelers;

import functional.com.trailblazers.freewheelers.apis.AdminApi;
import functional.com.trailblazers.freewheelers.apis.ScreenApi;
import functional.com.trailblazers.freewheelers.apis.UserApi;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static functional.com.trailblazers.freewheelers.SyntaxSugar.SOME_PASSWORD;
import static functional.com.trailblazers.freewheelers.SyntaxSugar.password_is;

public class AccessRights_UserJourneyTest {

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
    public void testAccessRights() throws Exception {
        admin
                .there_is_a_user("Hugo Huser", password_is(SOME_PASSWORD))
                .there_is_an_admin("Arno Admin", password_is(SOME_PASSWORD));

        user
                .is_logged_out()
                .visits_his_profile();
        screen
                .shows_login();

        user
                .logs_in_with("Hugo Huser", password_is(SOME_PASSWORD))
                .visits_his_profile();
        screen
                .shows_profile_for("Hugo Huser");

        user
                .visits_admin_profile();
        screen
                .shows_error("access is denied");

        user
                .logs_in_with("Arno Admin", password_is(SOME_PASSWORD))
                .visits_admin_profile();
        screen
                .shows_admin_profile();

        user
                .visits_profile_for("Hugo Huser");
        screen
                .shows_profile_for("Hugo Huser");
    }




}
