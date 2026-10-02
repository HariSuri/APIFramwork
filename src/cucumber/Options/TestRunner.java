package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(   features = "src/features",
                    plugin ="json:target/jsonReports/cucumber-report.json",
                    glue = "stepDefinitions"
)
public class TestRunner extends AbstractTestNGCucumberTests {

}