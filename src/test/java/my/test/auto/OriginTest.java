package my.test.auto;

import my.test.auto.driver.Driver;
import my.test.auto.driver.DriverConfig;
import my.test.auto.pages.AuthenticationAutoPage;
import my.test.auto.pages.HeaderAutoPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(AutoTestWatcher.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OriginTest {

    @BeforeAll
    public void init(){
        WebDriver driver = Driver.getInstance().getDriver(DriverConfig.CHROME);
//        Utils.setUpAllureEnvironment(driver);
    }

    @AfterAll
    public void closeWebDriver(){
        Driver.getInstance().closeWebDriver();
    }

    protected HeaderAutoPage loginToSite(){
        HeaderAutoPage headerAutoPage = new HeaderAutoPage();
        AuthenticationAutoPage authenticationAutoPage = new AuthenticationAutoPage();
        headerAutoPage.loginToSite();
        authenticationAutoPage.login();
        return headerAutoPage;
    }
}

