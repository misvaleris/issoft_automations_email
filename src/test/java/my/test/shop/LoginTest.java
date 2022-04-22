package my.test.shop;

import io.qameta.allure.*;
import my.test.shop.extensions.AllureListener;
import my.test.shop.pages.AuthPage;
import my.test.shop.pages.HomePage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LoginTest {
    private AuthPage authPage;

    @RegisterExtension
    AllureListener watcher = new AllureListener("target/surefire-reports");

    @BeforeEach
    void setup() {
        authPage = new AuthPage();
    }

    @DisplayName("Login test")
    @AllureId("Login_1")
    @Feature("Login")
    @Story("User Login")
    @Epic("Login")
    @Description("User login to Shop Home page. Expected result: user has ability to see home page after login")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @ValueSource(strings = { "My account - My Store"})
    void loginTest() {
        HomePage homePage = authPage.login();
        Assertions.assertEquals(homePage.getUserName(), "Valeria Panteleeva", "User name doesn't match. User don't login");
    }

    @AfterEach
    void cleanup() {
    }
}
