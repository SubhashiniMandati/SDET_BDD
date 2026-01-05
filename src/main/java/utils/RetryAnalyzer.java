package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int count = 0;
    public boolean retry(ITestResult result) {
        if (count < 2) {
            count++;
            return true;
        }
        return false;
    }
}

