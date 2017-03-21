package ru.stqa.selenium4;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tester on 3/19/2017.
 */
public class CountriesPage extends BasePage {
    protected By countriesListLocator = By.cssSelector("tr.row a:not([title=Edit])");
    protected By lineLocator = By.cssSelector("a:not([title=Edit])");
    protected By linesLocator = By.cssSelector("tr.row");
    protected By zoneLocator = By.cssSelector("td:nth-child(6)");
    protected By zoneListLocator = By.cssSelector("[type=hidden][name*=name]");

    public void isCountriesInAlphabetOrder(WebDriver driver){
        List<WebElement> countriesList = driver.findElements(countriesListLocator);

        Assert.assertTrue(String.format("Countries are not alphabetically sorted. Country # %s is on wrong place.",
                isSorted(countriesList, "textContent")), isSorted(countriesList, "textContent") ==0);
    }

    public void isZoneNumberMoreThanZero(WebDriver driver){
        List<WebElement> countriesLinesList = driver.findElements(linesLocator);

        for (int i=0; i<countriesLinesList.size(); i++){
            countriesLinesList = driver.findElements(linesLocator);
            WebElement countryLine = countriesLinesList.get(i);
            String zonesNumber = countryLine.findElement(zoneLocator).getAttribute("textContent");

            if (!zonesNumber.equals("0")) {
                countryLine.findElement(lineLocator).click();
                List<WebElement> zonesList = driver.findElements(zoneListLocator);

                Assert.assertTrue(String.format("Zones are not alphabetically sorted. Zone # %s is on wrong place.",
                        isSorted(zonesList, "value")), isSorted(zonesList, "textContent") ==0);

                if (driver.findElement(By.cssSelector("h1")).getAttribute("outerText").equals(" Edit Country")) {
                    driver.findElement(By.cssSelector("li.selected span.name")).click();
                }

            }
        }
    }

    /**
     * Returns line number when it's not in Alphabet order, or 0 if it's in order
     * @param driver
     */
    public void isZonesInAlphabetOrder(WebDriver driver){
        List<WebElement> zonesLinesList = driver.findElements(linesLocator);

        for (int i=0; i<zonesLinesList.size(); i++){
            zonesLinesList = driver.findElements(linesLocator);
            WebElement zoneLine = zonesLinesList.get(i);
            zoneLine.findElement(lineLocator).click();

            List<WebElement> geoList = driver.findElements(By.cssSelector("select[name*=zone_code] option[selected=selected]"));

            Assert.assertTrue(String.format("Zones are not alphabetically sorted. Zones # %s is on wrong place.",
                    isSorted(geoList, "textContent")), isSorted(geoList, "textContent") ==0);

            driver.findElement(By.cssSelector("li.selected span.name")).click();
        }

    }


}
