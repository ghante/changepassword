package com.thoughtworks.twu.selenium;

import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

		addItem("item1", "13.99");
		assertEquals(1, driver.findElements(By.xpath("//table//tbody//tr")).size());
	}

	@Test
	public void addingTwoItems() {
		addItem("item1", "13.99");
		addItem("item2", "13.99");
		assertEquals(2, driver.findElements(By.xpath("//table//tbody//tr")).size());
	}
	
	@Test
	public void updatingTwoItems() {
		addItem("item1", "13.99");
		addItem("item2", "13.99");
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
        addItem("badItem", "omgImNotANumber");
        assertTrue(isAlertPresent());
    }

    @Test
    public void shouldDeleteSelectedItemWhenDeleteIsPressed(){
        addItem("item1", "13.99");
        addItem("item2", "14.99");
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

    private void addItem(String name, String price) {
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.name("price")).sendKeys(price);
		driver.findElement(By.name("description")).sendKeys(name + " is awesome");
		driver.findElement(By.id("itemCommand")).findElement(By.xpath("//input[@type='submit']")).click();
	}

}
