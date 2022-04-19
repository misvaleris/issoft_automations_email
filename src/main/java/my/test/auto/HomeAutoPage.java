package my.test.auto;

import my.test.email.CreateDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomeAutoPage {

    private WebDriver driver;

    public HomeAutoPage() {
        PageFactory.initElements(driver, this);
        this.driver = CreateDriver.getInstance().getChromeDriver();
    }

}
