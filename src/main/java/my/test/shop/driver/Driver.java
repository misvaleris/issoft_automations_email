package my.test.shop.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {

    private WebDriver driver;
    private static Driver instance;

    private Driver() {
        driver = new ChromeDriver();
    }

    public synchronized static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
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

    public void deleteCookies(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
