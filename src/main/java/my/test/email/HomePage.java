package my.test.email;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private static final String PROFILE_BUTTON = "//a[@href='https://passport.yandex.com']//img[@src='https://avatars.mds.yandex.net/get-yapic/0/0-0/islands-middle']";
    private static final String USER_NAME = ".user-account_has-subname_yes > .user-account__name";
    private static final String LOGOUT_BUTTON = "//a[@aria-label='Log out']";

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage openProfile() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath(PROFILE_BUTTON)));
        driver.findElement(By.xpath(PROFILE_BUTTON)).click();
        return this;
    }

    public LogoutPage userLogout() {
        openProfile();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath(LOGOUT_BUTTON)));
        driver.findElement(By.xpath(LOGOUT_BUTTON)).click();
        return new LogoutPage(driver);
    }

    public boolean isUserLogIn() {
        openProfile();
        new WebDriverWait(driver, Duration.ofMinutes(1).plusSeconds(1)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(USER_NAME)));
        return driver.findElement(By.cssSelector(USER_NAME)).isDisplayed();
    }
}
