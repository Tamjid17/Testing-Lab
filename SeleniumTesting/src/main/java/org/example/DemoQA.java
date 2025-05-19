package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class DemoQA {
    WebDriver driver;
    public void setDemoDriver() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--incognito");
        driver = new ChromeDriver(option);
    }

    public void loadDemoWebsite() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        //driver.close();
    }

    public void fillTextInput() throws InterruptedException {
        driver.findElement(By.id("firstName")).sendKeys("John");
        driver.findElement(By.id("lastName")).sendKeys("Doe");
        driver.findElement(By.id("userEmail")).sendKeys("johndoe@gmail.com");
        driver.findElement(By.id("userNumber")).sendKeys("01364872674");
        driver.findElement(By.id("currentAddress")).sendKeys("14/3, Barkley Street, Milton, UK");
        Thread.sleep(1000);
    }

    public void uploadImage() throws InterruptedException {
        driver.findElement(By.id("uploadPicture")).sendKeys("D:\\Projects\\JohnDoe.jpg");
        Thread.sleep(3000);
    }

    public void fillCalenderDate() throws InterruptedException {
        String dayOfWeek = "Wednesday";
        int actualDate = 16;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.id("dateOfBirthInput")).click();

        WebElement month = wait.until(ExpectedConditions.elementToBeClickable(By.className("react-datepicker__month-select")));
        month.click();
        WebElement actualMonth = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='0']")));
        actualMonth.click();

        WebElement year = wait.until(ExpectedConditions.elementToBeClickable(By.className("react-datepicker__year-select")));
        year.click();
        WebElement actualYear = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='2002']")));
        actualYear.click();

        WebElement date = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[aria-label='Choose " +dayOfWeek+ ", January " +actualDate+ "th, 2002']")));
        date.click();
        Thread.sleep(2000);
    }
    public void fillDropdownInput() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.xpath("//div[contains(text(),'Select State')]")).click();
        driver.findElement(By.xpath("//div[text()='NCR']")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Select City')]")).click();
        WebElement cityOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Delhi']"))
        );
        cityOption.click();
        Thread.sleep(1000);
    }

    public void fillOptionButton(int genderValue, List<Integer> hobbiesValue) throws InterruptedException {
        driver.findElement(By.xpath("//label[@for='gender-radio-" +genderValue+ "']")).click();

        for (int hobbyValue : hobbiesValue) {
            String hobbyXPath = "//label[@for='hobbies-checkbox-" + hobbyValue + "']";
            driver.findElement(By.xpath(hobbyXPath)).click();
        }

    }

    public void fillSubjects(List<String> subjects) throws InterruptedException {
        WebElement subjectInput = driver.findElement(By.id("subjectsInput")); // Adjust ID if different

        for (String subject : subjects) {
            subjectInput.sendKeys(subject);
            Thread.sleep(1000); // Wait for suggestions to appear
            subjectInput.sendKeys(Keys.ARROW_DOWN);
            // Press ENTER to select the first suggestion
            subjectInput.sendKeys(Keys.ENTER);

            // Optional short pause between inputs
            Thread.sleep(500);
        }
    }

    public void submit() {
        driver.findElement(By.id("submit")).click();
    }

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Browser Driver\\chromedriver.exe");

        DemoQA testing = new DemoQA();
        testing.setDemoDriver();
        testing.loadDemoWebsite();
        testing.fillSubjects(Arrays.asList("Maths", "Commerce"));
        testing.fillTextInput();
        testing.uploadImage();
        testing.fillDropdownInput();
        testing.fillOptionButton(1, Arrays.asList(1, 3));
        testing.fillCalenderDate();
        testing.submit();
    }
}
