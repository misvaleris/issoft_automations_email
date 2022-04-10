package my.test.email;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class EmailMainTest {
    private LoginPage loginPage;

    @BeforeEach
    void setup() {
        loginPage = new LoginPage();
    }

    @DisplayName("Login test for Email")
    @AllureId("Login_1")
    @Feature("Login")
    @Description("User login to Email page. Expected result: after login user has ability to see home page")
    @ParameterizedTest
    @CsvFileSource(resources = "/EmailCredentials.csv", numLinesToSkip = 1)
    void loginTest(String login, String password) {
        HomePage homePage = loginPage.login(login, password);
        Assertions.assertTrue(homePage.isUserLogIn(), "User don't login");
    }

    @DisplayName("Logout test for Email")
    @AllureId("Logout_1")
    @Feature("Logout")
    @Description("User logout to Email page. Expected result: after logout use doesn't have ability to see home page")
    @ParameterizedTest
    @CsvFileSource(resources = "/EmailCredentials.csv", numLinesToSkip = 1)
    void logoutTest(String login, String password) {
        HomePage homePage = loginPage.login(login, password);
        LogoutPage logoutPage = homePage.userLogout();
        Assertions.assertEquals("Yandex ID. The key to all your services\n" + "Learn more", logoutPage.logout(), "User don't logout");
    }

    @AfterEach
    void cleanup() {
    }
}
