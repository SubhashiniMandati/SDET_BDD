package utils;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static WebDriver driver = DriverFactory.getDriver();

    private static WebDriverWait getWait() {
        return new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        config.ConfigReader.getExplicitWait()
                )
        );
    }

    public static WebElement waitForVisibility(By locator) {
        return getWait().until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static WebElement waitForClickable(By locator) {
        return getWait().until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public static void waitForUrlContains(String value) {
        getWait().until(ExpectedConditions.urlContains(value));
    }

    public static void waitForTitleContains(String value) {
        getWait().until(ExpectedConditions.titleContains(value));
    }
}
