package ru.stqa.selenium4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Tester on 3/26/2017.
 */
public class ItemPage extends BasePage {

    // Items locators
    protected By cartQuantityLocator = By.cssSelector("#cart .quantity");
    protected By cartItemSizeLocator = By.cssSelector("select");
    protected By addItemIntoCartLocator = By.cssSelector("td > button");
    protected By cartLocator = By.id("cart");


    public void addItemsToCart(int itemsNumber, WebDriver driver) {
        for (int i = 0; i < itemsNumber; i++) {
            if(driver.findElements(cartItemSizeLocator).size() != 0)  {
                new Select(driver.findElement(cartItemSizeLocator)).selectByValue("Small");
            }
            driver.findElement(addItemIntoCartLocator).click();
            waitUntilCartRefreshed(driver);
        }
        driver.findElement(cartLocator).click();
    }

    private void waitUntilCartRefreshed(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement quantity = driver.findElement(cartQuantityLocator);
        Integer expectedQuantity = Integer.parseInt(quantity.getText()) + 1;
        wait.until(ExpectedConditions.textToBePresentInElement(quantity, expectedQuantity.toString()));
    }
}
