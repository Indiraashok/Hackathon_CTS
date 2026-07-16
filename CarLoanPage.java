package pages;

import org.openqa.selenium.*;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CarLoanPage {

	// Logger for logging information
    private static final Logger logger =
            LogManager.getLogger(CarLoanPage.class);

    WebDriver driver;

    // Constructor to initialize WebDriver
    public CarLoanPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCarLoanTab() {

        logger.info("Clicking Car Loan Tab");

        WebElement carLoanTab =
                driver.findElement(By.xpath("//*[@id=\"car-loan\"]/a"));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", carLoanTab);
    }
    
    // Get WebElement for Loan Amount, Interest Rate, and Loan Tenure
    public WebElement loanAmount() {
        return driver.findElement(By.id("loanamount"));
    }

    public WebElement interestRate() {
        return driver.findElement(By.id("loaninterest"));
    }

    public WebElement loanTenure() {
        return driver.findElement(By.id("loanterm"));
    }

    public WebElement emiValue() {
        return driver.findElement(
                By.xpath("//*[@id=\"leschemewrapper\"]/div/div/div/div/label[2]"));
    }

    // Enter value in input field using JavaScriptExecutor
    public void enterValue(WebElement element, String value) {

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);

        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    // Enter Car Loan Details
    public void enterLoanDetails() {

        logger.info("Entering Car Loan Details");

        enterValue(loanAmount(), "1500000");
        enterValue(interestRate(), "9.5");
        enterValue(loanTenure(), "1");

        emiValue().click();
    }

    // Scroll to Amortization Table
    public void scrollToTable() {

        logger.info("Scrolling To Amortization Table");

        ((JavascriptExecutor) driver)
                .executeScript("window.scrollBy(0,1000)");

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    // Expand Year 2026 in Amortization Table
    public void expandYear2026() {

        logger.info("Expanding Year 2026");

        WebElement year = driver.findElement(By.id("year2026"));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", year);

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"monthyear2026\"]/td/div/table/tbody/tr[1]/td[1]")));
    }

    // Get First Month Principal and Interest
    public String getFirstMonthPrincipal() {

        return driver.findElement(
                By.xpath("//*[@id=\"monthyear2026\"]/td/div/table/tbody/tr[1]/td[2]"))
                .getText();
    }

    // Get First Month Interest
    public String getFirstMonthInterest() {

        return driver.findElement(
                By.xpath("//*[@id=\"monthyear2026\"]/td/div/table/tbody/tr[1]/td[3]"))
                .getText();
    }

    //	 Wait for Table Update
    public void waitForTableUpdate() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(driver -> {

            try {

                String principal = driver.findElement(
                        By.xpath("//tr[@id='monthyear2026']//tr[1]/td[2]"))
                        .getText();

                String interest = driver.findElement(
                        By.xpath("//tr[@id='monthyear2026']//tr[1]/td[3]"))
                        .getText();

                return principal != null &&
                        !principal.trim().isEmpty() &&
                        interest != null &&
                        !interest.trim().isEmpty();

            } catch (Exception e) {

                return false;
            }
        });
    }

    // Print Car Loan Details
    public void printCarLoanDetails() {

        clickCarLoanTab();

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }

        enterLoanDetails();

        scrollToTable();

        expandYear2026();

        waitForTableUpdate();

        String principal = getFirstMonthPrincipal();
        String interest = getFirstMonthInterest();

        logger.info("===== CAR LOAN RESULT =====");
        logger.info("Loan Amount : 1500000");
        logger.info("Interest Rate : 9.5%");
        logger.info("Tenure : 1 Year");
        logger.info("First Month Principal : {}", principal);
        logger.info("First Month Interest : {}", interest);
    }
}