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

    @FindBy(css = "[title='My wishlists'] > span")
    private WebElement wishlistButton;

    private WebDriver driver;

    public HomePage() {
        this.driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @Step("Go to product list")
    public CatalogPage goToProductList() {
        menuWomenButton.click();
        return new CatalogPage();
    }

    @Step("Retrieve user name from header")
    public String getUserName() {
        return viewAccountName.getText();
    }

    @Step("Go to Cart")
    public CartPage goToCart() {
        //add button
        return new CartPage();
    }

    @Step("Go to Wishlist")
    public WishlistPage goToWishlist() {
        wishlistButton.click();
        return new WishlistPage();
    }

    @Step("Go to Account")
    public void goToAccount() {
        userAccountButton.click();
    }
}
