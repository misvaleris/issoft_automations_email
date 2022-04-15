package my.test.email;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.rules.TestWatcher;

public class SauceTestWatcher extends TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
        driver.executeScript("sauce:job-result=passed");
        driver.quit();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        driver.executeScript("sauce:job-result=failed");
        driver.quit();
    }
}