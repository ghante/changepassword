package com.thoughtworks.twu.selenium;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserProfileTest {

    static WebDriver driver;

    @BeforeClass
    public static void before(){
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void after(){
        driver.close();
    }

    @Before
    public void setUp() throws SQLException {
        DatabaseTestUtil.clean();
    }

    @Test
    public void userShouldSeeAllOrdersReservedByThem() throws SQLException {
        DatabaseTestUtil.insertIntoItems(1, "framething", "13.44", "meh", "FRAME");
        DatabaseTestUtil.reserveOrder(1, 1, 2, new Date());
        driver.get("http://localhost:8080/trunk/userProfile");
        LoginHelper.loginAs("UserCat", "user", driver);
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/trunk/userProfile"));
        assertEquals("framething", driver.findElement(By.xpath("//tbody//tr[1]//td[1]")).getText());
        assertEquals("13.44", driver.findElement(By.xpath("//tbody//tr[1]//td[2]")).getText());
        assertEquals("meh", driver.findElement(By.xpath("//tbody//tr[1]//td[3]")).getText());
    }


}
