package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions","hooks"},
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber.json",
                "listeners.CucumberExtentListener",
                "rerun:target/rerun.txt"
        }
)
public class XrayTestRunner extends AbstractTestNGCucumberTests {
}
