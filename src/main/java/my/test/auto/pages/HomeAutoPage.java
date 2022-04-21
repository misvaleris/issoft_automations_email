package my.test.auto.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeAutoPage {

    @FindBy(css = ".lnk_wishlist")
    private WebElement wishList;

    private WebDriver driver;

    public HomeAutoPage() {
        PageFactory.initElements(this.driver, this);
    }

    @Step("Get title name")
    public String getTitleName(){
        return driver.getTitle();
    }

    @Step("Go into wishlist")
    public void goIntoWishlist(){
        wishList.click();
    }

}
