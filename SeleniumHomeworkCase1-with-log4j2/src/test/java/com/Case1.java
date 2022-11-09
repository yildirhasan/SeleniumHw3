package com;

import extension.ScreenshotWatcher5;
import extension.TestExtension;
import extension.TestStatusExtension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@ExtendWith({TestExtension.class, TestStatusExtension.class,})
public class Case1  extends BaseTest {
    private static final Logger logger = LogManager.getLogger(Case1.class);

    @RegisterExtension
    ScreenshotWatcher5 watcher = new ScreenshotWatcher5(driver, "target/surefire-reports");


    @Tag("Case_1_Test")
    @Test
    public void test() {
            wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".category-3517 > ul > li:nth-child(1)  > a"))).click();
            Assertions.assertEquals("https://www.sahibinden.com/kategori/otomobil", driver.getCurrentUrl());
            logger.info("Otomobil kategorisine yonlendirildi ");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".all-classifieds-link"))).click();
            Assertions.assertEquals("https://www.sahibinden.com/otomobil", driver.getCurrentUrl());
            String categoryText = driver.findElement(By.cssSelector(".result-text-sub-group > div > h1")).getText();
            Assertions.assertEquals("Otomobil", categoryText);
            //arama sayfasi cektigimiz title , price , km
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".searchResultsRowClass .searchResultsItem a")));
            String searchPageClasssifiedID = driver.findElement(By.cssSelector(".searchResultsItem .searchResultsTitleValue div")).getAttribute("data-classified-id");//ilan no
            String searchPageClassifiedName = driver.findElement(By.cssSelector(".searchResultsItem .searchResultsTitleValue .action-wrapper +a")).getAttribute("title");//ilan title
            String searchPageClassifiedPrice = driver.findElement(By.cssSelector(".searchResultsRowClass .searchResultsItem .searchResultsPriceValue  div span")).getText();//ilan  fiyat
            /* ilan detay acildiktan sonra */
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".searchResultsItem a"))).click();
            logger.info("Ilk ilana tiklanildi ");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".classifiedDetailTitle h1")));
            String classifiedsTitle = driver.findElement(By.cssSelector(".classifiedDetailTitle h1")).getText();
            String classifiedNumber = driver.findElement(By.id("classifiedId")).getText();
            String classifiedKm = driver.findElement(By.xpath("//strong[text()='KM']/../span")).getText();
            String classifiedPrice = driver.findElement(By.cssSelector(".classifiedInfo > h3")).getText();
            Assertions.assertAll
                    (
                            () -> Assertions.assertTrue(driver.getCurrentUrl().contains(classifiedNumber)),
                            () -> Assertions.assertEquals(searchPageClasssifiedID,classifiedNumber),
                            () -> Assertions.assertEquals(searchPageClassifiedName,classifiedsTitle),
                            () -> Assertions.assertTrue(classifiedPrice.contains(searchPageClassifiedPrice))
                            );
            logger.info("\n" + "Ilan basligi : " + classifiedsTitle +
                        "\n" + "Ilan number : " + classifiedNumber  +
                        "\n" + "KM bilgisi :" + classifiedKm +
                        "\n" + "Fiyat Bilgisi " + classifiedPrice);
        Assertions.assertTrue(driver.getCurrentUrl().contains(classifiedNumber));



    }

        @ParameterizedTest
        @CsvSource({
                "iPhone 12 Mini,#search_cats ul li.cl4,1",
                "PlayStation 5,#search_cats ul li.cl3 div a h2,2",
                "Koşu Bandı,#search_cats ul li.cl3 div a h2,3",
                "Elektrikli Isıtıcı,#search_cats ul li.cl3 div a h2,4",
                "Toyota,#search_cats ul li.cl2 div a h2,5"})
        @Tag("Test_Case_2")
        @DisplayName("Search Test ")
        public void testCase2(String categoryTitle, String selector, int index){
            logger.info("Test case 2 basladi ");
            WebElement popularCategories = driver.findElement(By.cssSelector("#container > div.homepage > div > div.homepage-content > div:nth-child(12) > ul > li:nth-child(" + index + ") > a"));
            popularCategories.click();

            WebElement category = driver.findElement(By.cssSelector(selector));
            String SearchResults = category.getText();
            Assertions.assertTrue(SearchResults.contains(categoryTitle));

        }



}
