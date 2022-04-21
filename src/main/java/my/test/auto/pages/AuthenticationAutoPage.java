package my.test.auto.pages;

import io.qameta.allure.Step;
import my.test.auto.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class AuthenticationAutoPage {

    @FindBy(xpath = "//input[@id='email_create']")
    private WebElement emailRegistrationFiled;

    @FindBy(xpath = "//button[@name='SubmitCreate']")
    private WebElement createAccountButton;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailLoginField;

    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@name='SubmitLogin']")
    private WebElement signInButton;

    private WebDriver driver;
    private static final String LOGIN_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

    private final String propertyPath = "src/test/resources/mail.properties";
    private final Properties properties = Utils.getProperties(propertyPath);

    public AuthenticationAutoPage(WebDriver driver) {
        PageFactory.initElements(this.driver, this);
    }

    public AuthenticationAutoPage() {
    }

    @Step("Submit Email for Account Creation Process")
    public CreateAccountAutoPage submitRegistrationEmail() {
        driver.get(LOGIN_URL);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(emailRegistrationFiled));
        emailRegistrationFiled.sendKeys(Utils.emailGenerator());
        createAccountButton.click();
        return new CreateAccountAutoPage();
    }


    @Step("Log in Account")
    public HomeAutoPage login() {
        driver.get(LOGIN_URL);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(emailLoginField));
        emailLoginField.sendKeys(properties.getProperty("USER_NAME_STORE"));
        passwordField.sendKeys(properties.getProperty("PASSWORD_STORE"));
        signInButton.click();
        return new HomeAutoPage();
    }
}
