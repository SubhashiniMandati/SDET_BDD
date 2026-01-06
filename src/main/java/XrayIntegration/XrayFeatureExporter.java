package XrayIntegration;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class XrayFeatureExporter {

    private static final String EXPORT_URL =
            "https://xray.cloud.getxray.app/api/v2/export/cucumber";

    public static void exportFeatures(String token, String testExecutionKey)
            throws IOException {

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .queryParam("testExecutionKey", testExecutionKey)
                .get(EXPORT_URL);

        byte[] zipBytes = response.asByteArray();

        File zipFile = new File("target/xray-features.zip");
        try (FileOutputStream fos = new FileOutputStream(zipFile)) {
            fos.write(zipBytes);
        }

        System.out.println("Feature files exported for execution: "
                + testExecutionKey);
    }
}

