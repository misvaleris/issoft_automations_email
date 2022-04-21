package my.test.auto;

import io.qameta.allure.*;
import my.test.auto.pages.CreateAccountAutoPage;
import my.test.auto.pages.HeaderAutoPage;
import my.test.auto.pages.HomeAutoPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Shop functionality")
@Feature("Create account")
@Tag("Critical")
public class CreateNewAccountTest {

    @ParameterizedTest
    @ValueSource(strings = {"My account - My Store"})
    @Story("Create account tests")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User creates account")
    @Tag("stable")
    public void createAccount(String title) {
        HeaderAutoPage headerAutoPage = new HeaderAutoPage();
        CreateAccountAutoPage createAccountAutoPage = new CreateAccountAutoPage();
        headerAutoPage.loginToSite();
        createAccountAutoPage.fillPersonalInfo();
        HomeAutoPage homeAutoPage = new HomeAutoPage();
        Assertions.assertAll("Page name and user name are correct",
                () -> assertEquals(homeAutoPage.getTitleName(), title, "Verify page name"),
                () -> assertEquals(headerAutoPage.getUserName(), createAccountAutoPage.getUserName(), "Verify account")
        );
    }
}
