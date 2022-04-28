package com.global.automation.pageobject;

import com.global.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * SearchProduct class represents activities related to search like input in search box, clear search box and click search button
 */

public class SearchProduct extends BasePage {

    private By searchBox = By.xpath("//input[@id='search_query_top']");
    private By clickSearch = By.xpath("//button[@name='submit_search']");

    public SearchProduct(WebDriver driver) {
        this.driver = driver;
    }

    public void productInSearchBox(String productName) {
        driver.findElement(searchBox).sendKeys(productName);
    }
    public void clickSearchbtn() {
        driver.findElement(clickSearch).click();
    }
    public void clearSearchBox() {
        driver.findElement(searchBox).clear();
    }
}
