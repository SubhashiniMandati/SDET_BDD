package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/rerun.txt",
        glue = {"stepdefs", "hooks"},
        plugin = {
                "pretty",
                "json:target/cucumber-rerun.json"
        }
)
public class RerunXrayTestRunner extends AbstractTestNGCucumberTests {
}

