package driver;

import config.ConfigReader;
import log.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static utils.web.WaitUtils.applyTimeouts;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger log =
            LoggerUtil.getLogger(DriverFactory.class);

    public static WebDriver getDriver() {
        return driver.get();
    }

    // ✅ SINGLE ENTRY POINT
    public static void initDriver(String scenarioName) {

        if (driver.get() != null) {
            log.warn("Driver already initialized");
            return;
        }

        String mode = ConfigReader.getProperty("execution.mode");
        String browser = ConfigReader.getProperty("browser");

        log.info("Initializing WebDriver | mode={} | browser={}", mode, browser);

        try {
            WebDriver webDriver;

            switch (mode.toLowerCase()) {
                case "grid":
                    webDriver = createGridDriver(browser);
                    break;

                case "browserstack":
                    webDriver = createBrowserStackDriver(browser, scenarioName);
                    break;

                case "local":
                default:
                    webDriver = createLocalDriver(browser);
            }
            applyTimeouts(webDriver);
            driver.set(webDriver);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }

    // 🔹 LOCAL EXECUTION
    private static WebDriver createLocalDriver(String browser) {

        switch (browser.toLowerCase()) {
            case "firefox":
                log.info("Starting local Firefox");
                return new FirefoxDriver(new FirefoxOptions());

            case "chrome":
            default:
                log.info("Starting local Chrome");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                return new ChromeDriver(options);
        }
    }

    // 🔹 SELENIUM GRID
    private static WebDriver createGridDriver(String browser) throws Exception {

        URL gridUrl = new URL(ConfigReader.getProperty("grid.url"));

        log.info("Starting Grid execution on {}", gridUrl);

        switch (browser.toLowerCase()) {
            case "firefox":
                return new RemoteWebDriver(gridUrl, new FirefoxOptions());

            case "chrome":
            default:
                return new RemoteWebDriver(gridUrl, new ChromeOptions());
        }
    }

    // 🔹 BROWSERSTACK CLOUD
    private static WebDriver createBrowserStackDriver(
            String browser,
            String scenarioName) throws Exception {

        String username = ConfigReader.getProperty("browserstack.username");
        String accessKey = ConfigReader.getProperty("browserstack.accesskey");

        URL bsUrl = new URL(
                "https://" + username + ":" + accessKey +
                        "@hub-cloud.browserstack.com/wd/hub");

        MutableCapabilities caps = new MutableCapabilities();

        // Browser
        caps.setCapability("browserName", browser);
        caps.setCapability("browserVersion", "latest");

        // BrowserStack Options
        MutableCapabilities bsOptions = new MutableCapabilities();
        bsOptions.setCapability("os", "Windows");
        bsOptions.setCapability("osVersion", "11");
        bsOptions.setCapability("projectName", "Cucumber Automation");
        bsOptions.setCapability("buildName", "Build-" + System.currentTimeMillis());
        bsOptions.setCapability("sessionName", scenarioName);

        caps.setCapability("bstack:options", bsOptions);

        log.info("Starting BrowserStack session for scenario: {}", scenarioName);

        return new RemoteWebDriver(bsUrl, caps);
    }

    // 🔹 CLEANUP
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
