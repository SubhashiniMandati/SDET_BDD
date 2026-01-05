package stepdefinitions;

import context.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.DashboardPage;

public class UiSteps {

    private TestContext context;
    DashboardPage dashboard;

    public UiSteps(TestContext context) {
        this.context = context;
        dashboard = new DashboardPage();
    }

    @When("user logs in via UI")
    public void login() {
        dashboard.open();
    }

    @Then("order should be visible in dashboard")
    public void verify_order() {
        Assert.assertTrue(
                dashboard.isOrderDisplayed(context.orderId)
        );
    }
}

