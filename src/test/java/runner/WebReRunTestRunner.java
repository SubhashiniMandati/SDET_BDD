package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/rerun/web-rerun.txt",
        glue = {"stepdefinitions", "hooks"},
        plugin = {
                "pretty",
                "json:target/cucumber-report/web-cucumber-rerun.json",
                "listeners.CucumberExtentListener"
        }
)
public class WebReRunTestRunner extends AbstractTestNGCucumberTests {
}
