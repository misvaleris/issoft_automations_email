package my.test.auto;

import io.qameta.allure.*;
import my.test.auto.pages.CatalogAutoPage;
import my.test.auto.pages.HeaderAutoPage;
import my.test.auto.pages.ShoppingCartAutoPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Shop functionality")
@Feature("Add into Cart")
@Tag("Critical")
public class CartTest {
    private ShoppingCartAutoPage shoppingCart;
    HeaderAutoPage headerAutoPage;

    @ParameterizedTest
    @ValueSource(ints = { 3})
    @Story("Add to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that products can be add to cart")
    @Tag("stable")
    public void addToCart(int numberOfProductInTheList){
        headerAutoPage.loginToSite();
        headerAutoPage.goToProductList();
        CatalogAutoPage catalog = new CatalogAutoPage();
        List<String> nameOfProductInCart = catalog.addProductsToCart(numberOfProductInTheList);
        catalog.goToShoppingCart();
        shoppingCart = new ShoppingCartAutoPage();
        List<String> productsNames = shoppingCart.getProductsNames();
        List<Integer> productsQuantity = shoppingCart.getProductsQuantity();
        List<Double> productPrice = shoppingCart.getProductPrice();
        Double totalProductPrice = shoppingCart.getTotalProductPrice();
        boolean namesInCartAndBeforeOrder = compareProductNamesInCartAndBeforeOrder(productsNames, nameOfProductInCart);
        Double calculatedTotalPrice = calculateTotalPrice(productPrice, productsQuantity);
        Assertions.assertAll("All products are in the cart and total is correct",
                () -> assertTrue(namesInCartAndBeforeOrder),
                () -> assertEquals(calculatedTotalPrice, totalProductPrice, "Total price is correct"));
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

    @AfterEach
    public void deleteAllProductsFromCart(){
        shoppingCart.deleteAllProductsFromCart();
    }
}
