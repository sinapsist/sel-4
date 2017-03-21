package ru.stqa.selenium4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Tester on 3/20/2017.
 */
public class Task10 extends TaskBase {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void verifyCampaignsProductDetails() {
        log.debug("Go to Home Page");
        HomePage homePage = new HomePage();
        log.debug("Verify that Home Page loads");
        homePage.getHomePage(driver);
        log.debug("Check first Campaigns product details info");
        homePage.compareProductInfo(driver);
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;

    }

}
