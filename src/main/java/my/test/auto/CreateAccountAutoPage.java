package my.test.auto;

import my.test.email.CreateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

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

    @FindBy(xpath = "//select[@id='id_state']/option")
    private WebElement stateDropDownOptions;

    @FindBy(xpath = "//input[@id='postcode']")
    private WebElement postalCodeField;

    @FindBy(xpath = "//input[@id='phone_mobile']")
    private WebElement phoneField;

    @FindBy(xpath = "//button[@id='submitAccount']")
    private WebElement registerButton;

    private WebDriver driver;

    public CreateAccountAutoPage() {
        PageFactory.initElements(driver, this);
        this.driver = CreateDriver.getInstance().getChromeDriver();
    }

    public String stateDropDownSelect() {
        List<WebElement> stateList = stateDropDownOptions.findElements((By) stateDropDownOptions);

        Random random = new Random();
        for (int i = 0; i < 54; i++) {
            int randomIndex = random.nextInt(stateList.size());
            Select drpValue = new Select(stateDropDown);
            drpValue.selectByIndex(randomIndex);
            String selectedSate = String.valueOf(drpValue.getFirstSelectedOption());
            return selectedSate;
        }

        public HomeAutoPage fillPersonalInfo () {
            firstNameField.sendKeys(firstName);
            lastNameField.sendKeys(lastName);
            passwordField.sendKeys(password);
            addressField.sendKeys(address);
            cityField.sendKeys(city);
            stateDropDownSelect();
            postalCodeField.sendKeys(postalCode);
            phoneField.sendKeys(phone);
            registerButton.click();
            return new HomeAutoPage();
        }
    }
}
