package my.test.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateDriver {

    private WebDriver driver;
    private static CreateDriver instance;

    private CreateDriver() {
        driver = new ChromeDriver();
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
