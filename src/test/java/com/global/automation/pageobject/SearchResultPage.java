package com.global.automation.pageobject;

import com.global.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * SearchResultPage class represents activities performed on product search result page like locating product result tab and performing activities on it.
 */

public class SearchResultPage extends BasePage {
    private By searchPage = By.xpath("//body[@id='search']");
    private By searchProductResult = By.xpath("//div[@class='product-container']/div/h5/a");
    private By moreBtn = By.xpath("//a[@class='button lnk_view btn btn-default']");
    private By productResultTab = By.xpath("//div[@id='center_column']/ul/li");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public void validateSearchPageLoaded() {
        driver.findElement(searchPage).isEnabled();
    }

    public String getSearchProductName() {
        return driver.findElement(searchProductResult).getText();
    }
    public WebElement locateProductTab(int productTab) {
        //return driver.findElement(searchProductResult);
        return driver.findElement(By.xpath("//div[@id='center_column']/ul/li["+productTab+"]/div[@class='product-container']/div/h5/a"));
    }

    public WebElement moreBtn() {
        return driver.findElement(moreBtn);
    }

    public List<WebElement> listOfProductResult() {
        return driver.findElements(By.xpath("//div[@id='center_column']/ul/li"));
    }

    public Integer getProductTab(String color) {
        int i = 1;
        if (listOfProductResult().size() > 1) {
            for (i = 1; i <= listOfProductResult().size(); i++) {
                driver.findElement(By.xpath("//div[@id='center_column']/ul/li["+i+"]/div/div[2]/div[@class='color-list-container']/ul/li/a[contains(@href, '"+color+"')]")).isDisplayed();
                break;
            }
        }
        return i;
    }

}
