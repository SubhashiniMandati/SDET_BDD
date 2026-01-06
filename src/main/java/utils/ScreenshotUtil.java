//package utils;
//
//import org.openqa.selenium.*;
//import java.nio.file.*;
//
//public class ScreenshotUtil {
//
//    public static String captureAndReturnPath(WebDriver driver, String name) {
//
//        if (driver == null) return null;
//
//        try {
//            TakesScreenshot ts = (TakesScreenshot) driver;
//            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
//
//            Path path = Paths.get("test-output/screenshots/" + name + ".png");
//            Files.createDirectories(path.getParent());
//            Files.write(path, screenshot);
//
//            return path.toString();
//
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}
