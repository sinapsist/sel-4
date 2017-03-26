package ru.stqa.selenium4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Tester on 3/20/2017.
 */
public class Task14 extends TaskBase {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkAddNewCountryLinksNewWindows() {
        log.debug("Go to Login Page");
        LoginPage loginPage = new LoginPage();
        loginPage.Login(driver);
        log.debug("Verify that Login Page loads");
        wait.until(titleIs("My Store"));
        log.debug("Go to Admin Page");
        AdminPage adminPage = new AdminPage();
        log.debug("Click countries category");
        adminPage.getCountries(driver).click();
        log.debug("Click on 'Add New Country' button and verify that all links open in new windows " );
        adminPage.checkNewWindowsOpenForAddCountry(driver);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }



}
