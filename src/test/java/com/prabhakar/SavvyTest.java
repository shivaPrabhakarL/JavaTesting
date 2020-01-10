package com.prabhakar;

//import io.github.bonigarcia.wdm.DriverManagerType;
//import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;
//import java.util.concurrent.TimeUnit;
//import com.prabhakar.SavvyTimeTest;

public class SavvyTest {
    DriverFunctions df = new DriverFunctions();


    public SavvyTest() throws FileNotFoundException {
    }

    @Test
    public void test() throws ParseException, IOException {

        SavvyTimeTest stt = new SavvyTimeTest();
        WebDriver webDriver = df.DriverInit();
        stt.goToSite("https://savvytime.com/");
        stt.Converter();
        stt.timeDifferenceCheck();
        webDriver.quit();
    }
}
