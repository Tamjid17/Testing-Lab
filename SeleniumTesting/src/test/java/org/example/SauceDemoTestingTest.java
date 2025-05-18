package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SauceDemoTestingTest {
    SauceDemoTesting testing;
    WebDriver driver;
    @BeforeAll
    static void setDriveProperty() {
        System.setProperty("webdriver.chrome.driver", "C:\\Browser Driver\\chromedriver.exe");
    }

    @BeforeEach
    void setup() throws InterruptedException {
        testing = new SauceDemoTesting();
        driver = testing.setDriver();
        testing.loadWebsite();
    }

    @Test
    @Order(1)
    void testSuccessfulLogin() throws InterruptedException {
        testing.login("standard_user", "secret_sauce");

        assertTrue(driver.getCurrentUrl().contains("inventory"), "User should be redirected to inventory page");
    }

    @Test
    @Order(2)
    void testUnsuccessfulLogin() throws InterruptedException  {
        testing.login("standard_use", "secret_sauce");
        String error = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        assertTrue(error.contains("Username and password do not match any user in this service"));
    }

    @Test
    @Order(3)
    void testLockedOutUser() throws InterruptedException {
        testing.login("locked_out_user", "secret_sauce");
        WebElement error = driver.findElement(By.cssSelector("[data-test='error']"));
        assertTrue(error.isDisplayed(), "Locked out user should see an error message");
    }

    @Test
    @Order(4)
    void testSelectBackpack() throws InterruptedException {
        testing.login("standard_user", "secret_sauce");
        testing.selectBackpack();
        assertTrue(driver.getCurrentUrl().contains("id=4"),
                "Backpack test failed: Should return to product page.");
    }

    @Test
    @Order(5)
    void testSelectCheckout() throws InterruptedException {
        testing.login("standard_user", "secret_sauce");
        testing.selectCheckout();
        assertTrue(driver.getCurrentUrl().contains("checkout-step-one"),
                "Checkout test failed: Should be on checkout step one.");
    }

    @Test
    @Order(5)
    void testFillCheckoutForm() throws InterruptedException {
        testing.login("standard_user", "secret_sauce");
        testing.selectCheckout();
        testing.fillCheckoutForm();
        assertTrue(driver.getCurrentUrl().contains("checkout-step-two"),
                "Checkout form test failed: Should proceed to checkout step two.");
    }

    @AfterEach
    void browserClose() throws InterruptedException {
        driver.close();
    }

}