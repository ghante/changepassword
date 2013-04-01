package functional.com.trailblazers.freewheelers;

import functional.com.trailblazers.freewheelers.Screens.LoginScreen;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

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
        driver.get("http://localhost:8080" + HomePage.PATH);
        CreateAccountPage createAccountPage = new HomePage(driver).navigateToCreateAccountPage();
        CreateAccountResultsPage resultsPage = createAccountPage.fillInForm();

        assertThat(resultsPage.getMessage(), containsString("TestUser"));

        driver.get("http://localhost:8080/login");
        LoginScreen.loginAs("TestUser", "password", driver);
        driver.get("http://localhost:8080/");
        assertThat(driver.findElement(By.id("welcome")).getText(), containsString("TestUser"));
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

        public CreateAccountResultsPage fillInForm() {
            driver.findElement(By.id("fld_email")).sendKeys("foo@blam.com");
            driver.findElement(By.id("fld_password")).sendKeys("password");
            driver.findElement(By.id("fld_name")).sendKeys("TestUser");
            driver.findElement(By.id("fld_phoneNumber")).sendKeys("12345678909");
            driver.findElement(By.id("createAccount")).click();
            return new CreateAccountResultsPage(driver);
        }
    }

    private class HomePage {
        public static final String PATH = "/";
        private final WebDriver driver;

        private HomePage(WebDriver driver) {
            this.driver = driver;
        }

        public CreateAccountPage navigateToCreateAccountPage() {
            driver.findElement(By.linkText("Create Account")).click();
            return new CreateAccountPage(driver);
        }
    }

    private class CreateAccountResultsPage {
        public CreateAccountResultsPage(WebDriver driver) {
        }

        public String getMessage() {
            return driver.findElement(By.id("resultMessage")).getText();
        }
    }
}
