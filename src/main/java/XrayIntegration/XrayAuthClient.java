package XrayIntegration;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class XrayAuthClient {

    private static final String AUTH_URL =
            "https://xray.cloud.getxray.app/api/v2/authenticate";

    public static String getAuthToken() {

        Map<String, String> body = new HashMap<>();
        body.put("client_id", "YOUR_CLIENT_ID");
        body.put("client_secret", "YOUR_CLIENT_SECRET");

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .post(AUTH_URL);

        return response.getBody().asString().replace("\"", "");
    }
}

