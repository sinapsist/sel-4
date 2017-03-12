package ru.stqa.selenium4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Tester on 3/12/2017.
 */
public class Task8 extends TaskBase {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void loginAdminPanelTest() {
        log.debug("Go to Home Page");
        HomePage homePage = new HomePage();
        log.debug("Verify that Home Page loads");
        log.debug("Check that every item has only one sticker");
        homePage.isStickerPresent(driver);

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;

    }

}
