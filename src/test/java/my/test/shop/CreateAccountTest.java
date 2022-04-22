package my.test.shop;

import io.qameta.allure.*;
import my.test.shop.extensions.AllureListener;
import my.test.shop.pages.AuthPage;
import my.test.shop.pages.HomePage;
import my.test.shop.pages.RegistrationPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CreateAccountTest {
    private AuthPage authPage;
    private RegistrationPage registrationPage;
    private HomePage homePage;

    @RegisterExtension
    AllureListener watcher = new AllureListener("target/surefire-reports");

    @BeforeEach
    void setup() {
        homePage = new HomePage();
        authPage = new AuthPage();
        registrationPage = new RegistrationPage();
    }

    @DisplayName("Create account test")
    @AllureId("Registration_1")
    @Feature("Registration")
    @Story("User Registration")
    @Epic("registration")
    @Description("User fills out required personal info to complete registration. Expected result: user has ability to see home page after registration")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @ValueSource(strings = {"My account - My Store"})
    void loginTest() {
        registrationPage = authPage.submitRegistrationEmail();
        homePage = registrationPage.fillPersonalInfo();
        Assertions.assertEquals(homePage.getUserName(), registrationPage.getUserName(), "User name doesn't match. User don't register");
    }

    @AfterEach
    void cleanup() {
    }
}
