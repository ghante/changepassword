package functional.com.thoughtworks.university;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtils {
    public static boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }

    public static WebDriverWait wait (WebDriver driver){
        return new WebDriverWait(driver, 10);
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            return true;
        }
        catch (NoAlertPresentException e){
            return false;
        }
    }

    public static void selectFromDropDown(WebDriver driver, String elementId, String dropDownItem) {
        Select itemType = new Select(driver.findElement(By.id(elementId)));
        itemType.selectByVisibleText(dropDownItem);
    }


}
