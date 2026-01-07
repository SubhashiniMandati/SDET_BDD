package XrayIntegration;

import config.ConfigReader;

public class XrayExecutionManager {

    public static void main(String[] args) throws Exception {
        exportFeaturesForExecution();
        System.out.println("✅ Features exported successfully for execution ");
    }
    public static void exportFeaturesForExecution() throws Exception {

        String executionKey = ConfigReader.getProperty("xray.execution.key");
        String token = XrayAuthClient.getAuthToken();

        XrayFeatureExporter.exportFeatures(token, executionKey);
        FeatureUnzipper.unzipFeatures();
        System.out.println("✅ Features exported successfully for execution " + executionKey);
    }
}
//mvn test -Dxray.export=true
//from jenkins
// java -cp target/test-classes xray.XrayExecutionManager

