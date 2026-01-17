package config;

import context.TestContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private static TestContext context;

    static {
        properties = new Properties();
        try (
                InputStream configStream = Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("config.properties");
                InputStream envStream = Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(context.ENV+".properties")
        ) {
            if (configStream == null || envStream == null) {
                throw new RuntimeException("One or more property files not found in classpath");
            }

            properties.load(configStream);
            properties.load(envStream);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load property files", e);
        }
    }
    public static String getProperty(String key) {

        String sysValue = System.getProperty(key);
        return (sysValue != null && !sysValue.isEmpty())
                ? sysValue
                : properties.getProperty(key);
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }
}
