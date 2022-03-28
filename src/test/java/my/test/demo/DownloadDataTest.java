package my.test.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DownloadDataTest {
    private DownloadDataDemo downloadDataDemo;
    private WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        downloadDataDemo = new DownloadDataDemo(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @DisplayName(value = "Loading the data Dynamically test")
    @Test
    void loadUserInfoTest() {
        Assertions.assertEquals(downloadDataDemo.downloadData(), "0%","Data is not uploaded");
    }

    @AfterEach
    void cleanup() {
        driver.quit();
    }
}
