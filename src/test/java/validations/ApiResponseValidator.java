package validations;

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
}
