package utils.api;

import io.restassured.filter.Filter;
import io.restassured.filter.log.*;

import java.util.ArrayList;
import java.util.List;

public class ApiLoggingUtil {

    /**
     * Logging utilities used for api request
     * and response logging.
     */

    private static final String LOGGING_LEVEL = System.getProperty("LOG_LEVEL","LOW");

    public static List<Filter> getLoggingFilter() {

        List<Filter> restAssuredFilters = new ArrayList<>();

        if (LOGGING_LEVEL == null || "test".equals(LOGGING_LEVEL)) {
            restAssuredFilters.add(new ErrorLoggingFilter());
        } else {
            restAssuredFilters.add(new RequestLoggingFilter());
            restAssuredFilters.add(new ResponseLoggingFilter());

        }

        return restAssuredFilters;
    }
}
