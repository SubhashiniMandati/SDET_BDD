//package listeners;
//
//import io.cucumber.plugin.EventListener;
//import io.cucumber.plugin.event.*;
//import reporting.ExtentTestManager;
//import com.aventstack.extentreports.ExtentTest;
//
//public class CucumberStepListener implements EventListener {
//
//    @Override
//    public void setEventPublisher(EventPublisher publisher) {
//
//        // Step started
//        publisher.registerHandlerFor(TestStepStarted.class, event -> {
//            String stepName = "";
//
//            if (event.getTestStep() instanceof PickleStepTestStep step) {
//                stepName = step.getStep().getKeyword() + step.getStep().getText();
//            } else if (event.getTestStep() instanceof HookTestStep hook) {
//                // Hooks like Before/After steps
//                stepName = hook.getHookType().name() + " Hook";
//            }
//
//            ExtentTest stepNode = ExtentTestManager.getScenarioTest().createNode(stepName);
//            ExtentTestManager.setStepTest(stepNode);
//        });
//
//        // Step finished
//        publisher.registerHandlerFor(TestStepFinished.class, event -> {
//            ExtentTest stepTest = ExtentTestManager.getStepTest();
//            if (stepTest == null) return; // skip if no step node
//
//            switch (event.getResult().getStatus()) {
//                case PASSED:
//                    stepTest.pass("Step Passed");
//                    break;
//                case FAILED:
//                    stepTest.fail(event.getResult().getError());
//                    break;
//                case SKIPPED:
//                    stepTest.skip("Step Skipped");
//                    break;
//                default:
//                    stepTest.info("Step status: " + event.getResult().getStatus());
//            }
//        });
//    }
//}
