package utils.web;

import driver.DriverFactory;
import org.openqa.selenium.*;
public class ScreenhotUtil {
    public static String getBase64Screenshot() {
        try {
            WebDriver driver = DriverFactory.getDriver();
            return ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
