package com.intralot.qa.automation.page.objects.web.terminal.validate;

import com.google.common.truth.Truth;
import com.intralot.qa.automation.core.driver.engine.SeleniumWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class ValidateTab {

    WebDriver webDriver;


    public static By lottery_tab = By.xpath("//span[text()='Lottery']");
    public static By validate_btn_path = By.xpath("//a[text()='Validate']");
    public static By validate_btn_assert_path = By.xpath("//a[@class='button col-24 validate_bt activated']");
    public static By validation_popup_message_path = By.xpath("//span[@class='cf']/div/p");



    public static By keypad(Character key){return By.xpath("//div[@class='numpad p_rel']//span[text()="+ key +"]");}








    public ValidateTab(WebDriver webDriver) {
        this.webDriver = webDriver;
    }






    public ValidateTab selectLotteryTab() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                lottery_tab);
        button.click();
        return this;
    }

    public ValidateTab selectValidateBtn() {
        await().atMost(20, TimeUnit.SECONDS).untilAsserted(() -> {
            Truth.assertThat(webDriver.findElements(validate_btn_assert_path).size()).isGreaterThan(0);
        });
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                validate_btn_path);
        button.click();
        return this;
    }

    public ValidateTab insertBarcode(String barcode) {

        for (int j = 0; j < barcode.length(); j++) {
            WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                    keypad(barcode.charAt(j)));
            button.click();
        }
        return this;
    }




    public void assertIfExchangePrinted(String message_text) {
        Truth.assertThat(message_text).contains("printing exchange ticket"); //assert that balance reduced
    }

    public void assertMessageFromPopUp(String message_text) {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                validation_popup_message_path);
        String message = button.getText();
        Truth.assertThat(message).contains(message_text); //assert that balance reduced
    }


}
