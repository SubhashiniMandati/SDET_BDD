package log;

import org.apache.logging.log4j.*;

public class LoggerUtil {

    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }
}
