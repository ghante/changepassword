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

public class FrameAcptTest {
	
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
        driver.get("http://localhost:8080/TrailBlazers/frame");
	}
	
	@Test
	public void addingOneFrame() {

		addFrame("frame1", "13.99");
		assertEquals(1, driver.findElements(By.xpath("//table//tbody//tr")).size());
	}

	@Test
	public void addingTwoFrames() {
		addFrame("frame1", "13.99");
		addFrame("frame2", "13.99");
		assertEquals(2, driver.findElements(By.xpath("//table//tbody//tr")).size());
	}
	
	@Test
	public void updatingTwoFrames() {
		addFrame("frame1", "13.99");
		addFrame("frame2", "13.99");
		driver.findElement(By.xpath("//tbody//tr[1]//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//tbody//tr[2]//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//tbody//tr[1]//input[contains(@name, '.name')]")).sendKeys("1");
		driver.findElement(By.xpath("//tbody//tr[2]//input[contains(@name, '.name')]")).sendKeys("2");
		driver.findElement(By.xpath("id('frameGrid')//input[@type='submit']")).click();
		assertEquals("frame11", driver.findElement(By.xpath("//tbody//tr[1]//input[contains(@name, '.name')]")).getAttribute("value"));
		assertEquals("frame22", driver.findElement(By.xpath("//tbody//tr[2]//input[contains(@name, '.name')]")).getAttribute("value"));
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
        addFrame("badFrame", "omgImNotANumber");
        assertTrue(isAlertPresent());
    }

    @Test
    public void shouldDeleteSelectedFrameWhenDeleteIsPressed(){
        addFrame("frame1", "13.99");
        addFrame("frame2", "14.99");
        driver.findElement(By.xpath("//tbody//tr[1]//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("id('frameGrid')//input[@name='delete']")).click();
        assertEquals("frame2", driver.findElement(By.xpath("//tbody//tr[1]//input[contains(@name, '.name')]")).getAttribute("value"));
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

    private void addFrame(String name, String price) {
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.name("price")).sendKeys(price);
		driver.findElement(By.name("description")).sendKeys(name + " is awesome");
		driver.findElement(By.id("frameCommand")).findElement(By.xpath("//input[@type='submit']")).click();
	}

}
