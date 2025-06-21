from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
import time
import unittest
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait

class DemoQaFormTest(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Edge()
        self.driver.get("https://demoqa.com/automation-practice-form")
        self.driver.maximize_window()
        self.wait = WebDriverWait(self.driver, 10)
    def tearDown(self):
        self.driver.quit()
    def test_successful_form_submission(self):
        self.driver.find_element(By.XPATH, '//*[@id="firstName"]').send_keys("John")
        self.driver.find_element(By.XPATH, '//*[@id="lastName"]').send_keys("Doe")
        time.sleep(1)
        self.driver.find_element(By.XPATH, '//*[@id="userEmail"]').send_keys("john.doe@example.com")
        time.sleep(1)
        self.driver.find_element(By.ID, 'userNumber').send_keys("1234567890")
        self.driver.find_element(By.ID, 'currentAddress').send_keys("123 Main Street, Anytown")
        time.sleep(1)
        self.driver.find_element(By.XPATH, "//label[text()='Male']").click()

        time.sleep(1)
        self.driver.find_element(By.XPATH, "//*[text()='Sports']").click()
        self.driver.find_element(By.XPATH, "//*[text()='Reading']").click()

        self.driver.find_element(By.ID, "dateOfBirthInput").click()
        #Year
        year_dropdown = self.driver.find_element(By.XPATH, '//*[@id="dateOfBirth"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select')
        Select(year_dropdown).select_by_visible_text("2000")
        time.sleep(1)
        #Month
        month_dropdown = self.driver.find_element(By.CLASS_NAME, "react-datepicker__month-select")
        Select(month_dropdown).select_by_visible_text("July")
        time.sleep(1)
        #Day
        self.driver.find_element(By.XPATH, '//*[@id="dateOfBirth"]/div[2]/div[2]/div/div/div[2]/div[2]/div[3]/div[7]').click()
        time.sleep(1)
        self.driver.find_element(By.ID, "uploadPicture").send_keys("C://Users//islam//Downloads//images.jpg")
        time.sleep(1)
        self.driver.find_element(By.ID, "state").click()
        self.driver.find_element(By.XPATH, "//*[text()='NCR']").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "city").click()
        self.driver.find_element(By.XPATH, "//*[text()='Delhi']").click()
        time.sleep(1)
        subjects = self.driver.find_element(By.ID, 'subjectsInput')
        subjects.send_keys("Com")
        time.sleep(1)

        subjects.send_keys(Keys.ENTER)
        time.sleep(1)
        subjects.send_keys("Che")
        subjects.send_keys(Keys.ENTER)
        time.sleep(1)
        self.driver.find_element(By.ID, 'submit').click()
        time.sleep(2)
        submitMessage = self.driver.find_element(By.ID, 'example-modal-sizes-title-lg')
        time.sleep(2)

        self.assertTrue(submitMessage.is_displayed(), "Message wasn't found, the test failed")
        print("Test Passed: 'Thanks for submitting the form!' message was verified successfully.")