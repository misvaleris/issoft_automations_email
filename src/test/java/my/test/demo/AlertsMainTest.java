package my.test.demo;

import my.test.demo.alerts.JSAlertDemo;
import my.test.demo.alerts.JSConfirmDemo;
import my.test.demo.alerts.JSPromptDemo;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class AlertsMainTest {
    private JSAlertDemo jsAlertDemo;
    private JSConfirmDemo jsConfirmDemo;
    private JSPromptDemo jsPromptDemo;
    private WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        jsAlertDemo = new JSAlertDemo(driver);
        jsConfirmDemo = new JSConfirmDemo(driver);
        jsPromptDemo = new JSPromptDemo(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @DisplayName(value = "JS Alert test")
    @Test
    void alertTest() {
        Assertions.assertEquals(jsAlertDemo.javaScriptAlertBox(), "I am an alert box!","JS Alert test failed, text isn't mach");
    }

    @DisplayName(value = "JS Confirmation test")
    @Test
    void confirmTest() {
        Assertions.assertTrue(jsConfirmDemo.javaScriptConfirmBox(), "JS Confirm test failed");
    }

    @DisplayName(value = "JS Promt test")
    @Test
    void promtTest() {
        Assertions.assertTrue(jsPromptDemo.javaScriptPromptBox(), "JS Prompt test failed");
    }

    @AfterEach
    void cleanup() {
        driver.quit();
    }

}
