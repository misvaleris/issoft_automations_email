package my.test.shop.pages;

import io.qameta.allure.Step;
import my.test.shop.driver.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WishlistPage {
    @FindBy(css = "#block-history table")
    private WebElement wishlistTable;

    private String wishlistTableStr = "#block-history table";

    @FindBy(css = "#block-history table td a")
    private WebElement wishlist;

    @FindBy(css = "p input[id=quantity_1_1]")
    private WebElement quantity;

    @FindBy(css = "#block-history table td[class=wishlist_delete] a")
    private WebElement deleteWishlist;

    @FindBy(css = "#name")
    private WebElement name;

    @FindBy(id = "submitWishlist")
    private WebElement submit;

    private WebDriver driver;
    private String productDescriptionStr = "#s_title";

    public WishlistPage() {
        this.driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @Step("Verify that wishlist table is presented")
    public boolean isElementArePresent() {
        try {
            return driver.findElement(By.cssSelector(wishlistTableStr)).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Step("Select wishlist")
    public void selectWishlist() {
        wishlist.click();
    }

    @Step("Get name of product from wishlist")
    public String nameOfProductInWishlist() {
        String textOfProductDescription =  new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(productDescriptionStr)))
                .getText();
        String[] descriptions = textOfProductDescription.split("\n");
        String productName = descriptions[0].trim();
        return productName;
    }

    @Step("Get product quantity")
    public int getProductQuantity() {
        return Integer.valueOf(quantity.getAttribute("value"));
    }

    @Step("Delete product from wishlist")
    public void deleteProductFromWishlist() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(deleteWishlist)).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Step("Create wishlist")
    public void createWishlist(String nameOfWishlist) {
        name.sendKeys(nameOfWishlist);
        submit.click();
    }
}
