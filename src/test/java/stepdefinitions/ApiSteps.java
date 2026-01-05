package stepdefinitions;

import api.UserApiClient;
import context.TestContext;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class ApiSteps {

    private TestContext context;

    public ApiSteps(TestContext context) {
        this.context = context;
    }

    @Given("user is created via API")
    public void create_user() {
        Response res = UserApiClient.createUser();
        context.userId = res.jsonPath().get("id");
    }
}

