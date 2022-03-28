package my.test.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoadingDataTest {
    private LoadingDataDemo loadingDataDemo;
    private WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        loadingDataDemo = new LoadingDataDemo(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @DisplayName(value = "Loading the data Dynamically test")
    @Test
    void loadUserInfoTest() {
        Assertions.assertTrue(loadingDataDemo.waitForUser(), "User data is not uploaded");
    }

    @AfterEach
    void cleanup() {
        driver.quit();
    }
}
