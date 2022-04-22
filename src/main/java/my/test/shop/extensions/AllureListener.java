package my.test.shop.extensions;

import my.test.shop.driver.Driver;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AllureListener implements TestWatcher {

    private final WebDriver driver;
    private final String path;

    public AllureListener(String path) {
        this.driver = Driver.getInstance().getDriver();
        this.path = path;
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable throwable) {
        String browserInfo = getBrowserInfo();
        captureScreenshot(driver, context.getDisplayName() + browserInfo);
    }

    public void captureScreenshot(WebDriver driver, String fileName) {
        try {
            new File(path).mkdirs();
            try (FileOutputStream out = new FileOutputStream(path + File.separator + "screenshot-" + fileName + ".png")) {
                out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            }
        } catch (IOException | WebDriverException e) {
            System.out.println("screenshot failed:" + e.getMessage());
        }
    }

    public String getBrowserInfo() {
        return Driver.getInfo();
    }
}

