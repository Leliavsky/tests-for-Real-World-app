package com.epam.mentoring.taf.ui;

import com.epam.mentoring.taf.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchingByTagTest extends AbstractTest {

    @Test
    public void uiVerification() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'tag-pill')]")));

        int randomTag = 1 + (int) (Math.random() * 10);
        WebElement tag = driver.findElement(By.xpath("//a[contains(@class,'tag-pill')][" + randomTag + "]"));
        String tagName = tag.getText();
        tag.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='app-article-preview' and not(@hidden)]")));
        String selectedTag = driver.findElement(By.xpath("//a[@class='nav-link active']")).getText();
        Assert.assertEquals(selectedTag, tagName);
    }
}
