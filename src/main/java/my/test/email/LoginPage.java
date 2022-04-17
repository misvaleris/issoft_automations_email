package my.test.email;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private static final String LOGIN_URL = "https://mail.yandex.com/";
    private static final By LOGIN_FIELD = By.xpath("//input[@id='passp-field-login']");
    private static final By LOGIN_BUTTON = By.xpath("//div[@class='HeadBanner-ButtonsWrapper']/a[.='Log in']");
    private static final By SUBMIT_LOGIN_BUTTON = By.xpath("//button[@id='passp:sign-in']");
    private static final By PASSWORD_FIELD = By.xpath("//input[@id='passp-field-passwd']");
    private static final By SUBMIT_PASSWORD_BUTTON = By.cssSelector(".Button2_view_action");

    private final RemoteWebDriver driver;

    public LoginPage() {
        this.driver = CreateDriver.getInstance().getEdgeDriver();
    }

    public LoginPage submitLogin(String username) {
        WebElement loginButton = driver.findElement(LOGIN_BUTTON);
        loginButton.click();
        driver.findElement(LOGIN_FIELD).sendKeys(username);
        driver.findElement(SUBMIT_LOGIN_BUTTON).submit();
        return this;
    }

    public HomePage submitPassword(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(SUBMIT_PASSWORD_BUTTON).submit();
        return new HomePage();
    }

    public HomePage login(String username, String password) {
        driver.navigate().to(LOGIN_URL);
        submitLogin(username);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(PASSWORD_FIELD));
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(SUBMIT_PASSWORD_BUTTON));
        return submitPassword(password);
    }
}