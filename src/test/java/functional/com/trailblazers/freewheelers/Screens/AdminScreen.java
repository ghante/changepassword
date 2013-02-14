package functional.com.trailblazers.freewheelers.Screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AdminScreen {
    public static void changeStatus(String newState, WebDriver driver) {
        driver.findElement(By.linkText("Admin Profile")).click();

        Select select = new Select(driver.findElement(By.xpath("//tr/td/select")));
        select.selectByVisibleText(newState);

        driver.findElement(By.xpath("//tbody/tr/td[6]/input[@type='submit']")).click();
        driver.findElement(By.linkText("Admin Profile")).click();
    }
}