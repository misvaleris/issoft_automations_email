package my.test.auto.pages;

import io.qameta.allure.Step;
import my.test.auto.userData.UserData;
import my.test.auto.utils.Parser;
import my.test.auto.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountAutoPage {
    public String userName;

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

    @FindBy(xpath = "//button[@id='submitAccount']")
    private WebElement registerButton;

    private WebDriver driver;

    public CreateAccountAutoPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void stateDropDownSelect() {
        Select states = new Select(stateDropDown);
        states.selectByIndex(5);
    }

    @Step("Fill required fields")
    public HomeAutoPage fillPersonalInfo() {
        UserData userData = Parser.readFromFile();
        String inputFirstName = userData.getFirstName();
        firstNameField.sendKeys(inputFirstName);

        String inputLastName = userData.getLastName();
        lastNameField.sendKeys(inputLastName);

        passwordField.sendKeys(userData.getPassword());
        addressField.sendKeys(userData.getAddress());
        cityField.sendKeys(userData.getCity());
        stateDropDownSelect();
        postalCodeField.sendKeys(userData.getPostCode());
        phoneField.sendKeys(Utils.numberGenerator(12));

        registerButton.click();
        return new HomeAutoPage();
    }

    public String getUserName() {
        return userName;
    }
}

