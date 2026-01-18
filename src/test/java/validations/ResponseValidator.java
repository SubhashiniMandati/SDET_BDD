package validations;

import static utils.SoftAssertManager.getSoftAssert;

public class ResponseValidator {
    public static void assertNotNull(String key) {
        getSoftAssert().assertNotNull(key, key +"is not null");
    }
    public static void assertEquals(String actualValue, String expectedValue) {
        getSoftAssert().assertEquals(actualValue, expectedValue,  "expected value" +expectedValue + "actualValue" +actualValue );
    }
    public static void assertTrue(Boolean key){
        getSoftAssert().assertTrue(key, key +" Assert True validation");
    }
}
