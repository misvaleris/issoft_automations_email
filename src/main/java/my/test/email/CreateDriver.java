package my.test.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.jupiter.api.TestInfo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CreateDriver {

    private WebDriver driver;
    private static CreateDriver instance;


    private CreateDriver(TestInfo testInfo) throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.setPlatformName("Windows 10");
        options.setBrowserVersion("latest");

        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", System.getenv("oauth-vs.panteleeva-b713f"));
        sauceOptions.put("accessKey", System.getenv("9e655efcda0c40eda447b33ecb47bd04"));
        sauceOptions.put("name", testInfo.getDisplayName());

        options.setCapability("sauce:options", sauceOptions);
        String baseUrl = ("https://ondemand.us-west-1.saucelabs.com/wd/hub");

        try {
            driver = new RemoteWebDriver(new URL(baseUrl), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public CreateDriver() {
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
