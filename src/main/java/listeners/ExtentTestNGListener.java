//package listeners;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import org.testng.*;
//import reporting.ExtentManager;
//import reporting.ExtentTestManager;
//
//public class ExtentTestNGListener implements ITestListener, ISuiteListener {
//
//    private static ExtentReports extent;
//
//    @Override
//    public void onStart(ISuite suite) {
//        extent = ExtentManager.createInstance();
//    }
//
//    @Override
//    public void onFinish(ISuite suite) {
//        extent.flush();
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        String testName = result.getMethod().getMethodName();
//        ExtentTest test = extent.createTest(testName);
//        ExtentTestManager.setScenarioTest(test);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        ExtentTest step = ExtentTestManager.getStepTest();
//        ExtentTestManager.getScenarioTest().info(step.toString());
//        ExtentTestManager.getScenarioTest().pass("Test Passed");
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//
//        ExtentTestManager.getScenarioTest().fail(result.getThrowable());
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        ExtentTestManager.getScenarioTest().skip("Test Skipped");
//    }
//
//
//}
//
