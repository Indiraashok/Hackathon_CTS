package tests;

import base.BaseClass;
import org.testng.annotations.Test;
import pages.HomeLoanPage;
import utils.ExcelUtil;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeLoanTest extends BaseClass {

    private static final Logger logger =
            LogManager.getLogger(HomeLoanTest.class);

    //TestNG test method to perform home loan calculation and extract data
    @Test
    public void extractHomeLoanData() throws Exception {

        HomeLoanPage page = new HomeLoanPage(driver);

        logger.info("Opening Home Loan EMI Calculator");

        page.openHomeLoanPage();

        Thread.sleep(3000);

        logger.info("Entering Home Loan Details");

        page.fillAllDetails();

        Thread.sleep(2000);

        logger.info("Scrolling To Payment Schedule Table");

        page.scrollToTable();

        Thread.sleep(2000);

        logger.info("Expanding Year Wise Rows");

        page.expandAllYears();

        Thread.sleep(3000);

        logger.info("Extracting Year Wise Data");

        List<List<String>> data = page.getTableData();

        ExcelUtil.writeData(data);

        logger.info("Home Loan Data Successfully Stored In Excel");
    }
}