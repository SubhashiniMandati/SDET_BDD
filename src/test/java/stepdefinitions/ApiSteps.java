package stepdefinitions;

import api.ApiResponse;
import config.ConfigReader;
import context.TestContext;
import io.cucumber.java.en.*;
import log.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.HashMap;

public class ApiSteps {
    private static final Logger log =
            LoggerUtil.getLogger(ApiSteps.class);
    private TestContext context;
    String id;

    public ApiSteps(TestContext context) {
        this.context = context;
    }

    @Given("user get object via API")
    public void user_get_object_via_api() {
        HashMap<String, String> headers= new HashMap<>();
        context.response= ApiResponse.getApiResponse(ConfigReader.getProperty("qc.api_base_url"),ConfigReader.getProperty("qc.api_base_path"),headers, "");
        log.info("user get object via API");
    }
    @Then("validate object response")
    public void validate_object_response() {
        Assert.assertEquals(context.response.jsonPath().get("id"), "7" );
        Assert.assertEquals(context.response.jsonPath().get("name"), "Apple MacBook Pro 16" );
        Object year = context.response.jsonPath().get("data.year");
       Assert.assertEquals(String.valueOf(year), "2019" );
        Object price = context.response.jsonPath().get("data.price");
        Assert.assertEquals(String.valueOf(price), "1849.99" );
        //Assert.assertEquals(context.response.jsonPath().get("data.CPUmodel"), "Intel Core i9" );
        //Assert.assertEquals(context.response.jsonPath().get("data.Hard disk size"), "1 TB" );
    }

    @Given("user get list of object via API")
    public void user_get_list_of_objects(){
        HashMap<String, String> headers= new HashMap<>();
        context.response= ApiResponse.getApiResponse(ConfigReader.getProperty("qc.api_base_url"),ConfigReader.getProperty("qc.get_objects"),headers, "");
        log.info("user get list of object via API");
    }
    @Then("validate specific object response")
    public void validate_specific_object_response() {
        Assert.assertEquals(context.response.jsonPath().getString("[6].id"), "7" );
        Assert.assertEquals(context.response.jsonPath().getString("[6].name"), "Apple MacBook Pro 16" );
        Object year = context.response.jsonPath().get("[6].data.year");
        Assert.assertEquals(String.valueOf(year), "2019" );
        Object price = context.response.jsonPath().get("[6].data.price");
        Assert.assertEquals(String.valueOf(price), "1849.99" );
        //Assert.assertEquals(context.response.jsonPath().get("data.CPUmodel"), "Intel Core i9" );
        //Assert.assertEquals(context.response.jsonPath().get("data.Hard disk size"), "1 TB" );
    }

    @Given("object is created via API")
    public void create_user() {
        HashMap<String, String> headers= new HashMap<>();
        String body = """
{
  "name": "Apple MacBook Pro 146",
  "data": {
    "year": 2019,
    "price": 1849.99,
    "CPU model": "Intel Core i9",
    "Hard disk size": "1 TB"
  }
}
""";
        context.response= ApiResponse.postApiResponse(ConfigReader.getProperty("qc.api_base_url"),ConfigReader.getProperty("qc.get_objects"),headers, body,"");
        log.info("object is created via API");
    }

    @Then("object is created")
    public void object_is_created() {
        log.info("object is created" +context.response.jsonPath().getString("id"));
        Assert.assertEquals(context.response.jsonPath().getString("name"), "Apple MacBook Pro 146" );
        Assert.assertNotNull(context.response.jsonPath().getString("createdAt"));
        Assert.assertEquals(context.response.jsonPath().getString("data.year"), "2019" );
    }
    @Given("object is updated via API")
    public void update_object() {
        String id = context.response.jsonPath().getString("id");
        HashMap<String, String> headers= new HashMap<>();
        String body = """
{
   "name": "Apple MacBook Pro 16",
   "data": {
      "year": 2019,
      "price": 2049.99,
      "CPU model": "Intel Core i9",
      "Hard disk size": "1 TB",
      "color": "silver"
   }
}
""";
        context.response= ApiResponse.putApiResponse(ConfigReader.getProperty("qc.api_base_url"),ConfigReader.getProperty("qc.get_objects")+"/"+id,headers, body,"");
        log.info("object is updated via API");
    }
    @Then("object details are updated")
    public void object_details_are_updated() {
        log.info("object is updated" +context.response.jsonPath().getString("id"));
        Assert.assertEquals(context.response.jsonPath().getString("name"), "Apple MacBook Pro 16" );
        Assert.assertNotNull(context.response.jsonPath().getString("updatedAt"));
        Assert.assertEquals(context.response.jsonPath().getString("data.year"), "2019" );
        Assert.assertEquals(context.response.jsonPath().getString("data.color"), "silver" );
    }
    @Given("object is partially updated via API")
    public void partially_update_object() {
        String id = context.response.jsonPath().getString("id");
        HashMap<String, String> headers= new HashMap<>();
        String body = """
{
   "name": "Apple MacBook Pro 186"
}
""";
        context.response= ApiResponse.patchApiResponse(ConfigReader.getProperty("qc.api_base_url"),ConfigReader.getProperty("qc.get_objects")+"/"+id,headers, body,"");
        log.info("object is partially updated via API");
    }
    @Then("object details are partially updated")
    public void object_details_are_partially_updated() {
        log.info("object is updated" +context.response.jsonPath().getString("id"));
        Assert.assertEquals(context.response.jsonPath().getString("name"), "Apple MacBook Pro 186" );
        Assert.assertNotNull(context.response.jsonPath().getString("updatedAt"));
    }
    @When("object is deleted via API")
    public void object_is_deleted_via_api(){
        id = context.response.jsonPath().getString("id");
        HashMap<String, String> headers= new HashMap<>();
        context.response= ApiResponse.deleteApiResponse(ConfigReader.getProperty("qc.api_base_url"),ConfigReader.getProperty("qc.get_objects")+"/"+id,headers,"");
        log.info("object is deleted via API");
    }
    @Then("verify object is deleted")
    public void object_is_deleted() {
        String message = context.response.jsonPath().getString("message");
        log.info("object is deleted" +context.response.jsonPath().getString("message"));
        Assert.assertEquals(message,  "Object with id = "+id+" has been deleted." );
    }
}

