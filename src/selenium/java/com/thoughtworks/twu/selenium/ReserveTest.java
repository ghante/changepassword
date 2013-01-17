package com.thoughtworks.twu.selenium;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
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
        DatabaseTestUtil.clean();
    }

    private void loginIntoReserveScreen() {
        driver.get("http://localhost:8080/trunk/reserve");
        LoginHelper.loginAs("UserCat", "user", driver);
    }

    @Test
    public void shouldLogOutUserBackToHomePageWhenLogOutLinkIsClicked(){
        loginIntoReserveScreen();
        driver.findElement(By.linkText("Logout")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/trunk/"));
    }

    @Test
    public void shouldShowReservedItemOnReservePage() throws SQLException {
        DatabaseTestUtil.insertIntoItems(111, "frame1", "14.99", "I should see this item", "FRAME");
        //refresh screen
        driver.get("http://localhost:8080/trunk/");
        driver.findElement(By.id("reserve")).click();
        LoginHelper.loginAs("UserCat", "user", driver);
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/trunk/reserve"));
        assertEquals("frame1", driver.findElement(By.xpath("//tbody//tr[1]//td[1]")).getText());
        assertEquals("14.99", driver.findElement(By.xpath("//tbody//tr[1]//td[2]")).getText());
        assertEquals("I should see this item", driver.findElement(By.xpath("//tbody//tr[1]//td[3]")).getText());
        assertEquals("FRAME", driver.findElement(By.xpath("//tbody//tr[1]//td[4]")).getText());

    }
}