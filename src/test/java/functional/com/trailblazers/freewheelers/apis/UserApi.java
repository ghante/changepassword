package functional.com.trailblazers.freewheelers.apis;

import functional.com.trailblazers.freewheelers.TestUtils;
import functional.com.trailblazers.freewheelers.helpers.URLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UserApi {

    private WebDriver driver;

    public UserApi(WebDriver driver) {
        this.driver = driver;
    }

    public UserApi is_logged_out() {
        driver.get(URLs.logout());
        driver.findElement(By.linkText("Logout")).click();

        return this;
    }

    public UserApi logs_in_with(String userName, String userPassword) {
        driver.get(URLs.login());

        TestUtils.wait(driver).until(ExpectedConditions.presenceOfElementLocated(By.name("j_username")));
        driver.findElement(By.name("j_username")).sendKeys(userName);
        driver.findElement(By.name("j_password")).sendKeys(userPassword);

        driver.findElement(By.name("submit")).click();

        return this;
    }

    public UserApi creates_an_account(String email, String password, String name, String phoneNumber) {
        driver.get(URLs.home());
        driver.findElement(By.linkText("Create Account")).click();

        fillField("fld_email", email);
        fillField("fld_password", password);
        fillField("fld_name", name);
        fillField("fld_phoneNumber", phoneNumber);

        driver.findElement(By.id("createAccount")).click();

        return this;
    }

    public UserApi creates_an_item(String name, String price, String type, String description, String quantity) {
        fillField("name", name);
        fillField("price", price);

        Select select = new Select(driver.findElement(By.id("type")));
        select.selectByVisibleText(type);

        fillField("description", description);
        fillField("quantity", quantity);

        driver.findElement(By.id("createItem")).click();

        return this;
    }

    private void fillField(String id, String value) {
        WebElement field = driver.findElement(By.id(id));

        field.clear();
        field.sendKeys(value);
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
        driver.get(URLs.userProfile() + "/" + encoded(name));
        return this;
    }

    public UserApi wants_to_manage_items() {
        driver.get(URLs.admin());
        driver.findElement(By.id("manageItems")).click();
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
