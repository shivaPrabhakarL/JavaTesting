package com.prabhakar;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class DriverFunctions {
    public  WebDriver webDriver;

    public WebDriver DriverInit(){
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        webDriver = new ChromeDriver();
        return webDriver;
    }

    public WebElement findElement(String identifier, String value){
        if(identifier.equalsIgnoreCase("id"))
            return webDriver.findElement(By.id(value));
        if(identifier.equalsIgnoreCase("classname"))
            return webDriver.findElement(By.className(value));
        if(identifier.equalsIgnoreCase("xpath"))
            return webDriver.findElement(By.xpath(value));
        return null;
    }

    List<WebElement> findElements(String identifier, String value){
        if(identifier.equalsIgnoreCase("id"))
            return webDriver.findElements(By.id(value));
        if(identifier.equalsIgnoreCase("classname"))
            return webDriver.findElements(By.className(value));
        if(identifier.equalsIgnoreCase("xpath"))
            return webDriver.findElements(By.xpath(value));
        return null;
    }

    public void quitWindow(){
        webDriver.quit();
    }

}
