package com.epam.mentoring.taf;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

abstract public class AbstractTest {

    protected final static String baseUrl = "https://angular-realworld.netlify.app/";
    protected final static String UI_URL = "https://angular-realworld.netlify.app/";
    protected final static String API_URL = "https://api.realworld.io";
    protected WebDriver driver;
    protected WebDriverWait wait;
    private boolean shouldRunBrowser = true;
    public void setShouldRunBrowser(boolean shouldRunBrowser) {
        this.shouldRunBrowser = shouldRunBrowser;
    }

    @BeforeMethod
    public void initialisation() throws MalformedURLException {
        if (shouldRunBrowser) {
            String gridUrl = System.getProperty("grid.url");

        if (gridUrl == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            driver = new RemoteWebDriver(new URL(gridUrl), DesiredCapabilities.chrome());
        }

            driver.manage().window().maximize();
            driver.get(baseUrl);
            wait = new WebDriverWait(driver, 2);
        }
    }

    @AfterMethod
    public void terminate() {
        if (shouldRunBrowser && driver != null) {
            driver.quit();
        }
    }
}
