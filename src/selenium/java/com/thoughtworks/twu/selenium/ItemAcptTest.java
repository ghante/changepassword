package com.thoughtworks.twu.selenium;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ItemAcptTest {
	
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
        driver.get("http://localhost:8080/TrailBlazers/item");
	}
	
	@Test
	public void addingOneItem() {

		addAFrame("item1", "13.99");
		assertEquals(1, driver.findElements(By.xpath("//table//tbody//tr")).size());
	}

	@Test
	public void addingTwoItems() {
		addAFrame("item1", "13.99");
		addAFrame("item2", "13.99");
		assertEquals(2, driver.findElements(By.xpath("//table//tbody//tr")).size());
	}
	
	@Test
	public void updatingTwoItems() {
		addAFrame("item1", "13.99");
		addAFrame("item2", "13.99");
		driver.findElement(By.xpath("//tbody//tr[1]//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//tbody//tr[2]//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//tbody//tr[1]//input[contains(@name, '.name')]")).sendKeys("1");
		driver.findElement(By.xpath("//tbody//tr[2]//input[contains(@name, '.name')]")).sendKeys("2");
		driver.findElement(By.xpath("id('itemGrid')//input[@type='submit']")).click();
		assertEquals("item11", driver.findElement(By.xpath("//tbody//tr[1]//input[contains(@name, '.name')]")).getAttribute("value"));
		assertEquals("item22", driver.findElement(By.xpath("//tbody//tr[2]//input[contains(@name, '.name')]")).getAttribute("value"));
	}

    @Test
    public void  shouldTakeUserToHomecreen(){
        driver.findElement(By.linkText("Home")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/TrailBlazers/"));
    }

    @Test
    public void  shouldTakeUserToAdminScreen(){
        driver.findElement(By.linkText("Admin")).click();
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/TrailBlazers/admin"));
    }

    @Test
    public void shouldShowAlertWhenNonNumberPriceIsEntered(){
        addAFrame("badItem", "omgImNotANumber");
        assertTrue(isAlertPresent());
    }

    @Test
    public void shouldDeleteSelectedItemWhenDeleteIsPressed(){
        addAFrame("item1", "13.99");
        addAFrame("item2", "14.99");
        driver.findElement(By.xpath("//tbody//tr[1]//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("id('itemGrid')//input[@name='delete']")).click();
        assertEquals("item2", driver.findElement(By.xpath("//tbody//tr[1]//input[contains(@name, '.name')]")).getAttribute("value"));
    }

    private boolean isAlertPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            return true;
        }
        catch (NoAlertPresentException e){
            return false;
        }
    }

    private void addAFrame(String name, String price) {
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.name("price")).sendKeys(price);
		driver.findElement(By.name("description")).sendKeys(name + " is awesome");
        Select itemType = new Select(driver.findElement(By.id("type")));
        itemType.selectByVisibleText("FRAME");
		driver.findElement(By.id("itemCommand")).findElement(By.xpath("//input[@type='submit']")).click();
	}

}
