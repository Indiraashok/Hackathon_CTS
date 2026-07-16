package stepdefinitions;

import java.util.List;

import base.BaseClass;
import io.cucumber.java.en.*;
import pages.HomeLoanPage;
import utils.ExcelUtil;

public class HomeLoanSteps extends BaseClass {

    HomeLoanPage page;

    //=========================
    //	Cucumber Step Definitions
    //=========================
    @Given("User opens Home Loan EMI Calculator")
    public void openHomeLoanPage() {

        setup();

        page = new HomeLoanPage(driver);

        page.openHomeLoanPage();
    }

    //=========================
    //    Home Loan Calculation Steps
    //=========================
    @When("User enters home loan details")
    public void enterDetails() {

        page.fillAllDetails();
    }

    //=========================
    //  Data Extraction Step
    //	=========================
    @When("User extracts year wise table")
    public void extractData() {

        page.scrollToTable();

        List<List<String>> data = page.getTableData();

        ExcelUtil.writeData(data);
    }
    //=========================
    //  Verification Step
    //=========================
    @Then("Data should be stored in excel")
    public void verifyExcel() {

        teardown();
    }
}