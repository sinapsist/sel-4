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
    protected By campaignsLocator = By.cssSelector("div#box-campaigns");
    protected By productLocator = By.cssSelector("div#box-campaigns a.link");
    protected By productNameLocator = By.cssSelector("div.name");
    protected By productPriceLocator = By.cssSelector("s.regular-price");
    protected By salesProductPriceLocator = By.cssSelector("strong.campaign-price");

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

    public void compareProductInfo(WebDriver driver){
        WebElement campaigns = driver.findElement(campaignsLocator);

        // As it's only one product WebElement can be used instead
        // But according task requirements it might be more than one product.
        List<WebElement> products = campaigns.findElements(productLocator);

        String nameOfProd = products.get(0).findElement(productNameLocator).getAttribute("textContent");
        String priceOfProd = products.get(0).findElement(productPriceLocator).getAttribute("textContent");
        String salePriceOfProd = products.get(0).findElement(salesProductPriceLocator).getAttribute("textContent");

        String colorOfProductPrice = colorToRGB(products.get(0).findElement(productPriceLocator).getCssValue("color"));
        String fontOfProductPrice = products.get(0).findElement(productPriceLocator).getCssValue("text-decoration");
        String fontSizeOfProductPrice = products.get(0).findElement(productPriceLocator).getCssValue("font-size");
        String colorOfProductSalesPrice = colorToRGB(products.get(0).findElement(salesProductPriceLocator).getCssValue("color"));
        String tagOfProductSalesPrice = products.get(0).findElement(salesProductPriceLocator).getTagName();
        String sizeOfSalesProductPrice = products.get(0).findElement(salesProductPriceLocator).getCssValue("font-size");
        String expectedColorOfProductPrice = "(119, 119, 119)";

        checkProductDetails(colorOfProductPrice, fontOfProductPrice, fontSizeOfProductPrice, colorOfProductSalesPrice, tagOfProductSalesPrice, sizeOfSalesProductPrice, expectedColorOfProductPrice);
        products.get(0).click();

        String nameOfProductOnPage = driver.findElement(By.cssSelector("h1.title")).getAttribute("textContent");
        String productPriceOnPage = driver.findElement(productPriceLocator).getAttribute("textContent");
        String salesProductPriceOnPage = driver.findElement(salesProductPriceLocator).getAttribute("textContent");

        Assert.assertTrue("The name of product on product page is not the same as on main page", nameOfProd.equals(nameOfProductOnPage));
        Assert.assertTrue("The product price of product on product page is not the same as on main page", priceOfProd.equals(productPriceOnPage));
        Assert.assertTrue("The sales price of product on product page is not the same as on main page", salePriceOfProd.equals(salesProductPriceOnPage));

        checkProductDetails(colorOfProductPrice, fontOfProductPrice, fontSizeOfProductPrice, colorOfProductSalesPrice, tagOfProductSalesPrice, sizeOfSalesProductPrice, expectedColorOfProductPrice);
    }

    public void getHomePage(WebDriver driver){
        driver.navigate().to("http://localhost:8081/litecart/en/");
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
        Assert.assertTrue("Expected Home Page but it's not ", driver.getTitle().contains("Online Store"));
    }


    private void checkProductDetails(String colorOfProductPrice, String fontOfProductPrice, String fontSizeOfProductPrice, String colorOfProductSalesPrice, String tagOfProductSalesPrice, String sizeOfSalesProductPrice, String expectedColorOfProductPrice){
        String[] sizeProductStringArray = fontSizeOfProductPrice.split("p");
        String[] sizeSalesProductStringArray = sizeOfSalesProductPrice.split("p");
        float fontSizeProduct = Float.parseFloat(sizeProductStringArray[0]);
        float fontSizeSales = Float.parseFloat(sizeSalesProductStringArray[0]);
        Assert.assertTrue("Color of regular price is not as expected", colorOfProductPrice.equals(expectedColorOfProductPrice));
        Assert.assertTrue("Product price is not crossed", fontOfProductPrice.equals("line-through"));
        Assert.assertTrue("Color of sales price is not red", colorOfProductSalesPrice.equals("(204, 0, 0)"));
        Assert.assertTrue("Font of sales price is not bold", tagOfProductSalesPrice.equals("strong"));
        Assert.assertTrue("Size of sale price is not bigger than regular price", fontSizeProduct < fontSizeSales);

    }


}
