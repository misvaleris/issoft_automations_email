package my.test.auto;

import io.qameta.allure.*;
import my.test.auto.pages.HeaderAutoPage;
import my.test.auto.pages.HomeAutoPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Shop functionality")
@Feature("Login in account")
@Tag("Critical")
public class LoginTest {

    @ParameterizedTest
    @ValueSource(strings = {"My account - My Store"})
    @Story("Log in account tests")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User log in account")
    @Tag("stable")
    public void loginIntoAccount(String title) {
        HeaderAutoPage headerAutoPage = loginToSite();
        HomeAutoPage homeAutoPage = new HomeAutoPage();
        Assertions.assertAll("Page name and user name are correct",
                () -> assertEquals(homeAutoPage.getTitleName(), title, "Verify page name"),
                () -> assertEquals(headerAutoPage.getUserName(), "Valeria Panteleeva", "Verify account")
        );
    }
}
