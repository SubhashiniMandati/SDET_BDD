package utils;

import org.testng.asserts.SoftAssert;

public class SoftAssertManager {
    private static final ThreadLocal<SoftAssert> softAssert =
            ThreadLocal.withInitial(SoftAssert::new);

    public static SoftAssert getSoftAssert() {
        return softAssert.get();
    }
    public static void clear() {
        softAssert.remove();
    }

    public static void assertAll() {
        getSoftAssert().assertAll();
        softAssert.remove();
    }
}
