package com.global.automation.base;

import com.global.automation.utils.IConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase implements IConstants {

    private Properties properties;
    protected BasePage basePage;
    protected WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(TestBase.class);

    public TestBase() {
        properties = new Properties();
        try (InputStream stream = TestBase.class.getResourceAsStream("/config.properties")) {
            properties.load(stream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite (alwaysRun = true)
    public void setup() {
        initialization();
    }

    @AfterSuite
    public void windowClose() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    /**
     *
     */
    public void initialization() {
        logger.info("Initialization started");
        String browserName = properties.getProperty(BROWSER);
        if (browserName.equalsIgnoreCase(CHROME)) {
            System.setProperty(CHROME_DRIVER, System.getProperty(USER_DIR) + "\\src\\test\\resources\\" + CHROME_EXE);
            driver = new ChromeDriver();
        }
        if (browserName.equalsIgnoreCase(FIREFOX)) {
            System.setProperty(FIREFOX_DRIVER, System.getProperty(USER_DIR) + "\\src\\test\\resources\\" + FIREFOX_EXE);
            driver = new FirefoxDriver();
        }
        driver.get(properties.getProperty(URL));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

        logger.info("Initialization ended");
    }
}
