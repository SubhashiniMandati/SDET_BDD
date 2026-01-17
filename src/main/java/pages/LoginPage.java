package pages;

import config.ConfigReader;
import driver.DriverFactory;
import org.openqa.selenium.*;
import utils.web.WaitUtils;

public class LoginPage {

    WebDriver driver = DriverFactory.getDriver();

   private By username = By.id("email");
   private By password = By.id("pass");

    public void openLoginPage() {
        driver.get(ConfigReader.getProperty("base.url"));
    }

    public void login(String user, String pass) {
        WaitUtils.waitForVisible(username).sendKeys(user);
        WaitUtils.waitForVisible(password).sendKeys(pass);
    }

    public boolean isHomePageDisplayed() {
        return driver.getTitle().contains("Face");
    }
    public boolean isHomePageDisplayed01() {
        return driver.getTitle().contains("ADFace");
    }

}

