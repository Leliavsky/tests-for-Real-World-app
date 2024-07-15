package com.epam.mentoring.taf.ui;

import com.epam.mentoring.taf.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserSignInTest extends AbstractTest {
    private final String username = "tom_marvolo";
    private final String email = "tom_marvolo@example.com";
    private final String password = "Voldemort";

    @Test
    public void uiVerification() {
        driver.findElement(By.xpath("//li/a[normalize-space(text())='Sign in']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class,'navbar-nav')]/li[4]/a")));
        String actualUserName = driver.findElement(By.xpath("//ul[contains(@class,'navbar-nav')]/li[4]/a")).getText();

        Assert.assertEquals(actualUserName, username);
    }

    @Test
    public void uiNegativeVerification() {
        driver.findElement(By.xpath("//li/a[text()=' Sign in ']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("wrong_password");
        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='error-messages']/li")));
        String actualUserName = driver.findElement(By.xpath("//ul[@class='error-messages']/li")).getText();
        Assert.assertEquals(actualUserName, "email or password is invalid");
    }
}
