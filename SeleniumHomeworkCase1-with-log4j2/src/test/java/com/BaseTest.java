package com;

import extension.ScreenshotWatcher5;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    protected WebDriver driver;
    protected String url = "https://www.sahibinden.com/";
    protected WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(Case1.class);

    @RegisterExtension
    ScreenshotWatcher5 watcher = new ScreenshotWatcher5(driver, "target/surefire-reports");
    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","/Users/test/Desktop/driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);
        List<Cookie> cookieList = new ArrayList<>();
        cookieList.add(new Cookie("testBox", "5", ".sahibinden.com", "/", null));
        cookieList.add(new Cookie("tbSite", "x", ".sahibinden.com", "/", null));
        cookieList.forEach(cookie -> driver.manage().addCookie(cookie));
        Cookie cookie = driver.manage().getCookieNamed("testBox");
        Cookie cookie2 = driver.manage().getCookieNamed("tbSite");
       logger.info("Siteye yonlendirildi " + " Set edilen  ilgili cookie " + cookie + cookie2);
        driver.navigate().refresh();
    }
    @AfterEach
    public void quit(){
        driver.close();
        logger.info("Driver kapandi ");


    }



}
