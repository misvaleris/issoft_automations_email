package my.test.shop;

import io.qameta.allure.*;
import my.test.shop.driver.Driver;
import my.test.shop.extensions.AllureListener;
import my.test.shop.pages.AuthPage;
import my.test.shop.pages.CatalogPage;
import my.test.shop.pages.HomePage;
import my.test.shop.pages.WishlistPage;
import org.junit.AfterClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WishlistTest {
    private AuthPage authPage;
    private WishlistPage wishlistPage;
    private HomePage homePage;
    private CatalogPage catalogPage;

    @RegisterExtension
    AllureListener watcher = new AllureListener("target/surefire-reports");

    @BeforeEach
    void setup() {
        homePage = new HomePage();
        authPage = new AuthPage();
        wishlistPage = new WishlistPage();
    }

    @Test
    @DisplayName("Content of wishlist tests")
    @AllureId("Wishlist_1")
    @Feature("Wishlist")
    @Story("Wishlist flow")
    @Epic("Wishlist")
    @Description("Verification that there is no Wishlist in account Settings")
    @Severity(SeverityLevel.CRITICAL)
    void verifyWishlistIsEmpty() {
        authPage.login();
        homePage.goToWishlist();
        Assertions.assertFalse(wishlistPage.isElementArePresent(), "Wishlist is not empty");
    }


    @DisplayName("Add to wishlist")
    @AllureId("Wishlist_2")
    @Feature("Wishlist")
    @Story("Wishlist flow")
    @Epic("Wishlist")
    @Description("Verify that product can be add to auto-created Wishlist")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @ValueSource(ints = {1})
    public void addToAutoCreatedWishlistProduct(int numberOfProductInTheList) {
        authPage.login();
        homePage.goToProductList();
        List<String> productsName = catalogPage.addProductsToWishList(numberOfProductInTheList);
        homePage.goToAccount();
        homePage.goToWishlist();
        wishlistPage.selectWishlist();
        String nameOfProductInWishlist = wishlistPage.nameOfProductInWishlist();
        int quantity = wishlistPage.getProductQuantity();
        Assertions.assertAll("Wishlist was created automatically and my product is in the list",
                () -> assertEquals(nameOfProductInWishlist, productsName.get(0), "My product in wishlist"),
                () -> assertEquals(quantity, numberOfProductInTheList, "Quantity of product is 1")
        );
    }

    @DisplayName("Manually add to wishlist")
    @AllureId("Wishlist_3")
    @Feature("Wishlist")
    @Story("Wishlist flow")
    @Epic("Wishlist")
    @Description("Verify that product can be add to auto-created Wishlist")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @ValueSource(ints = {3})
    public void addToManuallyCreatedWishlistProduct(int numberOfProductInTheList) {
        authPage.login();
        homePage.goToWishlist();
        wishlistPage.createWishlist("My first list");
        CatalogPage catalog = new CatalogPage();
        homePage.goToProductList();
        List<String> productsName = catalog.addProductsToWishList(numberOfProductInTheList);
        homePage.goToAccount();
        homePage.goToWishlist();
        wishlistPage.selectWishlist();
        String nameOfProductInWishlist = wishlistPage.nameOfProductInWishlist();
        int quantity = wishlistPage.getProductQuantity();
        Assertions.assertAll("Wishlist was created automatically and my product is in the list",
                () -> assertEquals(nameOfProductInWishlist, productsName.get(0), "My product in wishlist"),
                () -> assertEquals(quantity, numberOfProductInTheList, "Quantity of product is 3")
        );
    }

    @AfterEach
    void cleanup() {
    }

    @AfterClass
    public void deleteWishlist() {
        wishlistPage.deleteProductFromWishlist();
        Driver.getInstance().deleteCookies();
    }
}
