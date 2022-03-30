package my.test.email;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;

public class EmailMainTest {
    private LoginPage loginPage;
    private LogoutPage logoutPage;
    private WebDriver driver;

    @BeforeEach
    void setup() {
        loginPage = new LoginPage();
        logoutPage = new LogoutPage();
        driver = CreateDriver.getInstance();
    }

    @DisplayName("Login test for Email")
    @ParameterizedTest
    @CsvFileSource(resources = "/EmailCredentials.csv", numLinesToSkip = 1)
    void loginTest(String login, String password) {
        Assertions.assertTrue(loginPage.login(login, password), "User Name is not present. User don't log in");
    }

    @DisplayName("Logout test for Email")
    @ParameterizedTest
    @CsvFileSource(resources = "/EmailCredentials.csv", numLinesToSkip = 1)
    void logoutTest(String login, String password) {
        loginPage.login(login, password);
        Assertions.assertTrue(logoutPage.logout(), "User don't log out");
    }

    @AfterEach
    void cleanup() {
        driver.quit();
    }
}

