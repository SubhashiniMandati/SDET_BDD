package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions","hooks"},
        plugin = {
                "pretty",
                "listeners.CucumberExtentListener",
                "rerun:target/rerun.txt"
        },
        tags = "@Smoke"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
