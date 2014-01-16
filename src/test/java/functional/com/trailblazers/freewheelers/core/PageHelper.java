package functional.com.trailblazers.freewheelers.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

/**
 * Created by rameshb on 1/16/14.
 */
public class PageHelper extends Driver {
    public void waitForElement(By by) {
        int maxWait = 30;
        int counter = 0;
        while(counter <= maxWait) {
            try {
                findElement(by);
                break;
            } catch (NoSuchElementException e) {
                sleep(1);
            }
        }
    }

    public WebElement findElement(By by) {
        return driver().findElement(by);
    }

    public boolean isElementExists(By by) {
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
