package com.global.automation.testcases;

import com.global.automation.base.TestBase;
import com.global.automation.pageobject.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

/**
 * This is a test case class which executes different workflows like adding product, editing and validation of cart.
 */
public class ShoppingWorkflowTest extends TestBase {

    private SearchResultPage searchResultPage;
    private SearchProduct searchProduct;
    private ProductDetailPage productDetailPage;
    private Actions action;
    private Cart cart;
    private CartSummary cartSummary;
    private static Logger logger = LoggerFactory.getLogger(ShoppingWorkflowTest.class);

    /**
     * Adding all the products in the cart
     * @param productName
     * @param qty
     * @param size
     * @param color
     */
    @Test(dataProvider = "getProducts")
    public void addProductToCart(String productName, int qty, String size, String color) {
        searchProduct.productInSearchBox(productName);
        searchProduct.clickSearchbtn();
        searchResultPage.validateSearchPageLoaded();
        searchProduct.clearSearchBox();
        action.moveToElement(searchResultPage.locateProductTab(searchResultPage.getProductTab(color.toLowerCase()))).build().perform();
        searchResultPage.moreBtn().isDisplayed();
        searchResultPage.moreBtn().click();
        productDetailPage.selectQuantity(qty);
        productDetailPage.selectSize(size);
        productDetailPage.selectColor(color);
        productDetailPage.clickAddToCart();
        productDetailPage.clickContinueShopping();
        searchProduct.clearSearchBox();
        logger.info("Successfully added product "+productName+", size "+size+" , color "+color+ "in the cart");
    }

    /**
     * Proceeding to checkout
     */
    @Test(dependsOnMethods = "addProductToCart")
    public void proceedToCheckout() {
        logger.info("------Proceed To Checkout------");
        action.moveToElement(cart.viewCart()).build().perform();
        cart.checkout();
    }

    /**
     * Remove evening dress from the cart
     */
    @Test(dependsOnMethods = "proceedToCheckout")
    public void removeEveningDressFromCart() {
        cartSummary.deleteItemsFromCart("Printed Dress");
        logger.info("Successfully removed Evening dress from the cart");
    }

    /**
     * Add one more faded short sleeve in the cart
     */
    @Test(dependsOnMethods = "removeEveningDressFromCart")
    public void addFadedShortSleeve() {
        cartSummary.addProductQtyInCart("Faded Short Sleeve T-shirts", 2);
        logger.info("Added one more Faded Short Sleeve T-shirts to the cart");
    }

    /**
     * Validate price for each product in the cart as per quantity
     */
    @Test(dependsOnMethods = "addFadedShortSleeve")
    public void validatePriceForEachProduct() {
        //cartSummary.validateProductPrice();
        List<WebElement> cartItems = cartSummary.getCartItems();
        for (int i = 1; i <= cartItems.size(); i++) {
            Double expectedProductTotalPrice = cartSummary.getProductQty(i) * cartSummary.getProductPrice(i);
            Assert.assertEquals(cartSummary.getProductTotalPrice(i), expectedProductTotalPrice);
        }
        logger.info("Validated each product price");

    }

    /**
     * Validate total cart price is equivalent to sum of all product prices
     */
    @Test(dependsOnMethods = "validatePriceForEachProduct")
    public void validateTotalCartPrice() {
        //cartSummary.validateCartTotal();
        Assert.assertEquals(cartSummary.getCartTotal(), cartSummary.calculateTotalProductPrice());
        logger.info("Validated total cart price");
    }

    /**
     * List of product name with there respective quantity, size and color
     * @return
     */
    @DataProvider
    public Object[][] getProducts() {
        //return new Object[][] {{"Faded Short Sleeve T Shirt", 1, "M", "Blue"}};
        return new Object[][] {{"Faded Short Sleeve T Shirt", 1, "M", "Blue"},{"Evening Dress", 1, "S","Beige"}, {"Printed Summer Dress", 1, "M", "Orange"}};
    }
    @BeforeTest
    public void setup() {
        super.setup();
        searchProduct = new SearchProduct(driver);
        searchResultPage = new SearchResultPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        cart = new Cart(driver);
        cartSummary = new CartSummary(driver);
        action = new Actions(driver);
    }
}
