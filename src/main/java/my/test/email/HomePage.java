package my.test.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    @FindBy(xpath = "//a[@href='https://passport.yandex.com']//img[@src='https://avatars.mds.yandex.net/get-yapic/0/0-0/islands-middle']")
    private WebElement profileButton;
    @FindBy(css = ".user-account_has-subname_yes > .user-account__name")
    private WebElement userName;
    @FindBy(xpath = "//a[@aria-label='Log out']")
    private WebElement logoutButton;


    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public HomePage openProfile() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(profileButton));
        profileButton.click();
        return this;
    }

    public LogoutPage userLogout() {
        openProfile();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
        return new LogoutPage(driver);
    }

    public boolean isUserLogIn() {
        openProfile();
        return userName.isDisplayed();
    }
}
