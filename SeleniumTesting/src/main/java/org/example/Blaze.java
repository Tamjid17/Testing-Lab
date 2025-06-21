package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Blaze {
    WebDriver driver;
    ChromeOptions options;

    public ChromeOptions getIncognitoMode() {
        options = new ChromeOptions();
        options.addArguments("--incognito");
        return options;
    }

    public WebDriver setDriver() {
        driver = new ChromeDriver(getIncognitoMode());
        return driver;
    }

    public void loadWebsite() throws InterruptedException {
        driver.get("https://www.demoblaze.com");
        //driver.manage().window().maximize();
        Thread.sleep(1000);
    }

    public void signup() throws InterruptedException {
        driver.findElement(By.id("signin2")).click();
        driver.findElement(By.id("sign-username")).sendKeys("tamjid");
        Thread.sleep(500);
        driver.findElement(By.id("sign-password")).sendKeys("123456");
        Thread.sleep(500);

        driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[1]/button/span")).click();
    }
    public void login() throws InterruptedException {
        driver.findElement(By.id("login2")).click();
        Thread.sleep(500);
        driver.findElement(By.id("loginusername")).sendKeys("tamjid");
        Thread.sleep(500);
        driver.findElement(By.id("loginpassword")).sendKeys("123456");

        driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[normalize-space()='Samsung galaxy s6']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(500);
    }

    public void checkOut() throws InterruptedException {
        driver.findElement(By.xpath("//a[@id='cartur']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[normalize-space()='Place Order']")).click();
        Thread.sleep(500);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/form/div[1]/input")).sendKeys("abc");
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/form/div[2]/input")).sendKeys("Bd");
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/form/div[3]/input")).sendKeys("abc");
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/form/div[4]/input")).sendKeys("abc");
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/form/div[5]/input")).sendKeys("abc");
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/form/div[6]/input")).sendKeys("abc");
        driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();

    }

    public static void main(String[] args) throws InterruptedException {
        Blaze testing = new Blaze();
        testing.setDriver();
        testing.loadWebsite();
        //testing.signup();
        testing.login();
        testing.checkOut();
    }
}
