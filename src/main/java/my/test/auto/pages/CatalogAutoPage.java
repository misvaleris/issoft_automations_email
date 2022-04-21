package my.test.auto.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CatalogAutoPage {

    @FindBy(css = "[class='product-image-container']")
    private List<WebElement> productContainers;

    @FindBy(css = ".quick-view")
    private List<WebElement> quickViews;

    @FindBy(css = "#buy_block #wishlist_button")
    private WebElement wishlistButton;

    @FindBy(css = "[title = Close]")
    private WebElement close;

    @FindBy(css = "#product h1[itemprop='name']")
    private WebElement productName;

    @FindBy(css = "p#add_to_cart button")
    private WebElement addToCartButton;

    @FindBy(css = "div#layer_cart .cross")
    private WebElement closeWindow;

    @FindBy(css = ".shopping_cart a")
    private WebElement viewShoppingCart;

    private WebDriver driver;

    public CatalogAutoPage() {
        PageFactory.initElements(this.driver, this);
    }

    @Step("Add product to wishlist")
    public List<String> addProductsToWishList(int numberInList) {
        int size = productContainers.size();

        if (size >= numberInList) {
            List<String> productNames = new ArrayList<>();
            for (int i = 0; i < numberInList; i++) {
                productNames.add(selectProducts(i));
                new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(wishlistButton)).click();
                new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(close)).click();
                driver.switchTo().defaultContent();
                new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(close)).click();
            }
            return productNames;
        }
        return null;
    }

    @Step("Retrieve product name")
    public String retrieveProductName() {
        return productName.getText();
    }

    @Step("Select products")
    public String selectProducts(int numberInList) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productContainers.get(numberInList));
        new Actions(driver).moveToElement(productContainers.get(numberInList)).perform();
        new Actions(driver).moveToElement(quickViews.get(numberInList)).click().perform();
        driver.switchTo().frame(0);
        return retrieveProductName().trim();
    }

    @Step("Add products to cart")
    public List<String> addProductsToCart(int numberInList) {
        int size = productContainers.size();

        if (size >= numberInList) {
            List<String> productNames = new ArrayList<>();
            for (int i = 0; i < numberInList; i++) {
                productNames.add(selectProducts(i));
                new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
                driver.switchTo().defaultContent();
                new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(closeWindow)).click();
            }
            return productNames;
        }
        return null;
    }

    @Step("Go to shopping cart")
    public void goToShoppingCart() {
        viewShoppingCart.click();
    }
}
