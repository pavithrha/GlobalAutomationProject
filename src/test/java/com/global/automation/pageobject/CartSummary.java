package com.global.automation.pageobject;

import com.global.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * CartSummary class represents activities like get all cart item list and perform delete, add and reduce items, get shipping price, get Tax price and validation on product price and cart total
 */
public class CartSummary extends BasePage {
    private By listOfCartItems = By.xpath("//td[@class='cart_description']/p/a");
    private By cartTotal = By.xpath("//td/span[@id='total_price']");
    private By shippingPrice = By.xpath("//td[@id='total_shipping']");
    private By taxPrice = By.xpath("//td[@id='total_tax']");

    public CartSummary(WebDriver driver) {
        this.driver = driver;
    }

    public void deleteItemsFromCart(String productName) {
        List<WebElement> cartItems = driver.findElements(listOfCartItems);
        for (int i = 1; i <= cartItems.size(); i++) {
            String pName = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[@class='cart_description']/p/a")).getText();
            if (pName.equalsIgnoreCase(productName)) {
                driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[@class='cart_delete text-center']/div/a")).click();
            }
        }
    }

    public void addProductQtyInCart(String productName, int itemCount) {
        List<WebElement> cartItems = driver.findElements(listOfCartItems);
        for (int i = 1; i <= cartItems.size(); i++) {
            String pName = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[@class='cart_description']/p/a")).getText();
            if (pName.equalsIgnoreCase(productName)) {
                for (int j = 1; j < itemCount; j++) {
                    driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[@class='cart_quantity text-center']/div/a[2]")).click();
                }
            }
        }
    }

    public void reduceProductQtyInCart(String productName, int itemCount) {
        List<WebElement> cartItems = driver.findElements(listOfCartItems);
        for (int i = 1; i <= cartItems.size(); i++) {
            String pName = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[@class='cart_description']/p/a")).getText();
            if (pName.equalsIgnoreCase(productName)) {
                for (int j = 1; j < itemCount; j++) {
                    driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[@class='cart_quantity text-center']/div/a[1]")).click();
                }
            }
        }
    }


    public List<WebElement> getCartItems() {
        return driver.findElements(listOfCartItems);
    }

    public Double getProductPrice(Integer index) {
        return Double.valueOf(driver.findElement(By.xpath("//tbody/tr[" + index + "]/td[@class='cart_unit']/span/span")).getText().substring(1));
    }

    public Integer getProductQty(Integer index) {
        return Integer.valueOf(driver.findElement(By.xpath("//tbody/tr[" + index + "]/td[contains(@class, 'cart_quantity')]/input[1]")).getAttribute("value"));
    }

    public Double getProductTotalPrice(Integer index) {
        return Double.valueOf(driver.findElement(By.xpath("//tbody/tr[" + index + "]/td[@class='cart_total']/span")).getText().substring(1));
    }

    public Double getCartTotal() {
        return Double.valueOf(driver.findElement(cartTotal).getText().substring(1));
    }

    public double calculateTotalProductPrice() {
        List<WebElement> cartItems = driver.findElements(listOfCartItems);
        double expectedCartTotal = 0;
        for (int i = 1; i <= cartItems.size(); i++) {
            double productPrice = Double.valueOf(driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[@class='cart_total']/span")).getText().substring(1));
            expectedCartTotal = productPrice + expectedCartTotal;
        }
        return expectedCartTotal + getShippingPrice() + getTax();
    }

    public Double getShippingPrice() {
        return Double.valueOf(driver.findElement(shippingPrice).getText().substring(1));
    }

    public Double getTax() {
        return Double.valueOf(driver.findElement(taxPrice).getText().substring(1));
    }

}



