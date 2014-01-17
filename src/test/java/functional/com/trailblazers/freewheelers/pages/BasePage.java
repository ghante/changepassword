package functional.com.trailblazers.freewheelers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by rameshb on 1/16/14.
 */
public class BasePage {
    private static WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElement(By by) {
        int maxWait = 30;
        int counter = 0;
        while (counter <= maxWait) {
            try {
                findElement(by);
                break;
            } catch (NoSuchElementException e) {
                sleep(1);
            }
        }
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public boolean elementExists(By by) {
        try {
            findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void sleep(long timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (Exception e) {
            // Eat
        }
    }
}
