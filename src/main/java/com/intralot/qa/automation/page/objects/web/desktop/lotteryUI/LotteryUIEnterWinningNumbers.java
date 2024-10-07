package com.intralot.qa.automation.page.objects.web.desktop.lotteryUI;

import com.google.common.truth.Truth;
import com.intralot.qa.automation.core.driver.engine.SeleniumWaits;
import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.core.utilities.Log;
import com.intralot.qa.automation.page.objects.web.desktop.deviceManagement.DmsUI;
import com.intralot.qa.automation.utilities.Generic;
import com.intralot.qa.automation.utilities.dateTime;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.intralot.qa.automation.utilities.Generic.delay;
import static org.awaitility.Awaitility.await;

public class LotteryUIEnterWinningNumbers {

    WebDriver webDriver;

    public static By winning_numbers_path = By.xpath("//span[text()='Winning Numbers']");

    public static By winning_column_path = By.xpath("//span[text()='Winning Columns']");


    public static By input_field = By.xpath("//div[@class=' css-1ab7ooq']");

    public static By input_field2 = By.xpath("//input[@id='react-select-2-input']");


    public static By numbers_path(int number,int line) {return By.xpath("(//div[@class='col-xs-1']//a[text()='"+number+"'])["+line+"]");}

    public static By second_board_numbers_path(int number,int line) {return By.xpath("(//div[@class='col-xs-1']//a[text()='"+number+"'])["+line+"]");}

    public static By save_btn_path = By.xpath("//button[text()='Save']");

    public static By available_draw_existence = By.xpath("//select[@name='draw']/option[@value!='']");

    public static By second_user_requested = By.xpath("//div[@class='rrt-title' and text()='Error']");
    public static By user_menu_path = By.xpath("(//li[@class='dropdown'])[1]");

    public static By logout_path = By.xpath("//a[@name='logout']");




    public String pin;

    public LotteryUIEnterWinningNumbers(WebDriver webDriver) {
        this.webDriver = webDriver;
    }



    dateTime dt = new dateTime();

    public LotteryUIEnterWinningNumbers selectWinningNumbersTab() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                winning_numbers_path);
        button.click();
        return this;
    }
    public LotteryUIEnterWinningNumbers selectWinningColumnsTab() {

        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                winning_column_path);
        button.click();
        return this;
    }
    public LotteryUIEnterWinningNumbers selectInsertGameInput(String game) {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                input_field);
        button.click();
        WebElement field = SeleniumWaits.visibilityOfElementLocated(webDriver,
                input_field2);
        field.sendKeys(game);
        field.sendKeys(Keys.ENTER);
        return this;
    }


    public LotteryUIEnterWinningNumbers selectNumbersXD(String game_id, int num_of_numbers, List<Integer> numbers) throws InterruptedException {
        //int[] numbers = new int[num_of_numbers];
        for(int i =0;i<num_of_numbers;i++){
            numbers.set(i, i);
        }
        selectInsertGameInput(game_id);
        Generic.delay(2);
//        await().atLeast(10000, TimeUnit.MILLISECONDS).and().atMost(130, TimeUnit.SECONDS).untilAsserted(() -> {
//            Truth.assertThat(webDriver.findElements(available_draw_existence).size()).isGreaterThan(0);
//        });
        List<WebElement> elements = webDriver.findElements(available_draw_existence);
        int size = elements.size();
        Log.info("Number of Draws for winning column: "+size);
        while (size > 0) {
            selectNumbers(numbers);
            saveWinNumbersBtn();
            Generic.delay(5);
            selectInsertGameInput(game_id);
            Generic.delay(2);
            List<WebElement> exist = webDriver.findElements(available_draw_existence);
            size = exist.size();
        }
        return this;
    }
    public LotteryUIEnterWinningNumbers selectNumbersBingo(String game_id) throws InterruptedException {
        int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        selectInsertGameInput(game_id);
        Generic.delay(2);
//        await().atLeast(10000, TimeUnit.MILLISECONDS).and().atMost(130, TimeUnit.SECONDS).untilAsserted(() -> {
//            Truth.assertThat(webDriver.findElements(available_draw_existence).size()).isGreaterThan(0);
//        });
        List<WebElement> elements = webDriver.findElements(available_draw_existence);
        int size = elements.size();
        Log.info("Number of Draws for winning column: "+size);
        while (size > 0) {
            selectNumbersOneBoard(numbers);
            if(game_id=="5134")
                selectNumbersSecondBoard(1);
            saveWinNumbersBtn();
            /*******Check if it requests for second user******/
            try{
                await().atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
                    Truth.assertThat(webDriver.findElements(second_user_requested).size()).isGreaterThan(0);
                });
            }
            catch (Exception e)
            {
                Log.info("2nd User is not needed");
            }
            List<WebElement> second_user = webDriver.findElements(second_user_requested);
            int size_second_user = second_user.size();
            Log.info("If second user exists: "+size_second_user);
            if(size_second_user>0){
                loginWithOtherUser(game_id);
            }
            /*******End of second user check******/
            Generic.delay(2);
            selectInsertGameInput(game_id);
            Generic.delay(4);
            List<WebElement> exist = webDriver.findElements(available_draw_existence);
            size = exist.size();
        }
        return this;
    }

    public LotteryUIEnterWinningNumbers selectNumbersBingoOnce(String game_id) throws InterruptedException {
        int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        selectInsertGameInput(game_id);
        Generic.delay(2);
        selectNumbersOneBoard(numbers);
        if(game_id=="5134")
            selectNumbersSecondBoard(1);
        saveWinNumbersBtn();
        Generic.delay(2);
        return this;
    }

    public LotteryUIEnterWinningNumbers selectNumbersOneBoard(int [] numbers) {
        for(int number : numbers) {
            WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                    numbers_path(number,1));
            button.click();
        }
        return this;
    }

    public LotteryUIEnterWinningNumbers selectNumbersSecondBoard(int number) throws InterruptedException {
        WebElement element = webDriver.findElement(second_board_numbers_path(number,3));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        Generic.delay(1);
            WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                    second_board_numbers_path(number,3));
            button.click();
        return this;
    }


    public LotteryUIEnterWinningNumbers selectNumbers(List<Integer> numbers) {
        for(int number : numbers) {
            WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                    numbers_path(number,number+1));
            button.click();
        }
        return this;
    }

    public LotteryUIEnterWinningNumbers saveWinNumbersBtn() throws InterruptedException {
        WebElement element = webDriver.findElement(save_btn_path);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        Generic.delay(1);
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                save_btn_path);
        button.click();
        return this;
    }

    public LotteryUIEnterWinningNumbers clickLogout() throws InterruptedException {
        Generic.delay(7);
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                user_menu_path);
        button.click();
        WebElement button2 = SeleniumWaits.visibilityOfElementLocated(webDriver,
                logout_path);
        button2.click();
        return this;
    }

    public LotteryUIEnterWinningNumbers loginWithOtherUser(String game_id) throws InterruptedException {
        clickLogout();
        LotteryUIAuthorizationRequest lUI = new LotteryUIAuthorizationRequest(webDriver);
        lUI.lotteryUILogin(CustomProperties.getPropertyValue("back.office.2nd.user"),CustomProperties.getPropertyValue("back.office.2nd.password"));
        selectWinningNumbersTab().selectWinningColumnsTab();
        selectNumbersBingoOnce(game_id);
        clickLogout();
        lUI.lotteryUILogin(CustomProperties.getPropertyValue("back.office.user"),CustomProperties.getPropertyValue("back.office.password"));
        selectWinningNumbersTab().selectWinningColumnsTab();
        return this;
    }


}
