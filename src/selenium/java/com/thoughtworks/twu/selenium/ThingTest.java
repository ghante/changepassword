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

public class ThingTest {
	
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
	}
	
	@Test
	public void addingOneThing() {
		driver.get("http://localhost:8080/spring-mvc-hibernate-skeleton/thing");
		addThing("thing1");
		assertEquals(1, driver.findElements(By.xpath("//table//tbody//tr")).size());
	}

	@Test
	public void addingTwoThings() {
		driver.get("http://localhost:8080/spring-mvc-hibernate-skeleton/thing");
		addThing("thing1");
		addThing("thing2");
		assertEquals(2, driver.findElements(By.xpath("//table//tbody//tr")).size());
	}
	
	@Test
	public void updatingTwoThings() {
		driver.get("http://localhost:8080/spring-mvc-hibernate-skeleton/thing");
		addThing("thing1");
		addThing("thing2");
		driver.findElement(By.xpath("//tbody//tr[1]//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//tbody//tr[2]//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//tbody//tr[1]//input[contains(@name, '.name')]")).sendKeys("1");
		driver.findElement(By.xpath("//tbody//tr[2]//input[contains(@name, '.name')]")).sendKeys("2");
		driver.findElement(By.xpath("id('thingGrid')//input[@type='submit']")).click();
		assertEquals("thing11", driver.findElement(By.xpath("//tbody//tr[1]//input[contains(@name, '.name')]")).getAttribute("value"));
		assertEquals("thing22", driver.findElement(By.xpath("//tbody//tr[2]//input[contains(@name, '.name')]")).getAttribute("value"));
	}
	
	private void addThing(String name) {
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.name("email")).sendKeys(name + "@domain.com");
		driver.findElement(By.name("description")).sendKeys("31 Some Street\nSome Town");
		driver.findElement(By.id("thingCommand")).findElement(By.xpath("//input[@type='submit']")).click();
	}

}
