package my.test.shop.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

}
