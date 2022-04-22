package my.test.shop.pages;

import my.test.shop.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public AuthPage() {
        PageFactory.initElements(driver, this);
        this.driver = Driver.getInstance().getDriver();
    }




}
