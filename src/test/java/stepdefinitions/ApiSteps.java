package stepdefinitions;

import api.UserApiClient;
import context.TestContext;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import log.LoggerUtil;
import org.apache.logging.log4j.Logger;

public class ApiSteps {
    private static final Logger log =
            LoggerUtil.getLogger(ApiSteps.class);
    private TestContext context;

    public ApiSteps(TestContext context) {
        this.context = context;
    }

    @Given("user is created via API")
    public void create_user() {
        Response res = UserApiClient.createUser();
        context.userId = res.jsonPath().get("id");
        log.info("user is created via API");
    }

    @Given("user is displayed")
    public void user_is_displayed() {
        log.info("user is displayed" +context.userId);
    }
}

