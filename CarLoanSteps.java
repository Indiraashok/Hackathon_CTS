package stepdefinitions;

import base.BaseClass;
import io.cucumber.java.en.*;
import pages.CarLoanPage;

public class CarLoanSteps extends BaseClass {

    CarLoanPage carLoanPage;
    
    //=========================
    //  Cucumber Step Definitions
    //=========================
    @Given("User opens the EMI calculator website")
    public void openWebsite() {

        setup();

        carLoanPage = new CarLoanPage(driver);
    }

    //=========================
    //  Car Loan Calculation Steps
    //=========================
    @When("User calculates EMI for car loan")
    public void calculateCarLoan() {

        carLoanPage.printCarLoanDetails();
    }

    //=========================
    //  Verification Step
    //=========================
    @Then("Principal and interest amount should be displayed")
    public void verifyOutput() {

        teardown();
    }
}