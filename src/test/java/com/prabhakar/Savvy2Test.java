package com.prabhakar;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;

public class Savvy2Test {
    DriverFunctions df = new DriverFunctions();

    SavvyTimeTest stt = new SavvyTimeTest();

    public Savvy2Test() throws IOException {
    }

    @Test
    public void test() throws ParseException {
        WebDriver webDriver = df.DriverInit();
        stt.goToSite("https://savvytime.com/");
        stt.Converter();
        stt.dateCheck();
        stt.deleteAndExchange();
        stt.getLink();
        webDriver.quit();
    }
}
