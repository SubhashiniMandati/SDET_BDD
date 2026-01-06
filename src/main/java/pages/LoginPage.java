package pages;

import config.ConfigReader;
import context.TestContext;
import driver.DriverFactory;
import org.openqa.selenium.*;

public class LoginPage {

    WebDriver driver = DriverFactory.getDriver();

   private By username = By.id("email");
   private By password = By.id("pass");

    public void openLoginPage() {
        driver.get(ConfigReader.getProperty(TestContext.ENV+".base.url"));
    }

    public void login(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
    }

    public boolean isHomePageDisplayed() {
        return driver.getTitle().contains("Face");
    }
    public boolean isHomePageDisplayed01() {
        return driver.getTitle().contains("ADFace");
    }

}

