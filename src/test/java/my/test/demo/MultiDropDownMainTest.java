package my.test.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MultiDropDownMainTest {
    private MultiSelectListDemo multiSelectListDemo;
    private WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        multiSelectListDemo = new MultiSelectListDemo(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @DisplayName(value = "Multi Select Drop Down Value Test")
    @Test
    void multiSelectTest() {
        Assertions.assertTrue(multiSelectListDemo.multiDropDownSelect(), "Expected and Actual values is not equals");
    }


    @AfterEach
    void cleanup() {
        driver.quit();
    }
}
