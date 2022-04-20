package my.test.auto.pages;

import my.test.auto.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomeAutoPage {

    private WebDriver driver;

    public HomeAutoPage() {
        PageFactory.initElements(driver, this);
        this.driver = Driver.getInstance().getChromeDriver();
    }

}
