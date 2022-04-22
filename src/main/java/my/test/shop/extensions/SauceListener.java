package my.test.shop.extensions;

import my.test.shop.driver.Driver;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceListener implements TestWatcher {
    private final RemoteWebDriver driver;

    public SauceListener() {
        this.driver = Driver.getInstance().getChromeDriver();
    }

    public SauceListener(RemoteWebDriver driver) {
        this.driver = driver;
    }

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
