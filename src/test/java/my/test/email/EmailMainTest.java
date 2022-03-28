package my.test.email;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class EmailMainTest {
    private LoginPage loginPage;
    private WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/EmailCredentials.csv", numLinesToSkip = 1)
    void loginTest(String login, String password) {
        Assertions.assertTrue(loginPage.login(login, password), "User Name is not present. User don't log in");
    }

    @AfterEach
    void cleanup() {
        driver.quit();
    }
}

