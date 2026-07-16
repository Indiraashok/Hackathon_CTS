package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseClass {

    public WebDriver driver;

    // Initialize the WebDriver before running any tests and navigate to the EMI Calculator website
    @BeforeClass
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://emicalculator.net/");
    }

    // Quit the WebDriver after all tests have been executed
    @AfterClass
    public void teardown() {

        if (driver != null) {
            driver.quit();
        }
    }
}