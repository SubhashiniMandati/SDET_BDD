package XrayIntegration;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;

public class XrayResultUploader {

    public static void uploadResults() throws Exception {

        String executionKey = ConfigReader.getProperty("xray.execution.key");
        String token = XrayAuthClient.getAuthToken();

        File resultFile = new File("target/cucumber-final.json");

        if (!resultFile.exists()) {
            throw new RuntimeException("❌ Cucumber result file not found");
        }

        uploadResultToExecution(token, executionKey, resultFile);
        System.out.println("✅ Results uploaded to Xray execution " + executionKey);
    }
    private static final String XRAY_UPLOAD_URL = ConfigReader.getProperty("Xray_BaseUrl")+ConfigReader.getProperty("Xray_ResultUploadEndPoint");

    /**
     * Upload results and CREATE a new Test Execution
     */
    public static void uploadResult(String token) {

        File cucumberReport =
                new File("target/cucumber-report/cucumber.json");

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(cucumberReport)
                .post(XRAY_UPLOAD_URL);

        System.out.println("Xray response:");
        System.out.println(response.asPrettyString());
    }

    /**
     * Upload results INTO an EXISTING Test Execution
     */
    public static void uploadResultToExecution(
            String token,
            String testExecutionKey,
            File resultFile) {

        File cucumberReport =
                new File("target/cucumber-report/cucumber.json");

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .queryParam("testExecutionKey", testExecutionKey)
                .body(cucumberReport)
                .post(XRAY_UPLOAD_URL);

        System.out.println("Results uploaded to execution: "
                + testExecutionKey);
        System.out.println(response.asPrettyString());
    }
}

