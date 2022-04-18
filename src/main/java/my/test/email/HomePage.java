package my.test.email;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private static final By PROFILE_BUTTON = By.xpath("//a[@href='https://passport.yandex.com']//img[@src='https://avatars.mds.yandex.net/get-yapic/0/0-0/islands-middle']");
    private static final By USER_NAME = By.cssSelector(".user-account_has-subname_yes > .user-account__name");
    private static final By LOGOUT_BUTTON = By.xpath("//a[@aria-label='Log out']");

    private final RemoteWebDriver driver;

    public HomePage() {
        this.driver = CreateDriver.getInstance().getDriver();
    }

    public HomePage openProfile() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(PROFILE_BUTTON));
        driver.findElement(PROFILE_BUTTON).click();
        return this;
    }

    public LogoutPage userLogout() {
        openProfile();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(LOGOUT_BUTTON));
        driver.findElement(LOGOUT_BUTTON).click();
        return new LogoutPage();
    }

    public boolean isUserLogIn() {
        openProfile();
        new WebDriverWait(driver, Duration.ofMinutes(1).plusSeconds(1)).until(ExpectedConditions.visibilityOfElementLocated(USER_NAME));
        return driver.findElement(USER_NAME).isDisplayed();
    }
}
