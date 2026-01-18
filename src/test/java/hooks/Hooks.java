package hooks;

import config.ConfigReader;
import context.TestContext;
import driver.DriverFactory;
import io.cucumber.java.*;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import log.LoggerUtil;
import org.testng.asserts.SoftAssert;
import utils.SoftAssertManager;

public class Hooks {

    private static final Logger log =
            LoggerUtil.getLogger(Hooks.class);


    @Before
    public void beforeScenario(Scenario scenario) {
        log.info("===== Before Hook Executing | Platform: " + TestContext.platform + " =====");
        log.info("===== Scenario Started: {} =====", scenario.getName());
        if(TestContext.platform.equalsIgnoreCase("web")){
            // Initializes Local / Grid / BrowserStack
            DriverFactory.initDriver(scenario.getName());
        }

    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        SoftAssert softAssert = SoftAssertManager.getSoftAssert();

        try {
            softAssert.assertAll();   // ← triggers AssertionError
        } catch (AssertionError e) {

            // 1️⃣ Mark scenario as FAILED
            scenario.log("Soft assertion failures:");
            scenario.log(e.getMessage());

            // 2️⃣ Fail scenario explicitly
            throw e;
        } finally {
            SoftAssertManager.clear();
        }
        log.info("===== Scenario Finished: {} | Status: {} =====",
                scenario.getName(),
                scenario.getStatus());
        if (TestContext.platform.equalsIgnoreCase("web")){

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
