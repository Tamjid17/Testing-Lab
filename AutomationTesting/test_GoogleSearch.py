# 1. Import the necessary modules
import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time

# 2. Create a test class that inherits from unittest.TestCase
class GoogleSearchTest(unittest.TestCase):

    # 3. Use the setUp method for pre-test setup
    def setUp(self):
        # Initialize the Chrome driver for each test
        self.driver = webdriver.Edge()
        self.driver.implicitly_wait(10) # Add an implicit wait for stability

    # 4. Write the actual test method, starting its name with "test_"
    def test_search_for_selenium(self):
        # The 'self.driver' is available from the setUp method
        driver = self.driver
        driver.get("https://www.bing.com")

        # Check that the title is correct
        self.assertIn("Bing", driver.title)

        # Find the search box
        search_box = driver.find_element(By.NAME, "q")

        # Type the search query and submit
        search_box.send_keys("Selenium automation with Python")
        search_box.send_keys(Keys.RETURN)
        time.sleep(2) # Wait a moment for the results page to load

        # Assert that the results page title contains the search query
        self.assertIn("Selenium automation with Python", driver.title)

    # You could add another test here, e.g., test_search_for_images(self):
    # def test_google_images_link(self):
    #     driver = self.driver
    #     driver.get("https://www.google.com")
    #     # ... your test code here ...
    #     self.assertTrue(some_condition)

    # 5. Use the tearDown method for post-test cleanup
    def tearDown(self):
        # Close the browser window after each test
        self.driver.quit()

# 6. A block to make the script runnable directly
if __name__ == '__main__':
    unittest.main()