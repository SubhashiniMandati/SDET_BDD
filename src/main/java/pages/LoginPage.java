package pages;

import config.ConfigReader;
import driver.DriverFactory;
import org.openqa.selenium.*;

public class LoginPage {

    WebDriver driver = DriverFactory.getDriver();

    By username = By.id("email");
    By password = By.id("pass");
    By loginBtn = By.id("loginbutton");

    public void openLoginPage() {
        driver.get(ConfigReader.getBaseUrl());
    }

    public void login(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
      //aitUtils.waitForClickable(loginBtn).click();
    }

    public boolean isHomePageDisplayed() {
        return driver.getTitle().contains("Face");
    }
    public boolean isHomePageDisplayed01() {
        return driver.getTitle().contains("ADFace");
    }

}

