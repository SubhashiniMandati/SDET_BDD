package hooks;

import com.aventstack.extentreports.MediaEntityBuilder;
import driver.DriverFactory;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;

public class Hooks {
    private static final Logger log =
            LoggerUtil.getLogger(Hooks.class);
    @Before
    public void beforeScenario(Scenario scenario) {
        log.info("===== Scenario Started: {} =====", scenario.getName());
        DriverFactory.initDriver();
        // WebDriver setup ONLY
    }

//    @AfterStep
//    public void afterEachStep(Scenario scenario) {
//        if (scenario.isFailed()) {
//            WebDriver driver = DriverFactory.getDriver();
//            try {
//                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//                scenario.attach(screenshot, "image/png", "Failed Step Screenshot");
//
//                // Attach to Extent Report step node
//                ExtentManager.getStepTest().fail("Step Failed",
//                        MediaEntityBuilder.createScreenCaptureFromBase64String(
//                                java.util.Base64.getEncoder().encodeToString(screenshot)
//                        ).build());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @After
    public void afterScenario(Scenario scenario) {
//        log.info("===== Scenario Finished: {} | Status: {} =====",
//                scenario.getName(),
//                scenario.getStatus());
//        ExtentTestManager.clearAll();
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        DriverFactory.quitDriver();
    }
}
