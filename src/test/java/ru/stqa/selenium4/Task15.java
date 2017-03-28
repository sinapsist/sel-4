package ru.stqa.selenium4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tester on 3/26/2017.
 */
public class Task15 extends TaskBase{
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        try {
            DesiredCapabilities capabilites = DesiredCapabilities.internetExplorer();
            capabilites.setPlatform(Platform.WIN10);
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilites);
            wait = new WebDriverWait(driver, 10);
        }
        catch (MalformedURLException e)
        {}
    }

    @Test
    public void seleniumStandaloneTest() {
        driver.navigate().to("http://software-testing.ru/");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
