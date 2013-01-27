package com.thoughtworks.university.selenium.Screens;

import com.thoughtworks.university.selenium.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginScreen {

    public static void loginAs(String username, String password, WebDriver driver) {
        TestUtils.wait(driver).until(ExpectedConditions.presenceOfElementLocated(By.name("j_username")));
        driver.findElement(By.name("j_username")).sendKeys(username);
        driver.findElement(By.name("j_password")).sendKeys(password);
        submitForm(driver);
    }

    private static void submitForm(WebDriver driver) {
        driver.findElement(By.name("submit")).click();
    }

}
