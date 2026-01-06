package context;

import config.ConfigReader;

public class TestContext {
    public static final String ENV = ConfigReader.getProperty("env");
    public String userId;
    public String orderId;
    public String authToken;
}

