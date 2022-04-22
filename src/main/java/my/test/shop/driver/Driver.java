package my.test.shop.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    private static final String BASE_URL = "";

    private final RemoteWebDriver chromeDriver;
    private final RemoteWebDriver foxDriver;

    private RemoteWebDriver driver;
    private static Driver instance;

    private Driver() {
//        driver = new ChromeDriver();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPlatformName("macOS 11.00");
        chromeOptions.setBrowserVersion("75");

        chromeDriver = getRemoteDriver(chromeOptions);

        FirefoxOptions foxOptions = new FirefoxOptions();
        foxOptions.setPlatformName("Windows 8.1");
        foxOptions.setBrowserVersion("78");

        foxDriver = getRemoteDriver(foxOptions);
    }

    public synchronized static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }

    public RemoteWebDriver getRemoteDriver(AbstractDriverOptions<?> options) {
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", System.getenv(""));
        sauceOptions.put("accessKey", System.getenv(""));
        options.setCapability("sauce:options", sauceOptions);

        try {
            return new RemoteWebDriver(new URL(BASE_URL), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public RemoteWebDriver getChromeDriver() {
        return chromeDriver;
    }

    public RemoteWebDriver getFoxDriver() {
        return foxDriver;
    }


    public static String getInfo() {
        Capabilities cap = ((RemoteWebDriver) getInstance().getDriver()).getCapabilities();
        String browserName = cap.getBrowserName();
        String platform = cap.getPlatformName().toString();
        String version = cap.getBrowserVersion();
        return String.format("browser: %s v: %s platform: %s", browserName, version, platform);
    }

    public void deleteCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
