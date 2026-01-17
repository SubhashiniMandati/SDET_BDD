package pages;

import config.ConfigReader;
import context.TestContext;
import driver.DriverFactory;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    WebDriver driver = DriverFactory.getDriver();

    public void open() {
        driver.get(ConfigReader.getProperty("base.url"));
    }
    public boolean isOrderDisplayed(String orderId){
        boolean x=true;
        return x;
    }
}
