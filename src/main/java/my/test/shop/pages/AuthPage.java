package my.test.shop.pages;

import io.qameta.allure.Step;
import my.test.shop.driver.Driver;
import my.test.shop.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class AuthPage {

    @FindBy(xpath = "//input[@id='email_create']")
    private WebElement emailRegistrationFiled;

    @FindBy(xpath = "//button[@name='SubmitCreate']")
    private WebElement createAccountButton;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@name='SubmitLogin']")
    private WebElement signInButton;

    private WebDriver driver;
    private static final String LOGIN_URL = "http://automationpractice.com/index.php?controller=authentication&back=identity";
    private static String propertyPath = "src/test/resources/email.properties";
    private Properties properties = Utils.getProperties(propertyPath);

    public AuthPage() {
        PageFactory.initElements(driver, this);
        this.driver = Driver.getInstance().getDriver();
    }

    @Step("Submit Email to start Registration Process")
    public RegistrationPage submitRegistrationEmail() {
        driver.get(LOGIN_URL);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(emailRegistrationFiled));
        emailRegistrationFiled.sendKeys(Utils.emailGenerator());
        createAccountButton.click();
        return new RegistrationPage();
    }


    @Step("Log in Account")
    public HomePage login() {
        driver.get(LOGIN_URL);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.sendKeys(properties.getProperty("USER_NAME"));
        passwordField.sendKeys(properties.getProperty("PASSWORD"));
        signInButton.click();
        return new HomePage();
    }
}
