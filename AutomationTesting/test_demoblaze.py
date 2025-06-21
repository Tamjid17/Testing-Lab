import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select
import time

class TestPurchaseConfirmation(unittest.TestCase):
    def setUp(self):
        """Runs before the test. Sets up the browser and navigates to the URL."""
        self.driver = webdriver.Edge()
        self.driver.fullscreen_window()
        self.driver.get("https://demoblaze.com")
        # WebDriverWait is essential for reliable tests
        self.wait = WebDriverWait(self.driver, 10)
    def tearDown(self):
        """Runs after the test. Closes the browser."""
        self.driver.quit()
    def test_thank_you_message_after_purchase(self):
        """
        Performs a full end-to-end purchase and verifies ONLY the final
        'Thank you' message is displayed.
        """
        # --- 1. LOGIN ---
        # Assumes user 'jid0tam' already exists.
        self.driver.find_element(By.ID, 'login2').click()
        self.wait.until(EC.visibility_of_element_located((By.ID, 'logInModal')))
        self.driver.find_element(By.ID, 'loginusername').send_keys("jid0tam")
        self.driver.find_element(By.ID, 'loginpassword').send_keys("123456")
        self.driver.find_element(By.XPATH, '//*[@id="logInModal"]/div/div/div[3]/button[2]').click()

        # We wait for the welcome message to ensure the login is complete before proceeding.
        time.sleep(3)

        # --- 2. ADD ITEM TO CART ---
        self.driver.find_element(By.XPATH, '//*[@id="tbodyid"]/div[1]/div/div/h4/a').click()
        time.sleep(2)
        self.wait.until(EC.element_to_be_clickable((By.XPATH, "//*[text()='Add to cart']"))).click()
        
        # Wait for and accept the "Product added" alert without testing its text.
        self.wait.until(EC.alert_is_present()).accept()
        
        # --- 3. CHECKOUT PROCESS ---
        self.driver.find_element(By.ID, 'cartur').click()
        self.wait.until(EC.element_to_be_clickable((By.XPATH, "//button[text()='Place Order']"))).click()
        
        # Fill out the purchase form
        self.wait.until(EC.visibility_of_element_located((By.ID, 'orderModal')))
        self.driver.find_element(By.ID, 'name').send_keys("Tamjid")
        self.driver.find_element(By.ID, 'country').send_keys("Bangladesh")
        self.driver.find_element(By.ID, 'city').send_keys("Dhaka")
        self.driver.find_element(By.ID, 'card').send_keys("1234458965342")
        self.driver.find_element(By.ID, 'month').send_keys("June")
        self.driver.find_element(By.ID, 'year').send_keys("2025")
        
        self.driver.find_element(By.XPATH, "//button[text()='Purchase']").click()

        # --- 4. FINAL VERIFICATION (THE ONLY ASSERTION) ---
        # Wait for the confirmation message to appear
        success_message = self.wait.until(
            EC.visibility_of_element_located((By.XPATH, "//h2[text()='Thank you for your purchase!']"))
        )

        # This is the single test to verify the entire flow was successful.
        self.assertTrue(success_message.is_displayed(), 
                        "The purchase confirmation message was not found.")
        
        print("Test Passed: 'Thank you for your purchase!' message was verified successfully.")
        
        # Click OK on the final confirmation to allow the script to finish cleanly
        self.driver.find_element(By.XPATH, "//button[text()='OK']").click()

if __name__ == '__main__':
    unittest.main(verbosity=2)