package ru.stqa.selenium4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium4.Data.Product;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Tester on 3/20/2017.
 */
public class Task13 extends TaskBase {
    private WebDriver driver;
    private WebDriverWait wait;
    private final int CART_ITEMS_NUMBER = 3;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void addItemsInCart() {
        log.debug("Go to Home Page");
        HomePage homePage = new HomePage();
        log.debug("Verify that Home Page loads");
        homePage.getHomePage(driver);
        log.debug(String.format("Add %s items into cart", CART_ITEMS_NUMBER));
        homePage.getItemPage(driver);
        ItemPage itemPage = new ItemPage();
        itemPage.addItemsToCart(CART_ITEMS_NUMBER , driver);
        log.debug(String.format("Remove %s items from cart", CART_ITEMS_NUMBER));
        CartPage cartPage = new CartPage();
        cartPage.removeItemsFromCart(CART_ITEMS_NUMBER , driver);
        log.debug("Verify that cart is empty");
        cartPage.isCartEmpty(driver);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
