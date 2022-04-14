package my.test.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class CreateDriver {

    private WebDriver driver;
    private static CreateDriver instance;

    private static String url = "http://192.168.50.237:5555";
    private static DesiredCapabilities desiredCapabilities;

    private CreateDriver() {
        desiredCapabilities = new DesiredCapabilities();
        try {
            driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    public synchronized static CreateDriver getInstance() {
        if (instance == null) {
            try {
                instance = new RemoteWebDriver(new URL(url), desiredCapabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
