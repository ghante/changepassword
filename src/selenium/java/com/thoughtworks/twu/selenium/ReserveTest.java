package com.thoughtworks.twu.selenium;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class ReserveTest {

    static WebDriver driver;

    @BeforeClass
    public static void before() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void after() {
        driver.close();
    }

    @Before
    public void setup() throws SQLException {
        Database.clean();
        loginIntoReserveScreen();
    }

    private void loginIntoReserveScreen() {
        driver.get("http://localhost:8080/trunk/reserve");
        LoginHelper.loginAs("UserCat", "user", driver);
    }

    @Test
    public void shouldLogOutUserBackToHomePageWhenLogOutLinkIsClicked(){
        driver.findElement(By.linkText("Logout")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/trunk/"));
    }
}
