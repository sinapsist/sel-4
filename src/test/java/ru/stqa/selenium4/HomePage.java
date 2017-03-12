package ru.stqa.selenium4;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tester on 3/12/2017.
 */
public class HomePage extends BasePage {

    protected By productItemLocator = By.cssSelector("a.link[title*=Duck]");
    protected By stickerLocator = By.cssSelector("[class^=sticker]");


    public WebDriver isStickerPresent (WebDriver driver){
        driver.navigate().to("http://localhost:8081/litecart/en/");
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
        Assert.assertTrue("Expected Home Page but it's not ", driver.getTitle().contains("Online Store"));

        List<WebElement> productsList = driver.findElements(productItemLocator);

        for (int index = 0; index < productsList.size(); index++) {
            Assert.assertTrue(String.format("%s, has more or less than 1 sticker",
                                        productsList.get(index).getAttribute("href")),
                    (productsList.get(index).findElements(stickerLocator).size() == 1));
        }
        return driver;
    }
}
