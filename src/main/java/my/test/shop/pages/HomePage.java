package my.test.shop.pages;

import io.qameta.allure.Step;
import my.test.shop.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(css = ".account span")
    private WebElement viewAccountName;

    @FindBy(css = ".account")
    private WebElement userAccountButton;

    @FindBy(css = "#block_top_menu a[title='Women']")
    private WebElement menuWomenButton;

    private WebDriver driver;

    public HomePage() {
        PageFactory.initElements(driver, this);
        this.driver = Driver.getInstance().getDriver();
    }

//    @Step("Go to product list")
//    public void goToProductList(){
//        menuWomenButton.click();
//    }

    @Step("Retrieve user name from header")
    public String getUserName(){
        return viewAccountName.getText();
    }

//    @Step("Go to account")
//    public void goToAccount(){
//        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(userAccountButton)).click();
//    }
}
