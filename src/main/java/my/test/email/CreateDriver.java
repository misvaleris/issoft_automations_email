package my.test.email;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CreateDriver {

    private WebDriver driver;
    private static CreateDriver instance;

    private CreateDriver() {
        driver = new ChromeDriver();
    }

    public synchronized static CreateDriver getInstance() {
        if (instance == null) {
            instance = new CreateDriver();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static String getInfo() {
        Capabilities cap = ((RemoteWebDriver) getInstance().getDriver()).getCapabilities();
        String browserName = cap.getBrowserName();
        String platform = cap.getPlatformName().toString();
        String version = cap.getBrowserVersion();
        return String.format("browser: %s v: %s platform: %s", browserName, version, platform);
    }
}
