package my.test.email;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;

public class CreateDriver {

    private RemoteWebDriver driver;
    private static CreateDriver instance;

    private static String baseUrl = "http://localhost:4444/wd/hub";
    private static DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    private CreateDriver() {
        desiredCapabilities.setCapability(BROWSER_NAME, "chrome");

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

    public RemoteWebDriver getDriver() {
        return driver;
    }
}
