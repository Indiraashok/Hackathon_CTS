package pages;

import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoanCalculatorPage {

    private static final Logger logger =
            LogManager.getLogger(LoanCalculatorPage.class);

    WebDriver driver;

    public LoanCalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    //  OPEN LOAN CALCULATOR PAGE
    public void openLoanCalculator() throws InterruptedException {

        logger.info("Opening Loan Calculator Page");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement menu =
                driver.findElement(By.xpath("//*[@id='menu-item-dropdown-2696']"));

        js.executeScript("arguments[0].click();", menu);

        Thread.sleep(1000);

        WebElement loanCalc =
                driver.findElement(By.xpath("//*[@id='menu-item-2423']/a"));

        js.executeScript("arguments[0].click();", loanCalc);
    }

    // ==========================
    //  EMI CALCULATOR LOCATORS
    // ==========================

    public WebElement loanAmount() {
        return driver.findElement(By.id("loanamount"));
    }

    public WebElement interestRate() {
        return driver.findElement(By.id("loaninterest"));
    }

    public WebElement loanTenure() {
        return driver.findElement(By.id("loanterm"));
    }

    // ==========================
    //  SLIDERS
    // ==========================

    public WebElement loanAmountSlider() {
        return driver.findElement(By.id("loanamountslider"));
    }

    public WebElement interestSlider() {
        return driver.findElement(By.id("loaninterestslider"));
    }

    public WebElement tenureSlider() {
        return driver.findElement(By.id("loantermslider"));
    }

    // ==========================
    //  TENURE SWITCH
    // ==========================

    public WebElement yearsBtn() {
        return driver.findElement(By.id("loanyears"));
    }

    public WebElement monthsBtn() {
        return driver.findElement(By.id("loanmonths"));
    }

    // ==========================
    //  LOAN AMOUNT TAB
    // ==========================

    public void clickLoanAmountCalculator() {

        logger.info("Opening Loan Amount Calculator");

        driver.findElement(
                By.xpath("//*[@id=\"loan-amount-calc\"]/a[1]"))
                .click();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("loanemi")));
    }

    // ==========================
    //  LOAN TENURE TAB
    // ==========================

    public void clickLoanTenureCalculator() {

        logger.info("Opening Loan Tenure Calculator");

        driver.findElement(
                By.xpath("//*[@id=\"loan-tenure-calc\"]/a[1]"))
                .click();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("loanamount")));
    }

    // ==========================
    //  COMMON TEXTBOX VALIDATION
    // ==========================

    public void validateTextbox(WebElement element,
                                String value) {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView(true);",
                element);

        element.click();

        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        String actual =
                element.getAttribute("value");

        logger.info(
                "Entered: {} | Actual: {}",
                value,
                actual);

        if (!actual.replaceAll(",", "")
                .contains(value)) {

            throw new RuntimeException(
                    "Textbox validation failed");
        }
    }

    // ==========================
    //  SLIDER METHOD
    // ==========================

    public void moveSlider(WebElement slider) {

        logger.info("Moving Slider");

        Actions actions = new Actions(driver);

        actions.clickAndHold(slider)
                .moveByOffset(50, 0)
                .release()
                .perform();
    }

    // ==========================
    //  EMI VALIDATION
    // ==========================

    public void validateEMICalculator() {

        logger.info("Validating EMI Calculator");

        validateTextbox(loanAmount(), "500000");
        validateTextbox(interestRate(), "9");
        validateTextbox(loanTenure(), "5");

        moveSlider(loanAmountSlider());
        moveSlider(interestSlider());
        moveSlider(tenureSlider());

        logger.info("EMI Calculator Validation Complete");
    }

    // ==========================
    //  LOAN AMOUNT VALIDATION
    // ==========================

    public void validateLoanAmountCalculator() {

        logger.info("Validating Loan Amount Calculator");

        WebElement emi =
                driver.findElement(By.id("loanemi"));

        WebElement interest =
                driver.findElement(By.id("loaninterest"));

        WebElement tenure =
                driver.findElement(By.id("loanterm"));

        validateTextbox(emi, "20000");
        validateTextbox(interest, "8");
        validateTextbox(tenure, "10");

        logger.info("Loan Amount Calculator Validation Complete");
    }

    // ==========================
    //  LOAN TENURE VALIDATION
    // ==========================

    public void validateLoanTenureCalculator() {

        logger.info("Validating Loan Tenure Calculator");

        WebElement amount =
                driver.findElement(By.id("loanamount"));

        WebElement emi =
                driver.findElement(By.id("loanemi"));

        WebElement interest =
                driver.findElement(By.id("loaninterest"));

        validateTextbox(amount, "500000");
        validateTextbox(emi, "20000");
        validateTextbox(interest, "9");

        logger.info("Loan Tenure Calculator Validation Complete");
    }

    // ==========================
    //  TENURE SWITCH VALIDATION
    // ==========================

    public void switchTenure() {

        logger.info("Switching Loan Tenure");

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        String before =
                loanTenure().getAttribute("value");

        logger.info(
                "Before Switching (Years): {}",
                before);

        js.executeScript(
                "arguments[0].click();",
                monthsBtn());

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }

        String afterMonths =
                loanTenure().getAttribute("value");

        logger.info(
                "After Switching To Months: {}",
                afterMonths);

        js.executeScript(
                "arguments[0].click();",
                yearsBtn());

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }

        
        // Validate that the tenure value has changed back to years
        String afterYears =
                loanTenure().getAttribute("value");

        logger.info(
                "After Switching Back To Years: {}",
                afterYears);

        if (before.equals(afterMonths)) {

            throw new RuntimeException(
                    "Tenure did not change");
        }

        logger.info("Tenure Switch Validation Successful");
    }
}