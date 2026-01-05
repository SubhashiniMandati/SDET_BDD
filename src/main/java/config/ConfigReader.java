package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            FileInputStream fis =
                    new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public static String getProperty(String key) {
        return System.getProperty(key, properties.getProperty(key));
    }

    public static String getBaseUrl() {
        String env = getProperty("env");
        return getProperty(env + ".base.url");
    }

    public static boolean isGridEnabled() {
        return Boolean.parseBoolean(getProperty("grid.enabled"));
    }

    public static String getGridUrl() {
        return getProperty("grid.url");
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }
    /**
     * Reads value from:
     * 1. System property (mvn -Dkey=value)
     * 2. config.properties (fallback)
     */
    public static String get(String key) {
        String sysValue = System.getProperty(key);
        return (sysValue != null && !sysValue.isEmpty())
                ? sysValue
                : properties.getProperty(key);
    }
}
