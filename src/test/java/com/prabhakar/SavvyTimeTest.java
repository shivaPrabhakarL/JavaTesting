package com.prabhakar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SavvyTimeTest {
    WebDriver webDriver;
    List<WebElement> navElements;
    List<String[]> data;
    DriverFunctions df;
    CsvReader cr;
    public SavvyTimeTest() throws IOException {
        cr = new CsvReader();
        df = new DriverFunctions();
        data = cr.readData();
    }


    boolean goToSite(String s) {
        webDriver = df.DriverInit();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get(s);
        navElements = df.findElements("xpath","//nav[@id='main-menu']//a");
        return true;
    }

    private void cityAdder(String id, String input) {
       df.findElement("id",id).sendKeys(input);
        List<WebElement> list = df.findElements("xpath","//a[@class='list-group-item']");
        list.get(0).click();
    }


    public boolean timeDifferenceCheck() throws ParseException {
        List<WebElement> elements =  df.findElements("xpath","//input[@class='time ampm format12 form-control ui-timepicker-input']");
        Date[] date = new Date[elements.size()];
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
        for (int i = 0; i < elements.size(); i++) {
            date[i] = dateFormat.parse(elements.get(i).getAttribute("value"));
        }
        int diff = (int) Math.abs(date[1].getTime() - date[0].getTime());
        System.out.println("timeDiff");
        try {
            Assert.assertEquals(diff/60000, 630);
            return true;
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }


    public boolean Converter()  {
        navElements.get(1).click();
        cityAdder("time-search", data.get(1)[0]);
        cityAdder("time-search", data.get(2)[0]);
       // List<WebElement> list = df.findElements("xpath","//a[@class='list-group-item']");

        return true;
    }


    public boolean dateTest() throws InterruptedException {
        // WebDriverWait wait =
        df.findElement("xpath","//span[@class='input-group-addon']").click();
        df.findElement("xpath","//th[@class='datepicker-switch']").click();
        df.findElement("xpath","//div[@class='datepicker-months']//th[@class='datepicker-switch']").click();
        df.findElement("xpath","//span[text()='"+data.get(1)[1]+"']").click();
        df.findElement("xpath","//span[text()='"+data.get(1)[2]+"']").click();
        WebElement ele = df.findElement("xpath","//div[@class='datepicker-days']//td[text()='"+data.get(1)[3]+"']");
        Actions actions = new Actions(webDriver);
        actions.moveToElement(ele);
        actions.perform();
        ele.click();
        String day = df.findElement("xpath","//input[@class='form-control']").getAttribute("value");
        System.out.println("date");
        try {
            Assert.assertEquals(day, data.get(1)[2]+" "+data.get(1)[3]+", "+data.get(1)[1]);
            return true;
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }


    public boolean sliderTest() throws ParseException {
        System.out.println("slidecheck");
        List<WebElement> slide = df.findElements("xpath","//div[@class='noUi-handle noUi-handle-lower']");
        WebElement Slider = slide.get(0);

        Actions builder = new Actions(webDriver);
        builder.moveToElement(Slider).click().dragAndDropBy(Slider, 77, 0).build().perform();
        //timeDifferenceCheck()
        return true;
    }


    public boolean dateCheck() throws ParseException {
        System.out.println("datecheck");
        WebElement ele = webDriver.findElement(By.xpath("//td[text()='7pm']"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(ele);
        actions.perform();
        ele.click();
        List<WebElement> elements = df.findElements("xpath","//input[@class='time ampm format12 form-control ui-timepicker-input']");
        String ss = elements.get(0).getAttribute("value");
        try {
            Assert.assertEquals(ss, "7:00 pm");
            return timeDifferenceCheck();
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteAndExchange() {
        //webDriver.findElement(By.xpath("//div[@data-id='india-hyderabad']")).click();
        WebElement div = df.findElement("xpath","//i[@class='icon-exchange']");
        Actions actions = new Actions(webDriver);
        actions.moveToElement(div);
        actions.perform();
        div.click();
        List<WebElement> list = df.findElements("xpath","//div[@class='draggable-indicator']/parent::div");
        list.get(0).click();
        webDriver.findElement(By.xpath("//a[contains(@class,'delete')]")).click();
        List<WebElement> elements1 = df.findElements("xpath","//div[@class='col-xs-12 slider-wrap']");
        int len = elements1.size();
        try {
            Assert.assertEquals(len, 1);
            return true;
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }


    public boolean getLink() {

        df.findElement("id", "permanent-link").click();

        String s = df.findElement("id", "share-url").getAttribute("value");
        try {
            Assert.assertEquals(s, "https://savvytime.com/converter/india-hyderabad");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
        df.findElement("id", "include-time").click();
        s = df.findElement("id", "share-url").getAttribute("value");
        try {
            Assert.assertEquals(s, "https://savvytime.com/converter/india-hyderabad/12pm");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
        df.findElement("id", "include-date").click();
        s = df.findElement("id", "share-url").getAttribute("value");
        try {
            Assert.assertEquals(s, "https://savvytime.com/converter/india-hyderabad/jan-10-2020/12pm");
            return true;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }


}
