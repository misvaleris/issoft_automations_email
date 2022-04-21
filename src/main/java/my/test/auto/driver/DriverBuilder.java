package my.test.auto.driver;

import lombok.SneakyThrows;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverBuilder {
    public static String baseURL = "http://localhost:4444/wd/hub";

    public static DriverStrategy initWebDriver(DriverConfig driverConfig) {
        DriverStrategy driver;
        switch (driverConfig) {
            case CHROME:
                driver = new DriverBuilderChrome();
                break;
            case FF:
                driver = new DriverBuilderFirefox();
                break;
            case REMOTE_FF:
                driver = new DriverBuilderRemoteFirefox();
                break;
            case REMOTE_CHROME:
                driver = new DriverBuilderRemoteChrome();
                break;
            default:
                throw new RuntimeException("Not supported driver type");
        }
        return driver;
    }

    public static class DriverBuilderChrome implements DriverStrategy {

        @Override
        public WebDriver getDriver() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            return new ChromeDriver(chromeOptions);
        }
    }

    public static class DriverBuilderFirefox implements DriverStrategy {

        @Override
        public WebDriver getDriver() {
            System.setProperty("webdriver.gecko.driver", "/Users/misvaleris/Downloads/geckodriver");
            return new FirefoxDriver();
        }
    }

    public static class DriverBuilderRemoteChrome implements DriverStrategy {

        @SneakyThrows
        @Override
        public WebDriver getDriver() {
            DesiredCapabilities capabilitiesH = new DesiredCapabilities();
            capabilitiesH.setBrowserName("chrome");
            capabilitiesH.setVersion("100");
            capabilitiesH.setPlatform(Platform.WINDOWS);
            return new RemoteWebDriver(new URL(baseURL), capabilitiesH);
        }
    }

    public static class DriverBuilderRemoteFirefox implements DriverStrategy {

        @SneakyThrows
        @Override
        public WebDriver getDriver() {
            DesiredCapabilities capabilitiesF = new DesiredCapabilities();
            capabilitiesF.setBrowserName("firefox");
            capabilitiesF.setVersion("99.0.1");
            capabilitiesF.setPlatform(Platform.WINDOWS);
            return new RemoteWebDriver(new URL(baseURL), capabilitiesF);
        }
    }
}

