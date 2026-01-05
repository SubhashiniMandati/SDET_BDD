package reporting;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static final ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> stepTest = new ThreadLocal<>();

    // Scenario
    public static void setScenarioTest(ExtentTest test) {
        scenarioTest.set(test);
    }

    public static ExtentTest getScenarioTest() {
        return scenarioTest.get();
    }

    // Step
    public static void setStepTest(ExtentTest test) {
        stepTest.set(test);
    }

    public static ExtentTest getStepTest() {
        return stepTest.get();
    }

    public static void clearStep() {
        stepTest.remove();
    }

    public static void clearAll() {
        scenarioTest.remove();
        stepTest.remove();
    }

//    private static ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();
//    private static ThreadLocal<ExtentTest> stepTest = new ThreadLocal<>();
//
//    // Scenario
//    public static void setScenarioTest(ExtentTest test) {
//        scenarioTest.set(test);
//    }
//
//    public static ExtentTest getScenarioTest() {
//        return scenarioTest.get();
//    }
//
//    public static void unloadScenario() {
//        scenarioTest.remove();
//    }
//
//    // Step
//    public static void setStepTest(ExtentTest test) {
//        stepTest.set(test);
//    }
//
//    public static ExtentTest getStepTest() {
//        return stepTest.get();
//    }
//
//    public static void unloadStep() {
//        stepTest.remove();
//    }
//    public static void remove() {
//        unloadStep();
//        unloadScenario();
//    }
}
