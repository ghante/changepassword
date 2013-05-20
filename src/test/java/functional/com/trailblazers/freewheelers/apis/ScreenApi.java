package functional.com.trailblazers.freewheelers.apis;

import functional.com.trailblazers.freewheelers.helpers.ItemTable;
import functional.com.trailblazers.freewheelers.helpers.URLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScreenApi {
    private WebDriver driver;

    public ScreenApi(WebDriver driver) {
        this.driver = driver;
    }

    public void shows_error_alert(String expectedMessage) {
        expectMessageWithClass(expectedMessage, "alert-error");
    }

    public void shows_error(String expectedMessage) {
        expectMessageWithClass(expectedMessage, "text-error");
    }

    public void shows_message(String expectedMessage) {
        expectMessageWithClass(expectedMessage, "alert");
    }

    public void shows_in_navbar(String expectedMessage) {
        expectMessageWithClass(expectedMessage, "navbar-text");
    }

    private ScreenApi expectMessageWithClass(String expectedMessage, String messageClass) {
        String errorMessage = driver.findElement(By.className(messageClass)).getText();

        assertThat(errorMessage, containsString(expectedMessage));
        return this;
    }

    public ScreenApi shows_profile_for(String name) {
        String userDetails = driver.findElement(By.id("user-details")).getText();

        assertThat(userDetails, containsString(name));
        return this;
    }

    public ScreenApi shows_login() {
        assertThat(driver.getCurrentUrl(), is(URLs.login()));
        return this;
    }

    public ScreenApi shows_admin_profile() {
        assertThat(driver.getCurrentUrl(), is(URLs.admin()));
        return this;
    }

    public ScreenApi shows_in_the_list(String name) {
        assertNumberOfRows(name, 1);
        return this;
    }

    public ScreenApi shows_not_in_the_list(String name) {
        assertNumberOfRows(name, 0);
        return this;
    }

    private void assertNumberOfRows(String name, int expectedRows) {
        List<WebElement> elements = driver.findElements(ItemTable.nameFieldFor(name));
        assertThat(elements.size(), is(expectedRows));
    }
}
