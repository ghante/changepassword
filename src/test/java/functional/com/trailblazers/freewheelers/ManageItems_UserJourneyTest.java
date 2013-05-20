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

public class ManageItems_UserJourneyTest {

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
    public void shouldCreateAndUpdateItems() throws Exception {
        admin
                .there_is_an_admin("Arno Admin", password_is(SOME_PASSWORD));
        user
                .logs_in_with("Arno Admin", password_is(SOME_PASSWORD))
                .wants_to_manage_items();

        user
                .creates_an_item(
                        name_is("Simplon Pavo 3 Ultra"),
                        price_is("2899.00"),
                        type_is("FRAME"),
                        description_is("740 g lightweight frame with superb handling and classical aesthetics."),
                        quantity_is(EMPTY)
                );
        screen
                .shows_error("Please enter Item Quantity");

        // create good item
        // it is shown in the list

        // create second item
        // both shown in the list

        // update one - bad info
        // show error

        // update two
        // changes made

        // delete one
        // one only shown
    }


}
