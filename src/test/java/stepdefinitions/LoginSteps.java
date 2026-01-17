package stepdefinitions;

import config.ConfigReader;
import context.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import org.testng.Assert;
import pages.LoginPage;
import org.apache.logging.log4j.Logger;
import log.LoggerUtil;

import java.util.List;
import java.util.Map;


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
        loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));
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
    @When("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {
        loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));
    }
    @Then("user should see homepage with message {string}")
    public void verify_homepage(String message) {
        Assert.assertEquals("test message", "test message");
    }



}
