package validations;

import static utils.SoftAssertManager.getSoftAssert;

public class ApiResponseValidator {
    public static void assertNotNull(String key) {
        getSoftAssert().assertNotNull(key, key +"is not null");
    }
    public static void assertEquals(String actualValue, String expectedValue) {
        getSoftAssert().assertEquals(actualValue, expectedValue,  "expected value" +expectedValue + "actualValue" +actualValue );
    }
}
