package com.global.automation.pageobject;

import com.global.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * ProductDetailPage class represents all the activities performed on product detail page
 */

public class ProductDetailPage extends BasePage {

    By productQty = By.xpath("//input[@id='quantity_wanted']");
    By productSize = By.xpath("//select[@id='group_1']");
    By addToCartBtn = By.xpath("//button[@name='Submit']");
    By continueShoppingBtn = By.xpath("//span[@title='Continue shopping']");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectQuantity(int qty) {
        driver.findElement(productQty).clear();
        driver.findElement(productQty).sendKeys(String.valueOf(qty));
    }
    public void selectSize(String size) {
        WebElement sizeOptions = driver.findElement(productSize);
        Select selectSize= new Select(sizeOptions);
        selectSize.selectByVisibleText(size);
    }

    public void selectColor(String color) {
        driver.findElement(By.xpath("//a[@name='"+color+"']")).click();
    }

    public void clickAddToCart() {
        driver.findElement(addToCartBtn).click();
    }

    public void clickContinueShopping() {
        driver.findElement(continueShoppingBtn).click();
    }
}
