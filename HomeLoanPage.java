package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeLoanPage {

    private static final Logger logger =
            LogManager.getLogger(HomeLoanPage.class);

    WebDriver driver;

    public HomeLoanPage(WebDriver driver) {
        this.driver = driver;
    }

    // Get WebElement for Home Price, Down Payment, Interest Rate, Loan Term, and other fields
    public void openHomeLoanPage() {

        logger.info("Opening Home Loan EMI Calculator");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement menu = driver.findElement(
                By.xpath("//a[contains(text(),'Loan Calculators')]"));

        js.executeScript("arguments[0].click();", menu);

        WebElement homeLoan = driver.findElement(
                By.xpath("//a[contains(text(),'Home Loan EMI Calculator')]"));

        js.executeScript("arguments[0].click();", homeLoan);
    }

    //	 Enter value in input field using JavaScriptExecutor
    public void enterValue(WebElement element, String value) {

        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);

        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }

    // Fill all the details in the Home Loan EMI Calculator
    public void fillAllDetails() {

        logger.info("Entering Home Loan Details");

        enterValue(driver.findElement(By.id("homeprice")), "1000000");
        enterValue(driver.findElement(By.id("downpayment")), "10");
        enterValue(driver.findElement(By.id("homeloaninsuranceamount")), "0");
        enterValue(driver.findElement(By.id("homeloaninterest")), "6");
        enterValue(driver.findElement(By.id("homeloanterm")), "10");
        enterValue(driver.findElement(By.id("loanfees")), "0.15");
        enterValue(driver.findElement(By.id("onetimeexpenses")), "10");
        enterValue(driver.findElement(By.id("propertytaxes")), "0.35");
        enterValue(driver.findElement(By.id("homeinsurance")), "0.15");
        enterValue(driver.findElement(By.id("maintenanceexpenses")), "3000");

        logger.info("Waiting For Payment Schedule Table");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver ->
                        !driver.findElement(By.id("paymentschedule"))
                                .getText()
                                .isEmpty());
    }
    

    // Scroll to Payment Schedule Table
    public void scrollToTable() {

        logger.info("Scrolling To Payment Schedule Table");

        ((JavascriptExecutor) driver)
                .executeScript("window.scrollBy(0,1600)");
    }

    // Expand All Year Rows in Payment Schedule Table
    public void expandAllYears() {

        logger.info("Expanding All Year Rows");

        List<WebElement> years = driver.findElements(
                By.xpath("//td[contains(@id,'year')]"));

        for (WebElement year : years) {

            try {

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", year);

            } catch (Exception e) {
            }
        }
    }

    // Get Year Wise Table Data
    public List<List<String>> getTableData() {

        logger.info("Extracting Year Wise Table Data");

        List<List<String>> data = new ArrayList<>();

        List<WebElement> rows = driver.findElements(
                By.xpath("//tr[contains(@class,'yearlypaymentdetails')]"));

        // Add Header Row
        List<String> header = new ArrayList<>();

        header.add("Year");
        header.add("Principal");
        header.add("Interest");
        header.add("Taxes");
        header.add("Balance");

        data.add(header);

        for (WebElement row : rows) {

            List<String> rowData = new ArrayList<>();

            List<WebElement> cols = row.findElements(By.tagName("td"));

            for (WebElement col : cols) {

                rowData.add(col.getText());
            }

            data.add(rowData);
        }

        logger.info("Total Rows Extracted : {}", data.size() - 1);

        return data;
    }
}