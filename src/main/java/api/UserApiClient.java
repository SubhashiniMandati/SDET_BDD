package api;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class UserApiClient {
    public static Response createUser() {
//        return RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body("{ \"name\": \"testUser\" }")
//                .post("/users");
        Response response = given().relaxedHTTPSValidation().when().urlEncodingEnabled(true)
                .baseUri("https://api.restful-api.dev/").basePath("objects/7")
                .get()
                .then().log().all().assertThat().extract().response();
        return response;


    }
}
