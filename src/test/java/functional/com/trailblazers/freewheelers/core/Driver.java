package functional.com.trailblazers.freewheelers.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by rameshb on 1/16/14.
 */
public class Driver {
    private static WebDriver driver;

    public synchronized static WebDriver driver() {
        if (driver != null) {
            try {
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static void quit() {
        driver.quit();
        driver = null;
    }
}
