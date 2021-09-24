package tests;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int testTry = 0;
    private int LAST_TEST_TRY = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (result.isSuccess()) {
            result.setStatus(ITestResult.SUCCESS);
        } else {
            if (testTry < LAST_TEST_TRY) {
                testTry++;
                result.setStatus(ITestResult.FAILURE);
                return true;
            } else {
                result.setStatus(ITestResult.FAILURE);
            }
        }
        return false;
    }

}
