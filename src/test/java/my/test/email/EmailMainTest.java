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

    @BeforeEach
    void setup() {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);
    }

    @DisplayName("Login test for Email")
    @ParameterizedTest
    @CsvFileSource(resources = "/EmailCredentials.csv", numLinesToSkip = 1)
    void loginTest(String login, String password) {
        HomePage homePage = loginPage.login(login, password);
        Assertions.assertTrue(homePage.isUserLogIn(), "User don't login");

    }

    @DisplayName("Logout test for Email")
    @ParameterizedTest
    @CsvFileSource(resources = "/EmailCredentials.csv", numLinesToSkip = 1)
    void logoutTest(String login, String password) {
        HomePage homePage = loginPage.login(login, password);
        homePage.userLogout();
        Assertions.assertEquals("Yandex ID. The key to all your services\n" + "Learn more", logoutPage.logout(), "User don't logout");
    }

    @AfterEach
    void cleanup() {
    }
}
