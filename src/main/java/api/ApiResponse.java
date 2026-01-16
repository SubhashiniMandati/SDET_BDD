package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiResponse {
public static Response getApiResponse(String baseUrl, String basePath, HashMap<String, String> headers, String ResponseSchemaFileName){
    RequestSpecification requestSpecification = given();
    return CommonResponseSpec.commonResponseGet(baseUrl,basePath, headers, requestSpecification);
}
    public static Response postApiResponse(String baseUrl, String basePath, HashMap<String, String> headers,String body, String ResponseSchemaFileName){
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON)
                .body(body)
                .relaxedHTTPSValidation();
        return CommonResponseSpec.commonResponsePost(baseUrl,basePath, headers, requestSpecification);
    }
    public static Response putApiResponse(String baseUrl, String basePath, HashMap<String, String> headers,String body, String ResponseSchemaFileName){
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON)
                .body(body)
                .relaxedHTTPSValidation();
        return CommonResponseSpec.commonResponsePut(baseUrl,basePath, headers, requestSpecification);
    }
    public static Response patchApiResponse(String baseUrl, String basePath, HashMap<String, String> headers,String body, String ResponseSchemaFileName){
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON)
                .body(body)
                .relaxedHTTPSValidation();
        return CommonResponseSpec.commonResponsePatch(baseUrl,basePath, headers, requestSpecification);
    }
    public static Response deleteApiResponse(String baseUrl, String basePath, HashMap<String, String> headers, String ResponseSchemaFileName){
        RequestSpecification requestSpecification = given();
        return CommonResponseSpec.commonResponseDelete(baseUrl,basePath, headers, requestSpecification);
    }
}
