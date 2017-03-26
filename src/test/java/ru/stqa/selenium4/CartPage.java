package ru.stqa.selenium4;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Tester on 3/26/2017.
 */
public class CartPage extends BasePage {

    // Cart locators

    protected By cartCheckoutLocator = By.cssSelector("#checkout-cart-wrapper p");
    protected By cartTableLocator = By.cssSelector(".dataTable");
    protected By removeButtonLocator = By.name("remove_cart_item");

    public void removeItemsFromCart(int itemsNumber, WebDriver driver) {

        for (int i = 0; i < itemsNumber; i++) {
            driver.findElement(removeButtonLocator).click();
        }
        waitUntilCheckoutRefreshed(driver);

    }

    public void isCartEmpty(WebDriver driver){
        WebElement emptyCart = driver.findElement(cartCheckoutLocator);
        Assert.assertTrue("Cart is not empty", emptyCart.getText().equals("There are no items in your cart."));
    }

    private void waitUntilCheckoutRefreshed(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement cartTable = driver.findElement(cartTableLocator);
        wait.until(ExpectedConditions.stalenessOf(cartTable));
    }
}
