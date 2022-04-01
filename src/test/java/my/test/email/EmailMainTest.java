package my.test.email;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class EmailMainTest {
    private LoginPage loginPage;
    private LogoutPage logoutPage;

    @BeforeEach
    void setup() {
        loginPage = new LoginPage();
        logoutPage = new LogoutPage();
    }


    @DisplayName("Login test for Email")
    @ParameterizedTest
    @CsvFileSource(resources = "/EmailCredentials.csv", numLinesToSkip = 1)
    public void loginTest(String login, String password) {
        Assertions.assertTrue(loginPage.login(login, password), "User Name is not present. User don't log in");
    }

    @DisplayName("Logout test for Email")
    @Test
    public void logoutTest() {
        Assertions.assertTrue(logoutPage.logout(), "User don't log out");
    }

    @AfterEach
    void cleanup() {
    }
}

