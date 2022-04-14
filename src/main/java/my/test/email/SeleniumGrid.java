package my.test.email;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGrid {

    private WebDriver driver;

    public SeleniumGrid() {
        this.driver = CreateDriver.getInstance().getDriver();
    }
    public void main(String[] args) throws MalformedURLException {
        String url = "http://192.168.50.237:5555";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("Chrome");
        desiredCapabilities.setPlatform(Platform.MAC);
        driver = new RemoteWebDriver((new URL(url)), desiredCapabilities);
    }
}
