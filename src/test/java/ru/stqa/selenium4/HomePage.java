package ru.stqa.selenium4;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium4.Data.User;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tester on 3/12/2017.
 */
public class HomePage extends BasePage {

    // Product locators
    protected By productItemLocator = By.cssSelector("a.link[title*=Duck]");
    protected By stickerLocator = By.cssSelector("[class^=sticker]");
    protected By campaignsLocator = By.cssSelector("div#box-campaigns");
    protected By productLocator = By.cssSelector("div#box-campaigns a.link");
    protected By productNameLocator = By.cssSelector("div.name");
    protected By productPriceLocator = By.cssSelector("s.regular-price");
    protected By salesProductPriceLocator = By.cssSelector("strong.campaign-price");


    // User locators
    protected By createNewUserLocator = By.cssSelector("div#box-account-login a");
    protected By firstNameLocator = By.cssSelector("[name=firstname]");
    protected By lastNameLocator = By.cssSelector("[name=lastname]");
    protected By addressLocator = By.cssSelector("[name=address1]");
    protected By postalCodeLocator = By.cssSelector("[name=postcode]");
    protected By cityLocator = By.cssSelector("[name=city]");
    protected By phoneLocator = By.cssSelector("[name=phone]");
    protected By emailLocator = By.cssSelector("[name=email]");
    protected By passwordLocator = By.cssSelector("[name=password]");
    protected By confirmedPasswordLocator = By.cssSelector("[name=confirmed_password]");
    protected By createAccountButtonLocator = By.cssSelector("[name=create_account]");
    protected By loginButtonLocator = By.cssSelector("[name=login]");
    protected By logoutButtonLocator = By.cssSelector("div.content [href$=logout]");
    protected By countryListLocator = By.cssSelector("select.select2-hidden-accessible");
    protected By countryZoneLocator = By.cssSelector("select[name=zone_code]");

    //Cart items
    protected By cartItemLocator = By.cssSelector("#box-most-popular li");

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

    public User setUpNewUserInfo(){
        String userFirstName = "Tester";
        String userLastName = "Tester";
        String userAddress = "90210 Beverly Hills";
        String userPostalCost = "90210";
        String userCity = "California";
        String userPassword = "Tester";
        String userPhoneNumber = "+1-800-88443";
        String userEmailAddress = String.format("tester%s@gmail.com", getRandomNumber());

        User user =  new User(
                userFirstName,
                userLastName,
                userEmailAddress,
                userAddress,
                userPostalCost,
                userCity,
                userPassword,
                userPhoneNumber);
        return user;
    }

    private int getRandomNumber(){
        Random rand = new Random();
        return rand.nextInt(1000) + 1;
    }

    public void createNewUserAndLogin(WebDriver driver, User user){
        driver.findElement(createNewUserLocator).click();
        driver.findElement(firstNameLocator).sendKeys(user.getFirstName());
        driver.findElement(lastNameLocator).sendKeys(user.getLastName());
        driver.findElement(addressLocator).sendKeys(user.getAddress());
        driver.findElement(postalCodeLocator).sendKeys(user.getPostalCode());
        driver.findElement(cityLocator).sendKeys(user.getCity());

        Select countryList = new Select(driver.findElement(countryListLocator));
        countryList.selectByIndex(224);

        Select stateList = new Select(driver.findElement(countryZoneLocator));
        stateList.selectByIndex(11);

        driver.findElement(phoneLocator).sendKeys(user.getPhoneNumber());
        driver.findElement(emailLocator).sendKeys(user.getEmail());
        driver.findElement(passwordLocator).sendKeys(user.getPassword());
        driver.findElement(confirmedPasswordLocator).sendKeys(user.getPassword());

        driver.findElement(createAccountButtonLocator).click();

        driver.findElement(logoutButtonLocator).click();

        driver.findElement(emailLocator).sendKeys(user.getEmail());
        driver.findElement(passwordLocator).sendKeys(user.getPassword());
        driver.findElement(loginButtonLocator).click();

        driver.findElement(logoutButtonLocator).click();
    }


    public void getItemPage(WebDriver driver){
        driver.findElements(cartItemLocator).get(0).click();
    }









}
