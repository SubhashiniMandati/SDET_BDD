package validations;

import static context.TestContext.response;
import static utils.SoftAssertManager.getSoftAssert;

public class ApiResponseValidator {
    public static void validateId(String id, String eid) {
        getSoftAssert().assertEquals(id, eid, "Id mismatch");
    }
    public static void validateName(String actualName, String expectedName) {
        getSoftAssert().assertEquals(actualName, expectedName, "Name mismatch");
    }
    public static void validateYear(String actualYear, String expectedYear) {
        getSoftAssert().assertEquals(actualYear, expectedYear, "Year mismatch");
    }
    public static void validatePrice(String actualPrice, String expectedPrice) {
        getSoftAssert().assertEquals(actualPrice, expectedPrice, "Price mismatch");
    }
    public static void assertNotNull(String key) {
        getSoftAssert().assertNotNull(key, key +"is not null");
    }
    public static void assertEquals(String actualValue, String expectedValue) {
        getSoftAssert().assertEquals(actualValue, expectedValue,  "expected value" +expectedValue + "actualValue" +actualValue );
    }
}
