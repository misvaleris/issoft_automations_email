package my.test.email;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CreateDriver {
    private static final String BASE_URL = "https://oauth-vs.panteleeva-b713f:fded0546-993e-42dc-8f9b-6da4de0b233c@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
    private static CreateDriver instance;

    private final RemoteWebDriver edgeDriver;
    private final RemoteWebDriver chromeDriver;
    private final RemoteWebDriver foxDriver;

    private CreateDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPlatformName("macOS 11.00");
        chromeOptions.setBrowserVersion("75");

        chromeDriver = getRemoteDriver(chromeOptions);

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setPlatformName("Windows 10");
        edgeOptions.setBrowserVersion("latest");

        edgeDriver = getRemoteDriver(edgeOptions);

        FirefoxOptions foxOptions = new FirefoxOptions();
        foxOptions.setPlatformName("Windows 8.1");
        foxOptions.setBrowserVersion("78");

        foxDriver = getRemoteDriver(foxOptions);
    }

    public RemoteWebDriver getRemoteDriver(AbstractDriverOptions<?> options) {
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", System.getenv("oauth-vs.panteleeva-b713f"));
        sauceOptions.put("accessKey", System.getenv("fded0546-993e-42dc-8f9b-6da4de0b233c"));
        options.setCapability("sauce:options", sauceOptions);

        try {
            return new RemoteWebDriver(new URL(BASE_URL), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public synchronized static CreateDriver getInstance() {
        if (instance == null) {
            instance = new CreateDriver();
        }
        return instance;
    }

    public RemoteWebDriver getChromeDriver() {
        return chromeDriver;
    }

    public RemoteWebDriver getEdgeDriver() {
        return edgeDriver;
    }

    public RemoteWebDriver getFoxDriver() {
        return foxDriver;
    }
}
