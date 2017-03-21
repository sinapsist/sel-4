package ru.stqa.selenium4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Tester on 3/11/2017.
 */
public class LoginPage extends BasePage {

    public WebDriver Login(WebDriver driver) {
        driver.navigate().to("http://localhost:8081/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        return driver;
    }


}
