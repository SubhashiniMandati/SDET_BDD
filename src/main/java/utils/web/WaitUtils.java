package utils.web;

import config.ConfigReader;
import driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitUtils {
    public static void applyTimeouts(WebDriver driver) {

        int implicitWait = Integer.parseInt(
                ConfigReader.getProperty("implicit.wait"));

        int pageLoadTimeout = Integer.parseInt(
                ConfigReader.getProperty("page.load.timeout"));

        int scriptTimeout = Integer.parseInt(
                ConfigReader.getProperty("script.timeout"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(scriptTimeout));

    }
    private static WebDriverWait getWait() {

        int timeout = Integer.parseInt(
                ConfigReader.getProperty("explicit.wait"));

        int polling = Integer.parseInt(
                ConfigReader.getProperty("polling.interval"));

        return new WebDriverWait(
                DriverFactory.getDriver(),
                Duration.ofSeconds(timeout),
                Duration.ofMillis(polling)
        );
    }
    /* ================= VISIBILITY ================= */

    public static WebElement waitForVisible(By locator) {
        return getWait().until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForVisible(WebElement element) {
        return getWait().until(
                ExpectedConditions.visibilityOf(element));
    }

    /* ================= CLICKABLE ================= */

    public static WebElement waitForClickable(By locator) {
        return getWait().until(
                ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForClickable(WebElement element) {
        return getWait().until(
                ExpectedConditions.elementToBeClickable(element));
    }

    /* ================= PRESENCE ================= */

    public static WebElement waitForPresence(By locator) {
        return getWait().until(
                ExpectedConditions.presenceOfElementLocated(locator));
    }

    /* ================= INVISIBILITY ================= */

    public static boolean waitForInvisible(By locator) {
        return getWait().until(
                ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /* ================= TEXT ================= */

    public static boolean waitForText(By locator, String text) {
        return getWait().until(
                ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /* ================= FRAME ================= */

    public static void waitForFrame(By locator) {
        getWait().until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    /* ================= URL ================= */

    public static boolean waitForUrlContains(String fraction) {
        return getWait().until(
                ExpectedConditions.urlContains(fraction));
    }

    /* ================= ALERT ================= */

    public static Alert waitForAlert() {
        return getWait().until(
                ExpectedConditions.alertIsPresent());
    }

}
