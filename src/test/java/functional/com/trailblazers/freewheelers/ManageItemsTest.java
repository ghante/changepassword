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

public class ManageItemsTest {

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
                .there_is_an_admin("Arno Admin", password_is(SOME_PASSWORD))
                .there_is_no_item("Simplon Pavo 3 Ultra")
                .there_is_no_item("Spoke - Reflectors Arrow red")
                .there_is_no_item("NEW - Simplon Pavo 3 Ultra")
                .there_is_no_item("NEW - Spoke - Reflectors Arrow red");
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

        user
                .creates_an_item(
                        name_is("Simplon Pavo 3 Ultra"),
                        price_is("2899.00"),
                        type_is("FRAME"),
                        description_is("740 g lightweight frame with superb handling and classical aesthetics."),
                        quantity_is("1000")
                );
        screen
                .shows_in_the_list("Simplon Pavo 3 Ultra");

        user
                .creates_an_item(
                        name_is("Spoke - Reflectors Arrow red"),
                        price_is("4.95"),
                        type_is("ACCESSORIES"),
                        description_is("4 x red, curved Arrow shape, screw fastening"),
                        quantity_is("500")
                );

        screen
                .shows_in_the_list("Simplon Pavo 3 Ultra")
                .shows_in_the_list("Spoke - Reflectors Arrow red");

        user
                .changes_item_name(from("Simplon Pavo 3 Ultra"), to("NEW - Simplon Pavo 3 Ultra"))
                .changes_item_name(from("Spoke - Reflectors Arrow red"), to("NEW - Spoke - Reflectors Arrow red"));

        screen
                .shows_in_the_list("NEW - Simplon Pavo 3 Ultra")
                .shows_in_the_list("NEW - Spoke - Reflectors Arrow red");

        user
                .delete_item("NEW - Simplon Pavo 3 Ultra");

        screen
                .shows_in_the_list("NEW - Spoke - Reflectors Arrow red")
                .shows_not_in_the_list("NEW - Simplon Pavo 3 Ultra");
    }


}
