package my.test.auto;


import io.qameta.allure.*;
import my.test.auto.driver.Driver;
import my.test.auto.pages.CatalogAutoPage;
import my.test.auto.pages.HeaderAutoPage;
import my.test.auto.pages.HomeAutoPage;
import my.test.auto.pages.WishlistAutoPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Shop functionality")
@Feature("Add into Wishlist")
@Tag("Critical")
public class WishlistTest {
    WishlistAutoPage wishlist;
    HeaderAutoPage header;

    @Test
    @Story("Content of wishlist tests")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that wishlist is empty")
    @Tag("SkipCleanup")
    public void verifyWishlistIsEmpty(){
        header.loginToSite();
        HomeAutoPage homeAutoPage = new HomeAutoPage();
        wishlist = new WishlistAutoPage();
        homeAutoPage.goIntoWishlist();
        assertTrue(!wishlist.isElementIsPresent(), "wishlist is empty");
    }

    @ParameterizedTest
    @ValueSource(ints = { 1})
    @Story("Add to wishlist")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that product can be add to auto-created Wishlist")
    @Tag("stable")
    public void addToAutoCreatedWishlistProduct(int numberOfProductInTheList){
        header.loginToSite();
        CatalogAutoPage catalog = new CatalogAutoPage();
        header.goToProductList();
        List<String> productsName = catalog.addProductsToWishList(numberOfProductInTheList);
        HomeAutoPage homeAutoPage = new HomeAutoPage();
        header.goToAccount();
        wishlist = new WishlistAutoPage();
        homeAutoPage.goIntoWishlist();
        wishlist.selectWishlist();
        String nameOfProductInWishlist = wishlist.nameOfProductInWishlist();
        int quantity = wishlist.retrieveProductQuantity();
        Assertions.assertAll("Wishlist was created automatically and my product is in the list",
                () -> assertTrue(wishlist.isElementIsPresent()),
                () -> assertEquals( nameOfProductInWishlist, productsName.get(0), "My product in wishlist"),
                () -> assertEquals( quantity, numberOfProductInTheList, "Quantity of product is 1")
        );
    }

    @ParameterizedTest
    @ValueSource(ints = { 1})
    @Story("Add to wishlist")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that product can be add to auto-created Wishlist")
    @Tag("stable")
    public void addToManuallyCreatedWishlistProduct(int numberOfProductInTheList){
        header.loginToSite();
        HomeAutoPage homeAutoPage = new HomeAutoPage();
        homeAutoPage.goIntoWishlist();
        wishlist = new WishlistAutoPage();
        wishlist.createWishlist("My first list");
        CatalogAutoPage catalog = new CatalogAutoPage();
        header.goToProductList();
        List<String> productsName = catalog.addProductsToWishList(numberOfProductInTheList);
        header.goToAccount();
        homeAutoPage.goIntoWishlist();
        wishlist.selectWishlist();
        String nameOfProductInWishlist = wishlist.nameOfProductInWishlist();
        int quantity = wishlist.retrieveProductQuantity();
        Assertions.assertAll("Wishlist was created automatically and my product is in the list",
                () -> assertTrue(wishlist.isElementIsPresent()),
                () -> assertEquals( nameOfProductInWishlist, productsName.get(0), "My product in wishlist"),
                () -> assertEquals( quantity, numberOfProductInTheList, "Quantity of product is 1")
        );
    }


    @AfterEach
    public void deleteWishlist(TestInfo testInfo){
        if(testInfo.getTags().contains("SkipCleanup")) {
            return;
        }
        wishlist.deleteProductFromWishlist();
        Driver.getInstance().deleteCookies();
    }
}
