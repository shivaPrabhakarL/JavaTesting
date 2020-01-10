package com.prabhakar;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SampleTest {
    @Test
    public void googleSearch() throws InterruptedException {
        System.out.println("Search Google");
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://www.google.com");
        webDriver.findElement(By.name("q")).sendKeys("selenium");
        Thread.sleep(2000);
        List<WebElement> elements = webDriver.findElements(By.xpath("//ul[@role='listbox']//li"));
        elements.get(0).click();
        Thread.sleep(3000);
        WebElement ele = webDriver.findElement(By.id("rcnt"));
        Assert.assertTrue(ele.getText().contains("selenium"));
        webDriver.quit();
    }
}
