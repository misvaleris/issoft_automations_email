package my.test.shop.pages;

import io.qameta.allure.Step;
import my.test.shop.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {

    @FindBy(css = "tbody .product-name a")
    private List<WebElement> descriptions;

    @FindBy(css = "tbody input[value][type=text]")
    private List<WebElement> quantityValue;

    @FindBy(css = "tbody span[class=price] span")
    private List<WebElement> priceValue;

    @FindBy(css = "#total_product")
    private WebElement totalProductPriceValue;

    @FindBy(css = "tbody td[data-title=Delete] a")
    private List<WebElement> deleteElements;

    @FindBy(css = "#summary_products_quantity")
    private WebElement summaryProductsQuantityValue;

    private WebDriver driver;

    public CartPage() {
        this.driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @Step("Retrieve products name")
    public List<String> getProductsNames() {
        List<String> productsNames = new ArrayList<>();
        for (WebElement description : descriptions) {
            productsNames.add(description.getText());
        }
        return productsNames;
    }

    @Step("Retrieve quantity of every product")
    public List<Integer> getProductsQuantity() {
        List<Integer> productsQuantity = new ArrayList<>();
        for (WebElement quantityOfOneProduct : quantityValue) {
            productsQuantity.add(Integer.valueOf(quantityOfOneProduct.getAttribute("value")));
        }
        return productsQuantity;
    }

    @Step("Retrieve product price")
    public List<Double> getProductPrice() {
        List<Double> productsPrice = new ArrayList<>();
        for (WebElement productPrice : priceValue) {
            String productPriceText = productPrice.getText();
            productsPrice.add(Double.valueOf(productPriceText.substring(1).trim()));
        }
        return productsPrice;
    }

    @Step("Retrieve total product quantity")
    public Double getTotalProductPrice() {
        return Double.valueOf(totalProductPriceValue.getText().substring(1).trim());
    }

    @Step("Delete products from cart")
    public void deleteAllProductsFromCart() {
        int size = deleteElements.size();
        if (size > 0) {
            for (WebElement delete : deleteElements) {
                delete.click();
                size--;
                if (size == 1) {
                    new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.textToBePresentInElement(summaryProductsQuantityValue, size + " Product"));
                }
                if (size > 1) {
                    new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.textToBePresentInElement(summaryProductsQuantityValue, size + " Product"));
                }

            }
        }
    }
}
