package my.test.email;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;


public class SauceTestWatcher implements TestWatcher {

    private final WebDriver driver;

    public SauceTestWatcher() {
        this.driver = CreateDriver.getInstance().getDriver();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        context.
        driver.executeScript("sauce:job-result=passed");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        driver.executeScript("sauce:job-result=failed");
        driver.quit();
    }
}