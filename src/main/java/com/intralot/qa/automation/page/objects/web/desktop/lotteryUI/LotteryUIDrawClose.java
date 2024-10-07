package com.intralot.qa.automation.page.objects.web.desktop.lotteryUI;

import com.intralot.qa.automation.core.driver.engine.SeleniumWaits;
import com.intralot.qa.automation.core.utilities.Log;
import com.intralot.qa.automation.utilities.Generic;
import com.intralot.qa.automation.utilities.dateTime;
import kotlin.coroutines.ContinuationInterceptor;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v118.indexeddb.model.Key;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class LotteryUIDrawClose {

    WebDriver webDriver;

    public static By monitoring_and_operations_path = By.xpath("//span[text()='Monitoring and Operations']");

    public static By draw_operations_path = By.xpath("//span[text()='Draw Operations']");
    public static By modify_draw_status_path = By.xpath("//a[@id='drawOperations-tab-mdd']");


    public static By game_path(String game,String time_or_status){return By.xpath("(//select[@name='game']//option[@value='"+game+"'])["+time_or_status+"]");}


    public static By draw_option_path = By.xpath("//select[@name='draw']//option[2]");

    public static By draw_time_path = By.xpath("//span[@class='input-group']//input[@type='text']");

    public static By button_modify_path = By.xpath("//button[text()='Modify Draw Time']");

    public static By somewhere_path = By.xpath("//a[@id='drawOperations-tab-mdt']");

    public static By payment_suspended_existence = By.xpath("//tr/td[text()='Payments Suspended']/following-sibling::td/a/span[@title='View']");

    public static By suspend_path = By.xpath("(//tr/td[text()='Payments Suspended']/following-sibling::td/a/span[@title='View'])[1]");

    public static By release_payments_path = By.xpath("//button[text()='Release Payments']");

    public static By modify_draw_status_btn_path = By.xpath("//button[text()='Modify Draw Status']");

    public static By num_of_entries_path = By.xpath("//select[@name='limit']/option[@value='100']");



    public String pin;

    public LotteryUIDrawClose(WebDriver webDriver) {
        this.webDriver = webDriver;
    }



    dateTime dt = new dateTime();



    public LotteryUIDrawClose selectMonitoringAndOperationsTab() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                monitoring_and_operations_path);
        button.click();
        return this;
    }
    public LotteryUIDrawClose selectDrawOperationsTab() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                draw_operations_path);
        button.click();
        return this;
    }
    public LotteryUIDrawClose selectModifyDrawStatusTab() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                modify_draw_status_path);
        button.click();
        return this;
    }
    public LotteryUIDrawClose selectGameFromDropDown(String game, String draw_time_or_status) {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                game_path(game,draw_time_or_status));
        button.click();
        return this;
    }

    public LotteryUIDrawClose changeToPaymentSuspended() throws InterruptedException {
        Generic.delay(2);
        List<WebElement> elements = webDriver.findElements(payment_suspended_existence);
        int size = elements.size();
        Log.info("Number of Draws for winning column: "+size);
        while (size > 0) {
            //try{
            selectLatestDrawToSuspend();
            //}
            //catch(Exception e){
                //JavascriptExecutor js = (JavascriptExecutor) webDriver;
                //js.executeScript("window.scrollBy(0,350)", "");
            //}
            Generic.delay(2);
            List<WebElement> exist = webDriver.findElements(payment_suspended_existence);
            size = exist.size();
        }
        return this;
    }
    public LotteryUIDrawClose selectLatestDrawToSuspend() throws InterruptedException {
        try {
            WebElement element = webDriver.findElement(suspend_path);
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            Generic.delay(2);
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
            Generic.delay(2);
            js.executeScript("window.scrollBy(0, -100)", "");
            Generic.delay(2);
            WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                    suspend_path);
            button.click();
            WebElement button2 = SeleniumWaits.visibilityOfElementLocated(webDriver,
                    release_payments_path);
            button2.click();
            WebElement button3 = SeleniumWaits.visibilityOfElementLocated(webDriver,
                    modify_draw_status_btn_path);
            button3.click();
            js.executeScript("scroll(0, -100)", "");
        }
        catch (Exception e){
            Log.info("No more draws with payment Suspended");
        }
        return this;
    }



    public LotteryUIDrawClose selectDraw() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                draw_option_path);
        button.click();
        return this;
    }

    public LotteryUIDrawClose newDateTime() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                draw_time_path);
        button.click();
        for(int i=0;i<20;i++){
        button.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        }
        button.sendKeys(dt.dateTime());
        return this;
    }

    public LotteryUIDrawClose modifyDrawTimeButton() throws InterruptedException {
        WebElement button2 = SeleniumWaits.visibilityOfElementLocated(webDriver,
                somewhere_path);
        button2.click();
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                button_modify_path);
        button.click();
        Generic.delay(120);
        return this;
    }

    public LotteryUIDrawClose select100Entries() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                num_of_entries_path);
        button.click();
        return this;
    }

}
