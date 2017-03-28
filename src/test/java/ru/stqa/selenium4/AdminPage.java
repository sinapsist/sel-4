package ru.stqa.selenium4;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium4.Data.Product;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

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


    // Product locators
    protected By catalogLocator = By.cssSelector("li#app-:nth-child(2)");
    protected By addNewProductButtonLocator = By.cssSelector("a.button:nth-child(2)");
    protected By radioButtonEnableLocator = By.cssSelector("label:nth-child(3)");
    protected By productNameFieldLocator = By.cssSelector("[name^=name]");
    protected By productCodeFieldLocator = By.cssSelector("[name=code]");
    protected By categoriesDuckLocator = By.cssSelector("[data-name*=Ducks]");
    protected By defaultCategoryLocator  = By.cssSelector("[name=default_category_id]");
    protected By productGroupLocator = By.cssSelector("[name^=product_groups]");
    protected By productQuantityLocator = By.cssSelector("[name=quantity]");
    protected By productSoldOutStatusLocator = By.cssSelector("[name=sold_out_status_id]");
    protected By productPictureLocator = By.cssSelector("[type=file]");
    protected By productValidFromLocator = By.cssSelector("[name=date_valid_from]");
    protected By productValidToLocator = By.cssSelector("[name=date_valid_to]");
    protected By productInfoLocator = By.cssSelector("[href*=information]");
    protected By productManufactureIdLocator = By.cssSelector("[name=manufacturer_id]");
    protected By productShortDescriptionLocator = By.cssSelector("[name*=short_description]");
    protected By productLongDescription = By.cssSelector("div.trumbowyg-editor");
    protected By productHeadTitleLocator =  By.cssSelector("[name*=head_title]");
    protected By productMetaDescriptionLocator = By.cssSelector("[name*=meta_description]");
    protected By productButtonPriceLocator = By.cssSelector("[href*=tab-prices]");
    protected By productPriceFieldLocator = By.cssSelector("[name=purchase_price]");
    protected By productPriceCurrencyCode = By.cssSelector("[name=purchase_price_currency_code]");
    protected By productSalesPriceFieldLocator = By.cssSelector("[name*=gross_prices][name*=USD]");
    protected By saveButtonLocator = By.cssSelector("[name=save]");

    // Add country locators
    protected By addCountryButtonLocator = By.cssSelector("a.button");
    protected By AddCountryLinksLocator = By.cssSelector(".fa-external-link");

    // Catalog items
    protected By rubberDuckLocator = By.cssSelector("td:nth-child(3) > a");
    protected By duckCatalogItemsLocatator = By.cssSelector("tr.row [href*=edit]:not([title=Edit])");

    public void createNewProduct (WebDriver driver, Product product){
        driver.findElement(catalogLocator).click();
        driver.findElement(addNewProductButtonLocator).click();
        driver.findElement(radioButtonEnableLocator).click();
        driver.findElement(productNameFieldLocator).sendKeys(product.getProductName());
        driver.findElement(productCodeFieldLocator).sendKeys(product.getProductCode());
        driver.findElement(categoriesDuckLocator).click();

        Select rootList = new Select(driver.findElement(defaultCategoryLocator));
        rootList.selectByIndex(product.getProductId());

        List<WebElement> gender = driver.findElements(productGroupLocator);
        gender.get(product.getProductGroup()).click();

        driver.findElement(productQuantityLocator).clear();
        driver.findElement(productQuantityLocator).sendKeys(String.valueOf(product.getQuantity()));

        Select status = new Select(driver.findElement(productSoldOutStatusLocator));
        status.selectByIndex(0);

        File picturePath = new File("src/images/test.png");
        driver.findElement(productPictureLocator).sendKeys(picturePath.getAbsolutePath());

        DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");

        driver.findElement(productValidFromLocator).sendKeys(dateFormat.format(product.getValidFrom()));
        driver.findElement(productValidToLocator).sendKeys(dateFormat.format(product.getValidTo()));

        driver.findElement(productInfoLocator).click();

        Select manufacturer = new Select(driver.findElement(productManufactureIdLocator));
        manufacturer.selectByIndex(product.getManufactureId());

        driver.findElement(productShortDescriptionLocator).sendKeys(product.getShortDescription());
        driver.findElement(productLongDescription).sendKeys(product.getLongDescription());
        driver.findElement(productHeadTitleLocator).sendKeys(product.getHeadTitle());
        driver.findElement(productMetaDescriptionLocator).sendKeys(product.getMetaDescription());

        driver.findElement(productButtonPriceLocator).click();

        driver.findElement(productPriceFieldLocator).clear();
        driver.findElement(productPriceFieldLocator).sendKeys(String.valueOf(product.getPurchasePrice()));

        Select currency = new Select(driver.findElement(productPriceCurrencyCode));
        currency.selectByIndex(1);

        driver.findElement(productSalesPriceFieldLocator).clear();
        driver.findElement(productSalesPriceFieldLocator).sendKeys(String.valueOf(product.getSalesPrice()));

        driver.findElement(saveButtonLocator).click();

    }

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

    public Product setUpProduct() {
        SimpleDateFormat dateformat = new SimpleDateFormat("MMddyyyy");
        Product product = null;
        try {

            String productName = "test";
            String productCode = "test";
            int productId = 1;
            int productGroup = 0;
            int quantity = 10;

            Date validFrom = dateformat.parse("01022017");
            Date validTo = dateformat.parse("11022017");

            int manufactureId = 1;
            String shortDescription = "test";
            String longDescription = "test";
            String headTitle = "test";
            String metaDescription = "test";
            double purchasePrice = 10.0;
            double salesPrice = 20.0;


            product = new Product(productName,
                    productCode,
                    productId,
                    productGroup,
                    quantity,
                    validFrom,
                    validTo,
                    manufactureId,
                    shortDescription,
                    longDescription,
                    headTitle,
                    metaDescription,
                    purchasePrice,
                    salesPrice
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void checkNewWindowsOpenForAddCountry(WebDriver driver) {
        driver.findElement(addCountryButtonLocator).click();
        List<WebElement> links = driver.findElements(AddCountryLinksLocator);
        WebDriverWait wait = new WebDriverWait(driver, 5);

        String mainWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        for (WebElement link : links) {
            link.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(oldWindows.size() + 1));
            driver.switchTo().window(getNewWindowHandle(oldWindows, driver));
            driver.close();
            driver.switchTo().window(mainWindow);
        }

    }

    private String getNewWindowHandle(Set<String> oldWindows, WebDriver driver) {
        Set<String> handles = driver.getWindowHandles();
        handles.removeAll(oldWindows);
        return handles.isEmpty() ? null : handles.iterator().next();
    }


    public WebElement getCatalog (WebDriver driver) {
        return driver.findElement(catalogLocator);
    }


    public void isConsoleLogs(WebDriver driver) {
        LogEntries logs;
        driver.findElement(rubberDuckLocator).click();
        List<WebElement> productList = driver.findElements(duckCatalogItemsLocatator);

        for (int i = 0; i < productList.size(); i++) {
            productList = driver.findElements(duckCatalogItemsLocatator);
            productList.get(i).click();
            driver.navigate().back();
            logs = driver.manage().logs().get("browser");
            Assert.assertTrue("Something found in the log!", logs.getAll().isEmpty());
        }
    }

}
