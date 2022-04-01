package my.test.email;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage {

    WebDriver driver = CreateDriver.getInstance();

    @FindBy(xpath = "//span[text()='Log out']")
    private WebElement profileButton;

    @FindBy(xpath = "//h1[@class='passp-title ']")
    private WebElement chooseAnAccountTitle;

    public LogoutPage() {
        PageFactory.initElements(driver,this);
    }

    public boolean logout() {

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Log out']")));
        profileButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[@class='passp-title ']")));
        chooseAnAccountTitle.isDisplayed();

        return chooseAnAccountTitle.isDisplayed();
    }
}
