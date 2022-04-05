package my.test.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private static final String LOGIN_URL = "https://mail.yandex.com/";
    @FindBy(xpath = "//input[@id='passp-field-login']")
    private WebElement loginField;
    @FindBy(xpath = "//div[@class='HeadBanner-ButtonsWrapper']/a[.='Log in']")
    private WebElement loginButton;
    @FindBy(xpath = "//button[@id='passp:sign-in']")
    private WebElement submitLoginButton;
    @FindBy(xpath = "//input[@id='passp-field-passwd']")
    private WebElement passwordField;
    @FindBy(css = ".Button2_view_action")
    private WebElement submitPasswordButton;

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public LoginPage openLoginForm() {
        loginButton.click();
        return this;
    }

    public LoginPage typeUsername(String username) {
        loginField.sendKeys(username);
        return this;
    }

    public LoginPage submitLogin() {
        submitLoginButton.submit();
        return this;
    }

    public LoginPage typePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public HomePage submitPassword() {
        submitPasswordButton.submit();
        return new HomePage(driver);
    }

    public HomePage login(String username, String password) {
        driver.navigate().to(LOGIN_URL);
        openLoginForm();
        typeUsername(username);
        submitLogin();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(passwordField));
        typePassword(password);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(submitPasswordButton));
        return submitPassword();
    }
}