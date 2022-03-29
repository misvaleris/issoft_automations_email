package my.test.email;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EmailMainTest {
    private LoginPage loginPage;
    private LogoutPage logoutPage;
    private WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);

    }

    @DisplayName("Login test for Email")
    @ParameterizedTest
    @CsvFileSource(resources = "/EmailCredentials.csv", numLinesToSkip = 1)
    void loginTest(String login, String password) {
        Assertions.assertTrue(loginPage.login(login, password), "User Name is not present. User don't log in");
    }

    @DisplayName("Logout test for Email")
    @Test
    void logoutTest() {
        Assertions.assertTrue(logoutPage.logout(), "User don't log out");
    }

    @AfterEach
    void cleanup() {
        driver.quit();
    }
}

