package com.github.swapbook.selenium;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class IntegrationTest {
    private static WebDriver driver;
    private String host = "http://localhost:3000";

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/com/github/swapbook/selenium/chromedriver78linux");
        driver = new ChromeDriver();
    }

    @Test
    public void SuccessfullyLoginUser() throws Exception {
        driver.navigate().to(host);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        driver.findElements(By.className("loginNaviBar")).get(0).click();

        driver.findElement(By.name("email")).sendKeys("test@test.pl");
        driver.findElement(By.name("password")).sendKeys("test1234");

        driver.findElement(By.tagName("button")).click();
    }

    @Test
    public void ErrorLoginUser() {
        driver.navigate().to(host);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        driver.findElements(By.className("loginNaviBar")).get(0).click();

        driver.findElement(By.name("email")).sendKeys("test@test.pl");
        driver.findElement(By.name("password")).sendKeys("test1234blad");

        driver.findElement(By.tagName("button")).click();

        Assert.assertEquals("Błędne dane", driver.findElement(By.name("error")).getText());
    }

    @Test
    public void ConfirmRegisterPageIsAvailable() {
        driver.navigate().to(host);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        driver.findElements(By.className("registerNaviBar")).get(0).click();

        Assert.assertTrue(driver.findElement(By.name("name")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.name("email")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.name("password")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.name("address")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.name("regulamin")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.name("regulamin")).isSelected());
        Assert.assertTrue(driver.findElement(By.tagName("button")).isDisplayed());
    }

    @AfterClass
    public static void cleanUp(){
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
