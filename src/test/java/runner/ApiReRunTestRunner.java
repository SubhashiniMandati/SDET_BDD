package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/rerun/api-rerun.txt",
        glue = {"stepdefinitions", "hooks"},
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber-rerun.json",
                "listeners.CucumberExtentListener"
        }
)
public class ApiReRunTestRunner extends AbstractTestNGCucumberTests {
}
