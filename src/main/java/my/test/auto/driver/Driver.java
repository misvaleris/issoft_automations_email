package my.test.auto.driver;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;

public class Driver {

    private static Driver instance;
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    private Driver() {
    }

    public static Driver getInstance() {
        Driver localInstance = instance;
        if (localInstance == null) {
            synchronized (Driver.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Driver();
                }
            }
        }
        return localInstance;
    }

    @SneakyThrows
    public WebDriver getDriver(DriverConfig driverConfig) {
        WebDriver driver = webDriver.get();
        if (driver == null) {
            driver = DriverBuilder.initWebDriver(driverConfig).getDriver();
        }
        webDriver.set(driver);
        return driver;
    }

    public WebDriver getCurrentWebDriver() {
        return webDriver.get();
    }

    public void closeWebDriver() {
        WebDriver driver = this.webDriver.get();
        if (driver != null) {
            driver.quit();
            webDriver.set(null);
        }
    }

    public void deleteCookies() {
        WebDriver driver = this.webDriver.get();
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
