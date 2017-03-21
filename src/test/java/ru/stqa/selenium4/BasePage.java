package ru.stqa.selenium4;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

/**
 * Created by Tester on 3/11/2017.
 */
public class BasePage {

    WebDriver driver;
    protected WebDriverWait wait;

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

    public int isSorted (List<WebElement> itemList, String attributeName){
        List<String> itemsNames = new ArrayList<String>();
        List<String> sortedItemsNames = new ArrayList<String>();

        for (WebElement item: itemList) {
            String itemName = item.getAttribute(attributeName);
            itemsNames.add(itemName);
            sortedItemsNames.add(itemName);
        }
        Collections.sort(sortedItemsNames);
        for (int i = 0; i < itemsNames.size(); i++) {
            if(!itemsNames.get(i).equals(sortedItemsNames.get(i))){
                return i;
            }
        }
        return 0;

    }

    public String colorToRGB(String rgba) {
        String delimiters = "[a-z(), ]+";
        String[] colorNum = rgba.split(delimiters);
        String color = Arrays.toString(Arrays.copyOfRange(colorNum, 1, 4));
        color = color.replace("[", "(").replace("]", ")");
        return color;
    }
}
