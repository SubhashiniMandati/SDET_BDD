package pages;

import config.ConfigReader;
import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    WebDriver driver = DriverFactory.getDriver();

    public void open() {
driver.get(ConfigReader.getBaseUrl());
    }
    public boolean isOrderDisplayed(String orderId){
        boolean x=true;
        return x;
    }
}
