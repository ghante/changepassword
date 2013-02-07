package functional.com.trailblazers.freeriders;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class CreateAccountTest {
    static WebDriver driver;

    @BeforeClass
    public static void before() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void whenTestClassIsComplete() {
        driver.close();
    }

    @Test
    public void testRouteToCreateAccountPage() {
        driver.get("http://localhost:8080" + CreateAccountPage.PATH);
        new CreateAccountPage(driver);
    }

    private class CreateAccountPage {
        public static final String PATH = "/account/create";
        private final WebDriver driver;

        public CreateAccountPage(WebDriver driver) {
            this.driver = driver;

            Wait wait = new FluentWait(driver)
                    .withTimeout(5, TimeUnit.SECONDS)
                    .pollingEvery(1, TimeUnit.SECONDS);

            wait.until(ExpectedConditions.titleIs("Create Account"));
        }
    }
}
