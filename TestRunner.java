package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// This class is used to run the Cucumber tests using TestNG
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        monochrome = true,
        plugin = {

                "pretty",

                "html:target/CucumberReport.html",

                "json:target/cucumber.json",

                "junit:target/cucumber.xml"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
