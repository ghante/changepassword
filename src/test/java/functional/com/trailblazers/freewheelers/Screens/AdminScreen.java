package functional.com.trailblazers.freewheelers.Screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AdminScreen {
    public static void changeStatus(WebDriver driver, String newState) {
        driver.findElement(By.linkText("Admin Profile")).click();

        Select select = new Select(driver.findElement(By.xpath("//tr/td/select")));
        select.selectByVisibleText(newState);

        driver.findElement(By.xpath("//tbody/tr/td[6]/button")).click();
    }


    public static String selectedStatusName(WebDriver driver) {
        return driver.findElement(By.xpath("//tr/td/select/option[@selected=\"selected\"]")).getText();
    }

    public static void addNote(WebDriver driver, String note) {
        driver.findElement(By.linkText("Admin Profile")).click();

        WebElement textarea = driver.findElement(By.xpath("//tr/td/textarea"));
        textarea.clear();
        textarea.sendKeys(note);

        driver.findElement(By.xpath("//tbody/tr/td[6]/button")).click();
    }

    public static String getNote(WebDriver driver) {
        return driver.findElement(By.xpath("//tr/td/textarea")).getText();
    }
}