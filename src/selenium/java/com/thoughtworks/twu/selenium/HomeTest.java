package com.thoughtworks.twu.selenium;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
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
        logout();
        Database.clean();
        driver.get("http://localhost:8080/TrailBlazers/");
    }

    private void logout() {
        driver.get("http://localhost:8080/TrailBlazers/logout");
        driver.findElement(By.linkText("Logout")).click();
    }

    @Test
    public void  shouldTakeUserToHomeScreen(){
        driver.findElement(By.linkText("Home")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/TrailBlazers/"));
    }

    @Test
    public void  shouldTakeUserToLoginScreen(){
        driver.findElement(By.linkText("Admin")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/TrailBlazers/login"));
    }

    @Test
    public void shouldShowListOfItemsOnHomeScreen() throws SQLException {
        Database.insertIntoItems("frame1","14.99","I should see this item", "FRAMES");
        //refresh screen
        driver.get("http://localhost:8080/TrailBlazers/");
        assertEquals("frame1", driver.findElement(By.xpath("//tbody//tr[1]//td[1]")).getText());
        assertEquals("14.99", driver.findElement(By.xpath("//tbody//tr[1]//td[2]")).getText());
        assertEquals("I should see this item", driver.findElement(By.xpath("//tbody//tr[1]//td[3]")).getText());
        assertEquals("FRAMES", driver.findElement(By.xpath("//tbody//tr[1]//td[4]")).getText());
    }
}
