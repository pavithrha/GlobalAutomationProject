package com.global.automation.pageobject;

import com.global.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Cart class represents activities like viewing cart and proceeding to checkout
 */

public class Cart extends BasePage {

    private By cart = By.xpath("//a[@title='View my shopping cart']");
    private By proceedToCheckout = By.xpath("//a[@id='button_order_cart']");

    public Cart(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement viewCart() {
        return driver.findElement(cart);
    }

    public void checkout() {
        driver.findElement(proceedToCheckout).click();
    }
}
