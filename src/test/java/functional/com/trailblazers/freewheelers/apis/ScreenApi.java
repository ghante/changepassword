package functional.com.trailblazers.freewheelers.apis;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScreenApi {
    private WebDriver driver;

    public ScreenApi(WebDriver driver) {
        this.driver = driver;
    }

    public void shows_error(String expectedMessage) {
        expectMessageWithClass(expectedMessage, "alert-error");
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


    public ScreenApi shows_login() {
        String loginUrl = "http://localhost:8080/login";
        String currentUrl = driver.getCurrentUrl();

        assertThat(currentUrl, is(loginUrl));
        return this;
    }

    public ScreenApi shows_profile_for(String name) {
        String userDetails = driver.findElement(By.id("user-details")).getText();

        assertThat(userDetails, containsString(name));
        return this;
    }
}
