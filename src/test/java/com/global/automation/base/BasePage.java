package com.global.automation.base;

import com.global.automation.utils.IConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage implements IConstants {

    protected WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(BasePage.class);
    private Properties properties;


}
