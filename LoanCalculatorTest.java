package tests;

import base.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import pages.LoanCalculatorPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoanCalculatorTest extends BaseClass {

    private static final Logger logger =
            LogManager.getLogger(LoanCalculatorTest.class);

    //TestNG test method to validate the Loan Calculator UI
    @Test
    public void testUI() throws Exception {

        LoanCalculatorPage page = new LoanCalculatorPage(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        logger.info("Opening Loan Calculator");

        page.openLoanCalculator();

        Thread.sleep(3000);

        logger.info("Validating EMI Calculator");

        page.validateEMICalculator();

        logger.info("Switching Loan Tenure Between Year And Month");

        page.switchTenure();

        logger.info("Validating Loan Amount Calculator");

        page.clickLoanAmountCalculator();

        Thread.sleep(3000);

        js.executeScript("window.scrollBy(0,300)");

        page.validateLoanAmountCalculator();

        logger.info("Validating Loan Tenure Calculator");

        page.clickLoanTenureCalculator();

        Thread.sleep(3000);

        js.executeScript("window.scrollBy(0,300)");

        page.validateLoanTenureCalculator();

        logger.info("Loan Calculator Validation Completed Successfully");
    }
}