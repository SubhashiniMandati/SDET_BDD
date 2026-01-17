package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/web",
        glue = {"stepdefinitions","hooks"},
        plugin = {
                "pretty",
                "json:target/cucumber-report/web-cucumber.json",
                "listeners.CucumberExtentListener",
                "rerun:target/rerun/web-rerun.txt"
        },
        tags = "@Smoke"
)
public class WebTestRunner extends AbstractTestNGCucumberTests {
}
