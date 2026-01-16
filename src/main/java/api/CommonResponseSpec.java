package api;

import io.restassured.filter.Filter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import static utils.api.ApiLoggingUtil.getLoggingFilter;

public class CommonResponseSpec {
public static Response commonResponseGet(String baseUrl, String basePath, HashMap<String ,String> headers, RequestSpecification requestSpecification){
    Response response=requestSpecification.relaxedHTTPSValidation()
        .when()
        .urlEncodingEnabled(false)
        .filters(getLoggingFilter())
        .baseUri(baseUrl)
        .basePath(basePath)
        .headers(headers)
        .get().then()
        .assertThat()
        .extract().response();
    return response;
    }

    public static Response commonResponsePost(String baseUrl, String basePath, HashMap<String ,String> headers, RequestSpecification requestSpecification){
        Response response=requestSpecification.relaxedHTTPSValidation()
                .when()
                .urlEncodingEnabled(false)
                .filters(getLoggingFilter())
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers)
                .post().then()
                .assertThat()
                .extract().response();
        return response;
    }
    public static Response commonResponsePut(String baseUrl, String basePath, HashMap<String ,String> headers, RequestSpecification requestSpecification){
        Response response=requestSpecification.relaxedHTTPSValidation()
                .when()
                .urlEncodingEnabled(false)
                .filters(getLoggingFilter())
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers)
                .put().then()
                .assertThat()
                .extract().response();
        return response;
    }
    public static Response commonResponsePatch(String baseUrl, String basePath, HashMap<String ,String> headers, RequestSpecification requestSpecification){
        Response response=requestSpecification.relaxedHTTPSValidation()
                .when()
                .urlEncodingEnabled(false)
                .filters(getLoggingFilter())
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers)
                .patch().then()
                .assertThat()
                .extract().response();
        return response;
    }
    public static Response commonResponseDelete(String baseUrl, String basePath, HashMap<String ,String> headers, RequestSpecification requestSpecification){
        Response response=requestSpecification.relaxedHTTPSValidation()
                .when()
                .urlEncodingEnabled(false)
                .filters(getLoggingFilter())
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers)
                .delete().then()
                .assertThat()
                .extract().response();
        return response;
    }
}
