package com.thoughtworks.twu.selenium;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class HomeTest {
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
        driver.get("http://localhost:8080/TrailBlazers/");
    }

    @Test
    public void shouldShowWelcomeScreenAtStartup() {
        WebElement bodyTag = driver.findElement(By.className("heading"));
        assertTrue(bodyTag.getText().contains("Welcome to ThoughtWorks University !"));
    }

    @Test
    public void  shouldTakeUserToFramePageFromHomeScreen(){
        driver.findElement(By.linkText("Frame")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/TrailBlazers/frame"));
    }

}
