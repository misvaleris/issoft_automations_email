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

public class HeaderAutoPage {

    private String propertyPath = "src/test/resources/sites.properties";
    private Properties properties = Utils.getProperties(propertyPath);


    @FindBy(css = ".account span")
    private WebElement viewCustomerAccountName;

    @FindBy(css = ".account")
    private WebElement userAccount;

    @FindBy(css = "#block_top_menu a[title='Women']")
    private WebElement menuWomen;

    @FindBy(xpath = "//header[@id = 'header']//div[@class='nav']//a[@class='login']")
    private WebElement signInButton;

    private WebDriver driver;

    public HeaderAutoPage() {
        PageFactory.initElements(this.driver, this);
    }

    @Step("Click sign in button")
    public void loginToSite(){
        driver.get(properties.getProperty("URL"));
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    @Step("go to product list")
    public void goToProductList(){
        menuWomen.click();
    }

    @Step("Retrieve user name from header")
    public String getUserName(){
        return viewCustomerAccountName.getText();
    }

    @Step("Go to account")
    public void goToAccount(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(userAccount)).click();
    }
}
