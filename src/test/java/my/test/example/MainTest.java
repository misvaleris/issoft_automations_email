package my.test.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


public class MainTest {
    LoginPage loginPage;

    @BeforeEach
    void setup() {
        loginPage = new LoginPage();

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/EmailCredentials.csv", numLinesToSkip = 1)
    void loginTest(String login, String password) {
        loginPage.login(login, password);
        Assertions.assertEquals(loginPage.emailPageIsPresent(), true);
        loginPage.logOutCurrentUser();
    }

    @AfterEach
    void cleanup() {
    }
}

