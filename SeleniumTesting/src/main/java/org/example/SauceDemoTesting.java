package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SauceDemoTesting {

    WebDriver driver;
    ChromeOptions options;

    public ChromeOptions getIncognitoMode() {
        options = new ChromeOptions();
        options.addArguments("--incognito");
        return options;
    }

    public void setDriver() {
        driver = new ChromeDriver(getIncognitoMode());
    }

    public void loadWebsite() throws InterruptedException {
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    public void login() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);
    }

    public void selectBackpack() throws InterruptedException {
        driver.findElement(By.id("item_4_title_link")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("back-to-products")).click();
        Thread.sleep(3000);
    }

    public void selectCheckout() throws InterruptedException {
        driver.findElement(By.id("shopping_cart_container")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(3000);
    }

    public void fillCheckoutForm() throws InterruptedException {
        driver.findElement(By.id("first-name")).sendKeys("Tamjid");
        driver.findElement(By.id("last-name")).sendKeys("Islam");
        driver.findElement(By.id("postal-code")).sendKeys("1207");
        Thread.sleep(2000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
    }

    public void exit() throws InterruptedException {
        driver.close();
    }
}
