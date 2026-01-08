package stepdefinitions;

import config.ConfigReader;
import context.TestContext;
import io.cucumber.java.en.*;
import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import org.testng.Assert;
import pages.LoginPage;
import org.apache.logging.log4j.Logger;
import log.LoggerUtil;


public class LoginSteps{
    LoginPage loginPage = new LoginPage();
    private static final Logger log =
            LoggerUtil.getLogger(LoginSteps.class);
    @Given("user is on login page")
    public void user_on_login_page() {
        loginPage.openLoginPage();
        log.info("Navigating to login page");

    }

    @When("user enters valid credentials")
    public void enter_credentials() {
        loginPage.login(ConfigReader.getProperty(TestContext.ENV+".username"),ConfigReader.getProperty(TestContext.ENV+".password"));
        log.info("Entering valid credentials");
    }

    @Then("user should land on home page")
    public void verify_home_page()
    {
        Assert.assertTrue(loginPage.isHomePageDisplayed());
        log.info("Verifying home page");
    }
    @Then("user should land on home page01")
    public void verify_home_page_01()
    {
        Assert.assertTrue(loginPage.isHomePageDisplayed01());
        log.info("Verifying home page");
    }

}
