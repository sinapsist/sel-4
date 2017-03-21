package ru.stqa.selenium4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Tester on 3/19/2017.
 */
public class Task9 extends TaskBase {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void loginAdminPanelTest() {
        log.debug("Go to Login Page");
        LoginPage loginPage = new LoginPage();
        loginPage.Login(driver);
        log.debug("Verify that Login Page loads");
        wait.until(titleIs("My Store"));
        log.debug("Go to Admin Page");
        AdminPage adminPage = new AdminPage();
        log.debug("Click countries category");
        adminPage.getCountries(driver).click();
        log.debug("Verify that every item in a list in alphabet order");
        CountriesPage countriesPage = new CountriesPage();
        countriesPage.isCountriesInAlphabetOrder(driver);
        countriesPage.isZoneNumberMoreThanZero(driver);
        adminPage.getZones(driver).click();
        countriesPage.isZonesInAlphabetOrder(driver);
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;

    }

}

