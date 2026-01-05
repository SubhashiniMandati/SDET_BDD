package utils;

public class FailureContext {
    public static final ThreadLocal<Throwable> error = new ThreadLocal<>();
}
