package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/rerun/api-rerun.txt",
        glue = {"stepdefinitions", "hooks"},
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber-rerun.json",
                "listeners.CucumberExtentListener",
                "html:target/api-cucumber-rerun-report.html"
        }
)
public class ApiReRunTestRunner extends AbstractTestNGCucumberTests {
}
