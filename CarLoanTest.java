package tests;

import base.BaseClass;
import org.testng.annotations.Test;
import pages.CarLoanPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CarLoanTest extends BaseClass {

    private static final Logger logger =
            LogManager.getLogger(CarLoanTest.class);

    //TestNG test method to perform car loan calculation
    @Test
    public void carLoanTest() throws Exception {

        logger.info("Starting Car Loan Calculation");

        CarLoanPage page = new CarLoanPage(driver);

        page.printCarLoanDetails();

        logger.info("Car Loan Calculation Completed Successfully");
    }
}
