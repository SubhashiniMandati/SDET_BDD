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
}
