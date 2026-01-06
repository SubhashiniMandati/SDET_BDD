package hooks;

import config.ConfigReader;
import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import utils.LoggerUtil;

public class Hooks {

    private static final Logger log =
            LoggerUtil.getLogger(Hooks.class);

    @Before
    public void beforeScenario(Scenario scenario) {

        log.info("===== Scenario Started: {} =====", scenario.getName());

        // Initializes Local / Grid / BrowserStack
        DriverFactory.initDriver(scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {

        log.info("===== Scenario Finished: {} | Status: {} =====",
                scenario.getName(),
                scenario.getStatus());

        WebDriver driver = DriverFactory.getDriver();

        // 🔹 Attach screenshot for failed scenarios (ALL modes)
        if (scenario.isFailed() && driver instanceof TakesScreenshot) {
            try {
                byte[] screenshot =
                        ((TakesScreenshot) driver)
                                .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            } catch (Exception e) {
                log.warn("Screenshot capture failed", e);
            }
        }

        // 🔹 Update BrowserStack status ONLY if BrowserStack execution
        updateBrowserStackStatus(scenario, driver);

        // 🔹 Cleanup
        DriverFactory.quitDriver();
    }

    // =====================================================
    // BrowserStack Status Update (SAFE & ISOLATED)
    // =====================================================
    private void updateBrowserStackStatus(Scenario scenario, WebDriver driver) {

        String executionMode =
                ConfigReader.getProperty("execution.mode");

        if (!"browserstack".equalsIgnoreCase(executionMode)) {
            return; // Skip for local/grid
        }

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            if (scenario.isFailed()) {
                js.executeScript(
                        "browserstack_executor: {\"action\": \"setSessionStatus\", " +
                                "\"arguments\": {\"status\":\"failed\", \"reason\":\"Scenario failed\"}}"
                );
            } else {
                js.executeScript(
                        "browserstack_executor: {\"action\": \"setSessionStatus\", " +
                                "\"arguments\": {\"status\":\"passed\", \"reason\":\"Scenario passed\"}}"
                );
            }

            log.info("BrowserStack session status updated");

        } catch (Exception e) {
            log.warn("Failed to update BrowserStack status", e);
        }
    }
}
