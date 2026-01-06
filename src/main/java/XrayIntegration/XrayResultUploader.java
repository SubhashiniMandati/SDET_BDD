package XrayIntegration;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;

public class XrayResultUploader {

    private static final String XRAY_UPLOAD_URL =
            "https://xray.cloud.getxray.app/api/v2/import/execution/cucumber";

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
            String testExecutionKey) {

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
