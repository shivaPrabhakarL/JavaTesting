package com.prabhakar;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;

public class Savvy1Test {
    DriverFunctions df = new DriverFunctions();

    SavvyTimeTest stt = new SavvyTimeTest();

    public Savvy1Test() throws IOException {
    }


    @Test
    public void test() throws ParseException, InterruptedException {
        WebDriver webDriver = df.DriverInit();
        stt.goToSite("https://savvytime.com/");
        stt.Converter();
        stt.dateTest();
        stt.sliderTest();
        webDriver.quit();
    }
}
