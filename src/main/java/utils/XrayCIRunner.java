package utils;

import XrayIntegration.*;
import config.ConfigReader;

public class XrayCIRunner {

    public static void main(String[] args) throws Exception {

        String executionKey = ConfigReader.getProperty("XrayKey");

        String token = XrayAuthClient.getAuthToken();

        XrayFeatureExporter.exportFeatures(token, executionKey);
        FeatureUnzipper.unzipFeatures();

        // Now run: mvn test (outside Java)
        // After test completion:
        XrayResultUploader.uploadResultToExecution(token, executionKey);
    }
}


