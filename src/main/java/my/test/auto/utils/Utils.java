package my.test.auto.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    private static Properties properties = new Properties();

    public static Properties getProperties(String path) {
        try (InputStream input = new FileInputStream(path)) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    public static String emailGenerator() {
        return "autotestsmisvaleris" + RandomStringUtils.randomAlphanumeric(5) + "@yandex.by";
    }

    public static String numberGenerator(int count){
        return RandomStringUtils.randomNumeric(count);
    }

    public static String getBrowserName(WebDriver driver){
        Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
        return cap.getBrowserName().toLowerCase();
    }

    public static String getBrowserVersion(WebDriver driver){
        Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
        return cap.getBrowserVersion();
    }

    public static String getOsName(WebDriver driver){
        Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
        return cap.getPlatformName().toString();
    }

}
