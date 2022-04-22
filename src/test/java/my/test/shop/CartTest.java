package my.test.shop;

import io.qameta.allure.*;
import my.test.shop.extensions.AllureListener;
import my.test.shop.pages.*;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest {
    private AuthPage authPage;
    private HomePage homePage;
    private CatalogPage catalogPage;
    private CartPage cartPage;

    @RegisterExtension
    AllureListener watcher = new AllureListener("target/surefire-reports");

    @BeforeEach
    void setup() {
        homePage = new HomePage();
        authPage = new AuthPage();
        catalogPage = new CatalogPage();
        cartPage = new CartPage();
    }

    @ParameterizedTest
    @ValueSource(ints = { 3})
    @DisplayName("Add to cart")
    @AllureId("Cart_1")
    @Feature("Cart")
    @Story("Cart flow")
    @Epic("Cart")
    @Description("Verify that products can be add to cart")
    @Severity(SeverityLevel.CRITICAL)
    public void addToCart(int numberOfProductInTheList){
        authPage.login();
        homePage.goToProductList();
        List<String> nameOfProductInCart = catalogPage.addProductsToCart(numberOfProductInTheList);
        catalogPage.goToShoppingCart();
        List<String> productsNames = cartPage.getProductsNames();
        List<Integer> productsQuantity = cartPage.getProductsQuantity();
        List<Double> productPrice = cartPage.getProductPrice();
        Double totalProductPrice = cartPage.getTotalProductPrice();
        boolean namesInCartAndBeforeOrder = compareProductNamesInCartAndBeforeOrder(productsNames, nameOfProductInCart);
        Double calculatedTotalPrice = calculateTotalPrice(productPrice, productsQuantity);
        Assertions.assertAll("All products are in the cart and total is correct",
                () -> assertTrue(namesInCartAndBeforeOrder),
                () -> assertEquals(calculatedTotalPrice, totalProductPrice, "Total price is correct"));
    }

    private boolean compareProductNamesInCartAndBeforeOrder(List<String> productsInOrder, List<String> productsInCart){
        boolean flag = true;
        int size = productsInOrder.size();
        if(size != productsInOrder.size()){
            return false;
        }
        for(int i = 0; i < size; i++){
            if (productsInOrder.get(i).equals(productsInCart.get(i))){
                flag = true;
            } else{
                flag = false;
            }
        }
        return flag;
    }

    private Double calculateTotalPrice(List<Double> productsPrice, List<Integer> productsQuantity){
        if (productsPrice.size() != productsQuantity.size()){
            return null;
        }
        Double totalPrice = 0.00;
        int size = productsPrice.size();
        for(int i = 0; i < size; i++){
            totalPrice += productsPrice.get(i) * productsQuantity.get(i);
        }
        return totalPrice;
    }

    @AfterEach
    void cleanup() {
    }

    @AfterClass
    public void deleteAllProductsFromCart(){
        cartPage.deleteAllProductsFromCart();
    }
}
