package my.test.shop.pages;

import io.qameta.allure.Step;
import my.test.shop.driver.Driver;
import my.test.shop.userData.Parser;
import my.test.shop.userData.UserData;
import my.test.shop.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage {

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

    public RegistrationPage() {
        PageFactory.initElements(driver, this);
        this.driver = Driver.getInstance().getDriver();
    }

    public void stateDropDownSelect() {
        Select states = new Select(stateDropDown);
        states.selectByIndex(5);
    }

    public String getUserName() {
        return userName;
    }

    @Step("Fill required fields")
    public HomePage fillPersonalInfo() {
        UserData userData = Parser.readFromFile();
        String inputFirstName = userData.getFirstName();
        firstNameField.sendKeys(inputFirstName);

        String inputLastName = userData.getLastName();
        lastNameField.sendKeys(inputLastName);
        userName = inputFirstName + " " + inputLastName;

        passwordField.sendKeys(userData.getPassword());
        addressField.sendKeys(userData.getAddress());
        cityField.sendKeys(userData.getCity());
        stateDropDownSelect();
        postalCodeField.sendKeys(userData.getPostCode());
        phoneField.sendKeys(Utils.numberGenerator(12));
        registerButton.click();
        return new HomePage();
    }
}
