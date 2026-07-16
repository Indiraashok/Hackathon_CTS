package stepdefinitions;

import base.BaseClass;
import io.cucumber.java.en.*;
import pages.LoanCalculatorPage;

public class LoanCalculatorSteps extends BaseClass {

    LoanCalculatorPage page;

    //this class contains the step definitions for the Loan Calculator feature
    @Given("User opens Loan Calculator")
    public void openLoanCalculator() throws Exception {

        setup();

        page = new LoanCalculatorPage(driver);

        page.openLoanCalculator();
    }

    //this step fills in the loan details in the calculator
    @When("User validates EMI calculator")
    public void validateEMI() {

        page.validateEMICalculator();
    }

    //this step switches the tenure in the calculator
    @When("User switches tenure")
    public void switchTenure() {

        page.switchTenure();
    }

    //this step validates the loan amount calculator
    @When("User validates Loan Amount Calculator")
    public void validateLoanAmount() {

        page.clickLoanAmountCalculator();

        page.validateLoanAmountCalculator();
    }

    //this step validates the loan tenure calculator
    @When("User validates Loan Tenure Calculator")
    public void validateLoanTenure() {

        page.clickLoanTenureCalculator();

        page.validateLoanTenureCalculator();
    }

    //this step completes the test and tears down the driver
    @Then("All validations should pass")
    public void complete() {

        teardown();
    }
}