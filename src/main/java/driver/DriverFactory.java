package driver;

import config.ConfigReader;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.LoggerUtil;

import java.net.URL;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger log =
            LoggerUtil.getLogger(DriverFactory.class);

    // ❌ DO NOT CREATE DRIVER HERE
    public static WebDriver getDriver() {
        return driver.get();
    }

    // ✅ CREATE DRIVER ONLY ONCE
    public static void initDriver() {

        if (driver.get() != null) {
            log.warn("Driver already initialized");
            return;
        }

        String mode = ConfigReader.getProperty("execution.mode");
        String browser = ConfigReader.getProperty("browser");

        log.info("Initializing WebDriver | mode={} | browser={}", mode, browser);

        try {
            WebDriver webDriver;

            if ("remote".equalsIgnoreCase(mode)) {
                webDriver = createRemoteDriver(browser);
            } else {
                webDriver = createLocalDriver(browser);
            }

            driver.set(webDriver);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }

    private static WebDriver createLocalDriver(String browser) {

        switch (browser.toLowerCase()) {
            case "firefox":
                return new FirefoxDriver(new FirefoxOptions());

            case "chrome":
            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                return new ChromeDriver(options);
        }
    }

    private static WebDriver createRemoteDriver(String browser) throws Exception {

        URL gridUrl = new URL(ConfigReader.getProperty("grid.url"));

        switch (browser.toLowerCase()) {
            case "firefox":
                return new RemoteWebDriver(gridUrl, new FirefoxOptions());

            case "chrome":
            default:
                return new RemoteWebDriver(gridUrl, new ChromeOptions());
        }
    }

    public static void quitDriver() {
        log.info("Attempting to quit WebDriver");

        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            log.info("WebDriver quit successfully");
        } else {
            log.warn("Driver already null, nothing to quit");
        }
    }
}
