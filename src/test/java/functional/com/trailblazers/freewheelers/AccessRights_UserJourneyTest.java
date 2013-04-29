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
    public void testAccessRight() throws Exception {
        admin
                .there_is_a_user("Hugo Huser", password_is(SOME_PASSWORD));

        // can't access profile without loging

        // can access user profile with login

        // can't access admin profile when logged in as user

        // can access admin profile when logged in as admin

        // admin can access user profile


    }


}
