package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    private ExtentReportManager() {}

    public static synchronized ExtentReports getExtent() {

        if (extent == null) {
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("target/test-output/ExtentReport.html");

            spark.config().setReportName("Automation Execution Report");
            spark.config().setDocumentTitle("BDD Automation");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static synchronized void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
