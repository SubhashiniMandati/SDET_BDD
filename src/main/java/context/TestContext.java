package context;

import config.ConfigReader;
import io.restassured.response.Response;

public class TestContext {
    public static final String platform = ConfigReader.getProperty("platform");
    public static final String ENV = ConfigReader.getProperty("env");
    public static Response response;
    public String userId;
    public String orderId;
    public String authToken;
}

