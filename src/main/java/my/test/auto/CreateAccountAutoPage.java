package my.test.auto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountAutoPage {
    @FindBy(xpath = "//input[@id='customer_firstname']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@id='customer_lastname']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@name='address1']")
    private WebElement addressField;

    @FindBy(xpath = "//input[@id='city']")
    private WebElement cityField;

    @FindBy(xpath = "//select[@id='id_state']")
    private WebElement stateDropDown;

    @FindBy(xpath = "//input[@id='postcode']")
    private WebElement postalCodeField;

    @FindBy(xpath = "//input[@id='phone_mobile']")
    private WebElement phoneField;

    private final WebDriver driver;

    public CreateAccountAutoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


}
