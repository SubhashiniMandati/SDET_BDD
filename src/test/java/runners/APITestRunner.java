package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/API",
        glue = {"stepdefinitions","hooks"},
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber.json",
                "listeners.CucumberExtentListener",
                "rerun:target/rerun.txt"
        },
        tags = "@api"
)
public class APITestRunner extends AbstractTestNGCucumberTests {
}
