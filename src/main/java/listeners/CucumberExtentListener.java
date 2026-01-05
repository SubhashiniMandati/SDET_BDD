package listeners;

import com.aventstack.extentreports.*;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import reporting.ExtentManager;

public class CucumberExtentListener implements EventListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> stepTest = new ThreadLocal<>();

    @Override
    public void setEventPublisher(EventPublisher publisher) {

        publisher.registerHandlerFor(TestCaseStarted.class, event -> {
            ExtentTest test = extent.createTest(event.getTestCase().getName());
            scenarioTest.set(test);
        });

        publisher.registerHandlerFor(TestStepStarted.class, event -> {
            if (event.getTestStep() instanceof PickleStepTestStep step) {
                stepTest.set(
                        scenarioTest.get().createNode(step.getStep().getText())
                );
            }
        });

        publisher.registerHandlerFor(TestStepFinished.class, event -> {
            if (stepTest.get() == null) return;

            switch (event.getResult().getStatus()) {
                case PASSED -> stepTest.get().pass("Step Passed");
                case FAILED -> stepTest.get().fail(event.getResult().getError()); // Screenshot will be added in Hooks
                default -> stepTest.get().skip("Skipped");
            }
        });

        publisher.registerHandlerFor(TestRunFinished.class, event -> extent.flush());
    }
}
