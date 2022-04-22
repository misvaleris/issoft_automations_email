package my.test.shop.pages;

import io.qameta.allure.Step;
import my.test.shop.driver.Driver;
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

public class CatalogPage {

    @FindBy(css = "[class='product-image-container']")
    private List<WebElement> productContainers;

    @FindBy(css = ".quick-view")
    private List<WebElement> quickViews;

    @FindBy(css = "#buy_block #wishlist_button")
    private WebElement wishlistButton;

    @FindBy(css = "[title = Close]")
    private WebElement closeButton;

    @FindBy(css = "#product h1[itemprop='name']")
    private WebElement productName;

    @FindBy(css = "p#add_to_cart button")
    private WebElement addToCartButton;

    @FindBy(css = "div#layer_cart .cross")
    private WebElement closeWindowButton;

    @FindBy(css = ".shopping_cart a")
    private WebElement viewShoppingCartButton;

    private WebDriver driver;

    public CatalogPage() {
        this.driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @Step("Add product in wishlist")
    public List<String> addProductsToWishList(int numberInList) {
        int size = productContainers.size();

        if (size >= numberInList) {
            List<String> productNames = new ArrayList<>();
            for (int i = 0; i < numberInList; i++) {
                productNames.add(selectProducts(i));
                new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(wishlistButton)).click();
                new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(closeButton)).click();
                driver.switchTo().defaultContent();
                new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(closeButton)).click();
            }
            return productNames;
        }
        return null;
    }

    @Step("Add products to cart")
    public List<String> addProductsToCart(int numberInList) {
        int size = productContainers.size();

        if (size >= numberInList) {
            List<String> productNames = new ArrayList<>();
            for (int i = 0; i < numberInList; i++) {
                productNames.add(selectProducts(i));
                new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
                driver.switchTo().defaultContent();
                new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(closeWindowButton)).click();
            }
            return productNames;
        }
        return null;
    }

    @Step("Select products")
    public String selectProducts(int numberInList) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productContainers.get(numberInList));
        new Actions(driver).moveToElement(productContainers.get(numberInList)).perform();
        new Actions(driver).moveToElement(quickViews.get(numberInList)).click().perform();
        driver.switchTo().frame(0);
        return getProductName().trim();
    }

    @Step("Retrieve product name")
    public String getProductName() {
        return productName.getText();
    }

    @Step("Go to shopping cart")
    public CartPage goToShoppingCart() {
        viewShoppingCartButton.click();
        return new CartPage();
    }
}
