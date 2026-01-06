package hooks;

import driver.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.LoggerUtil;

public class Hooks {
    private static final Logger log =
            LoggerUtil.getLogger(Hooks.class);
    @Before
    public void beforeScenario(Scenario scenario) {
        log.info("===== Scenario Started: {} =====", scenario.getName());
        DriverFactory.initDriver();
        // WebDriver setup ONLY
    }


    @After
    public void afterScenario(Scenario scenario) {
        log.info("===== Scenario Finished: {} | Status: {} =====",
                scenario.getName(),
                scenario.getStatus());
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        DriverFactory.quitDriver();
    }
}
