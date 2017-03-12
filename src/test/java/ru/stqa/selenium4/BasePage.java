package ru.stqa.selenium4;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

/**
 * Created by Tester on 3/11/2017.
 */
public class BasePage {

    WebDriver driver;
    private WebDriverWait wait;

    public boolean isElementPresent(WebDriver driver, By locator) {
        boolean isPresent = true;
        try {
                driver.findElement(locator);
            } catch (NoSuchElementException ex) {
                isPresent = false;
            }
            return isPresent;
    }

    public void waitElementVisibility(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
    }

    public void scrolToElement (WebDriver driver, WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
