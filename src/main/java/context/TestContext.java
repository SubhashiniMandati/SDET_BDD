package context;

import io.restassured.response.Response;

public class TestContext {
    public static final String platform = System.getProperty("platform", "web");
    public static final String ENV = System.getProperty("env", "qc");
    public static Response response;
}

