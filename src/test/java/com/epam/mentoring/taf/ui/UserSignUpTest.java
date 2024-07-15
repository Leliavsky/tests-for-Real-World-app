package com.epam.mentoring.taf.ui;

import com.epam.mentoring.taf.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserSignUpTest extends AbstractTest {
    private final String username = "Test User";
    private final String email = "test_user@example.com";
    private final String password = "test_password";

    @Test
    public void uiVerification() {
        int uniqueId = (int) (Math.random() * 100);
        String username = this.username + uniqueId;
        String email = this.email.replace("@", "." + uniqueId + "@");

        driver.get(UI_URL);
        driver.findElement(By.xpath("//li/a[normalize-space(text())='Sign up']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(text(),'Sign up')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class,'navbar-nav')]/li[4]/a")));

        String actualUserName = driver.findElement(By.xpath("//ul[contains(@class,'navbar-nav')]/li[4]/a")).getText();
        Assert.assertEquals(actualUserName, username);
    }
}
