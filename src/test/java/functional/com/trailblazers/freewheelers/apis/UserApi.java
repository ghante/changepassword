package functional.com.trailblazers.freewheelers.apis;

import functional.com.trailblazers.freewheelers.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UserApi {

    private WebDriver driver;

    public UserApi(WebDriver driver) {
        this.driver = driver;
    }

    public UserApi is_logged_out() {
        driver.get("http://localhost:8080/logout");
        driver.findElement(By.linkText("Logout")).click();

        return this;
    }

    public UserApi logs_in_with(String userName, String userPassword) {
        driver.get("http://localhost:8080/login");

        TestUtils.wait(driver).until(ExpectedConditions.presenceOfElementLocated(By.name("j_username")));
        driver.findElement(By.name("j_username")).sendKeys(userName);
        driver.findElement(By.name("j_password")).sendKeys(userPassword);

        driver.findElement(By.name("submit")).click();

        return this;
    }

    public UserApi creates_an_account(String email, String password, String name, String phoneNumber) {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Create Account")).click();

        driver.findElement(By.id("fld_email")).sendKeys(email);
        driver.findElement(By.id("fld_password")).sendKeys(password);
        driver.findElement(By.id("fld_name")).sendKeys(name);
        driver.findElement(By.id("fld_phoneNumber")).sendKeys(phoneNumber);

        driver.findElement(By.id("createAccount")).click();

        return this;
    }

    public UserApi visits_his_profile() {
        driver.findElement(By.linkText("User Profile")).click();
        return this;
    }

    public UserApi visits_admin_profile() {
        driver.findElement(By.linkText("Admin Profile")).click();
        return this;
    }

    public UserApi visits_profile_for(String name) {
        driver.get("http://localhost:8080/userProfile/" + encoded(name));
        return this;
    }

    private String encoded(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
