package stepdefinitions;

import api.ApiResponse;
import api.PayloadReader;
import com.fasterxml.jackson.databind.JsonNode;
import config.ConfigReader;
import context.TestContext;
import io.cucumber.java.en.*;
import log.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import validations.ApiResponseValidator;

import java.io.IOException;
import java.util.HashMap;

import static context.TestContext.response;
import static utils.SoftAssertManager.getSoftAssert;

public class ApiSteps {
    private static final Logger log = LoggerUtil.getLogger(ApiSteps.class);
    private TestContext context;
    String id;
    JsonNode root;

    public ApiSteps(TestContext context) throws IOException {
        this.context = context;
        root = PayloadReader.read(context.ENV, "objectPayloads.json");;
    }

    @Given("user get object via API")
    public void user_get_object_via_api() {
        HashMap<String, String> headers= new HashMap<>();
        response= ApiResponse.getApiResponse(ConfigReader.getProperty("api_base_url"),ConfigReader.getProperty("api_base_path"),headers, "");
        log.info("user get object via API");
    }
    @Then("validate specific object response")
    public void validate_specific_object_response() {
        // Read GET payload
        JsonNode getPayload = root.get("get");
        ApiResponseValidator.validateId(response.jsonPath().getString("id"), "8");
        ApiResponseValidator.validateName(response.jsonPath().getString("name"), "abc");
        Object year = response.jsonPath().get("data.year");
        ApiResponseValidator.validateYear(String.valueOf(year), getPayload.get("data").get("price").asText());
        Object price = response.jsonPath().get("data.price");
        ApiResponseValidator.validateYear(String.valueOf(price), getPayload.get("data").get("year").asText());
    }

    @Given("user get list of object via API")
    public void user_get_list_of_objects(){
        HashMap<String, String> headers= new HashMap<>();
        response= ApiResponse.getApiResponse(ConfigReader.getProperty("api_base_url"),ConfigReader.getProperty("get_objects"),headers, "");
        log.info("user get list of object via API");
    }

    @Then("validate object response")
    public void validate_object_response() {
        // Read GET payload
        JsonNode getPayload = root.get("get");
        Assert.assertEquals(response.jsonPath().getString("[6].id"), "7" );
        Assert.assertEquals(response.jsonPath().getString("[6].name"), getPayload.get("name").asText());
        Object year = response.jsonPath().get("[6].data.year");
        Assert.assertEquals(String.valueOf(year), getPayload.get("data").get("year").asText() );
        Object price = response.jsonPath().get("[6].data.price");
        Assert.assertEquals(String.valueOf(price), getPayload.get("data").get("price").asText() );
    }

    @Given("object is created via API")
    public void create_user() {
        HashMap<String, String> headers= new HashMap<>();
        // Read CREATE payload
        JsonNode createPayload = root.get("create");
        response= ApiResponse.postApiResponse(ConfigReader.getProperty("api_base_url"),ConfigReader.getProperty("get_objects"),headers, createPayload.toString(),"");
        log.info("object is created via API");
    }

    @Then("object is created")
    public void object_is_created() {
        log.info("object is created" + response.jsonPath().getString("id"));
        // Read CREATE payload
        JsonNode createPayload = root.get("create");
        Assert.assertEquals(response.jsonPath().getString("name"), createPayload.get("name").asText() );
        Assert.assertNotNull(response.jsonPath().getString("createdAt"));
        Assert.assertEquals(response.jsonPath().getString("data.year"), createPayload.get("data").get("year").asText() );
    }
    @Given("object is updated via API")
    public void update_object() {
        String id = response.jsonPath().getString("id");
        HashMap<String, String> headers= new HashMap<>();
        // Read UPDATE payload
        JsonNode updatePayload = root.get("update");
        response= ApiResponse.putApiResponse(ConfigReader.getProperty("api_base_url"),ConfigReader.getProperty("get_objects")+"/"+id,headers, updatePayload.toString(),"");
        log.info("object is updated via API");
    }
    @Then("object details are updated")
    public void object_details_are_updated() {
        // Read UPDATE payload
        JsonNode updatePayload = root.get("update");
        log.info("object is updated" + response.jsonPath().getString("id"));
        Assert.assertEquals(response.jsonPath().getString("name"), updatePayload.get("name").asText() );
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"));
        Assert.assertEquals(response.jsonPath().getString("data.year"), updatePayload.get("data").get("year").asText() );
        Assert.assertEquals(response.jsonPath().getString("data.color"), updatePayload.get("data").get("color").asText() );
    }
    @Given("object is partially updated via API")
    public void partially_update_object() {
        String id = response.jsonPath().getString("id");
        HashMap<String, String> headers= new HashMap<>();
        // Read Patch UPDATE payload
        JsonNode patchUpdatePayload = root.get("patchUpdate");
        response= ApiResponse.patchApiResponse(ConfigReader.getProperty("api_base_url"),ConfigReader.getProperty("get_objects")+"/"+id,headers, patchUpdatePayload.toString() ,"");
        log.info("object is partially updated via API");
    }
    @Then("object details are partially updated")
    public void object_details_are_partially_updated() {
        // Read Patch UPDATE payload
        JsonNode patchUpdatePayload = root.get("patchUpdate");
        log.info("object is updated" + response.jsonPath().getString("id"));
        Assert.assertEquals(response.jsonPath().getString("name"), patchUpdatePayload.get("name").asText() );
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"));
    }
    @When("object is deleted via API")
    public void object_is_deleted_via_api(){
        id = response.jsonPath().getString("id");
        HashMap<String, String> headers= new HashMap<>();
        response= ApiResponse.deleteApiResponse(ConfigReader.getProperty("api_base_url"),ConfigReader.getProperty("get_objects")+"/"+id,headers,"");
        log.info("object is deleted via API");
    }
    @Then("verify object is deleted")
    public void object_is_deleted() {
        String message = response.jsonPath().getString("message");
        log.info("object is deleted" + response.jsonPath().getString("message"));
        Assert.assertEquals(message,  "Object with id = "+id+" has been deleted.123" );
    }
}

