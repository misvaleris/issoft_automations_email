package my.test.email;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;

public class CreateDriver {

    private WebDriver driver;
    private static CreateDriver instance;

    private static String baseUrl = "http://localhost:4444";
    private static DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    private CreateDriver() {
        desiredCapabilities.setCapability(BROWSER_NAME, "chrome");
        desiredCapabilities.setPlatform(Platform.MAC);
        desiredCapabilities.setCapability("headless",true);

        try {
            driver = new RemoteWebDriver(new URL(baseUrl), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
}
