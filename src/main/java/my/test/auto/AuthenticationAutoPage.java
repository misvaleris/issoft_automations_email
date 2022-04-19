package my.test.auto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    private final WebDriver driver;

    public AuthenticationAutoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public AuthenticationAutoPage submitEmail(String email) {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(emailRegistrationFiled));
        emailRegistrationFiled.sendKeys(email);
        createAccountButton.click();

        return this;
    }

}
