package listeners;

import com.aventstack.extentreports.*;
import context.TestContext;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.openqa.selenium.*;
import reporting.ExtentManager;
import utils.web.ScreenhotUtil;

import java.util.*;

public class CucumberExtentListener implements ConcurrentEventListener {

    private static ExtentReports extent = ExtentManager.createInstance();
    private static Map<String, ExtentTest> scenarioMap = new HashMap<>();

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
        publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
    }

    private void onTestCaseStarted(TestCaseStarted event) {
        ExtentTest scenario = extent.createTest(event.getTestCase().getName());
        scenarioMap.put(event.getTestCase().getId().toString(), scenario);
    }

    private void onTestStepFinished(TestStepFinished event) {

        if (!(event.getTestStep() instanceof PickleStepTestStep)) {
            return;
        }

        PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
        String stepText = step.getStep().getText();

        ExtentTest scenarioNode =
                scenarioMap.get(event.getTestCase().getId().toString());

        io.cucumber.plugin.event.Status cucumberStatus =
                event.getResult().getStatus();

        if (cucumberStatus == io.cucumber.plugin.event.Status.PASSED) {
            scenarioNode.pass(stepText);
        }

        else if (cucumberStatus == io.cucumber.plugin.event.Status.FAILED) {
            if(TestContext.platform.equalsIgnoreCase("web")) {
                String base64Screenshot = ScreenhotUtil.getBase64Screenshot();

                scenarioNode.fail(stepText);

                if (base64Screenshot != null) {
                    scenarioNode.addScreenCaptureFromBase64String(
                            base64Screenshot,
                            "Failure Screenshot"
                    );
                }
            }

            scenarioNode.fail(event.getResult().getError());
        }

        else if (cucumberStatus == io.cucumber.plugin.event.Status.SKIPPED) {
            scenarioNode.skip(stepText);
        }
    }

    private void onTestCaseFinished(TestCaseFinished event) {
        extent.flush();
    }
}
