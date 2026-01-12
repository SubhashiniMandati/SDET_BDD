package stepdefinitions;

import api.ApiResponse;
import config.ConfigReader;
import context.TestContext;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import log.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class ApiSteps {
    private static final Logger log =
            LoggerUtil.getLogger(ApiSteps.class);
    private TestContext context;

    public ApiSteps(TestContext context) {
        this.context = context;
    }

    @Given("user is created via API")
    public void create_user() {
        HashMap<String, String> headers= new HashMap<>();
        Response response= ApiResponse.getApiResponse(ConfigReader.getProperty("qc.api_base_url"),ConfigReader.getProperty("qc.api_base_path"),headers, "");
        context.userId = response.jsonPath().get("id");
        log.info("user is created via API");
    }

    @Given("user is displayed")
    public void user_is_displayed() {
        log.info("user is displayed " +context.userId);
    }
}

