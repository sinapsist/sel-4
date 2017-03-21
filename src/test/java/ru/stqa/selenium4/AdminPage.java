package ru.stqa.selenium4;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Tester on 3/11/2017.
 */
public class AdminPage extends BasePage {
    protected By headerLocator = By.cssSelector("h1");
    protected By menuItemLocator = By.cssSelector("li#app-");
    protected String MENU_ITEM_STRING = "li#app-:nth-child(%s)";
    protected By subMenyItemLocator = By.cssSelector("[id^=doc-]");
    protected String SUB_MENU_ITEM_STRING = "[id^=doc-]:nth-child(%s)";
    protected String COUNTRIES_LOCATOR_STRING = "a[href='http://localhost:8081/litecart/admin/?app=countries&doc=countries']";
    protected String ZONES_LOCATOR_STRING = "a[href='http://localhost:8081/litecart/admin/?app=geo_zones&doc=geo_zones']";

    public WebDriver CheckElemenetsInMenu(WebDriver driver) {
        List<WebElement> menuList = driver.findElements(menuItemLocator);

        for (int index = 1; index < menuList.size() + 1; index++) {
            WebElement menuItem = driver.findElement(By.cssSelector(String.format(MENU_ITEM_STRING, index)));
            menuItem.click();
            Assert.assertTrue(isElementPresent(driver, headerLocator));
            List<WebElement> subMenuList = driver.findElements(subMenyItemLocator);
            if (subMenuList.size() > 0) {
                for (int subindex = 1; subindex < subMenuList.size() + 1; subindex++) {
                    WebElement subMenuItem = driver.findElement(By.cssSelector(String.format(SUB_MENU_ITEM_STRING, subindex)));
                    subMenuItem.click();
                    Assert.assertTrue(isElementPresent(driver, headerLocator));
                }
            }
        }
    return driver;
    }

    public WebElement getCountries (WebDriver driver) {
        return driver.findElement(By.cssSelector(COUNTRIES_LOCATOR_STRING));

    }

    public WebElement getZones (WebDriver driver) {
        return driver.findElement(By.cssSelector(ZONES_LOCATOR_STRING));

    }

}
