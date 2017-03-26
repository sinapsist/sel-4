package ru.stqa.selenium4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium4.Data.Product;
import ru.stqa.selenium4.Data.User;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Tester on 3/20/2017.
 */
public class Task12 extends TaskBase{
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void createNewProduct() {
        log.debug("Go to Login Page");
        LoginPage loginPage = new LoginPage();
        loginPage.Login(driver);
        log.debug("Verify that Login Page loads");
        wait.until(titleIs("My Store"));
        log.debug("Go to Admin Page");
        AdminPage adminPage = new AdminPage();
        log.debug("Click on Category item and add new item");
        Product product = adminPage.setUpProduct();
        adminPage.createNewProduct(driver, product);
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;

    }
}

