package XrayIntegration;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class XrayAuthClient {

    private static final String AUTH_URL = ConfigReader.getProperty("Xray_BaseUrl")+ConfigReader.getProperty("Xray_AuthEndPoint");

    public static String getAuthToken() {

        Map<String, String> body = new HashMap<>();
        body.put("client_id",ConfigReader.getProperty("Xray_CLIENT_ID"));
        body.put("client_secret", ConfigReader.getProperty("Xray_CLIENT_SECRET"));

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .post(AUTH_URL);

        return response.getBody().asString().replace("\"", "");
    }
}

