package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        if (extent == null) {
            try {
                // Ensure target/test-output folder exists
                Path outputPath = Path.of("target/test-output");
                if (!Files.exists(outputPath)) {
                    Files.createDirectories(outputPath);
                }

                String reportFile = "target/test-output/ExtentReport.html";
                ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFile);
                sparkReporter.config().setReportName("BDD Test Automation Report");
                sparkReporter.config().setDocumentTitle("Automation Report");

                extent = new ExtentReports();
                extent.attachReporter(sparkReporter);
                extent.setSystemInfo("Tester", "Subhashini");
                extent.setSystemInfo("Project", "My BDD Framework");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return extent;
    }
}
