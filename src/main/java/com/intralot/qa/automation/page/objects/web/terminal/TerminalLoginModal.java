package com.intralot.qa.automation.page.objects.web.terminal;

import com.google.common.truth.Truth;
import com.intralot.qa.automation.core.driver.engine.SeleniumWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static com.intralot.qa.automation.utilities.Generic.delay;

public class TerminalLoginModal {

    WebDriver webDriver;

    public static By try_again = By.xpath("//a[text()='Try Again']");
    public static By training = By.xpath("//span[text()='Training']");



    public TerminalLoginModal(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String performLogin(String username,String password) {
        enterValueUsername(username);
        enterValuePassword(password);
        clickLoginButton();
        WebElement message = getMessageLoginModal();
        Assert.assertTrue(message.isDisplayed());
        clickPaperRollMessage();
        return null;
    }

    public String performMultipleLoginsWrongCredentials(String username,String password, int times) throws InterruptedException {
        delay(1);
        for(int i=0;i<times;i++) {
            enterValueUsername(username);
            enterValuePassword(password);
            clickLoginButton();
            clickTryAgainInvalidCredentials();
        }
        return null;
    }

    public String performUnsuccessfulogin(String username,String password) {
        enterValueUsername(username);
        enterValuePassword(password);
        clickLoginButton();
        WebElement title = SeleniumWaits.visibilityOfElementLocated(webDriver,
                 By.xpath("//p[@class='pblocks']"));
        //System.out.println(title.getText());
        return title.getText();
    }

    public WebElement getMessageLoginModal() {
        WebElement title = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//div[@class='creditBalance']"));
        return title;
    }

    public TerminalLoginModal enterValueUsername(String value) {
        String character;
        SeleniumWaits.elementToBeClickable(webDriver,
                By.xpath("(//span[@class='special'])[2]")).click();

        for (int index = 0; index < value.length(); index++) {
            character = String.valueOf(value.charAt(index));
            //System.out.println(character);
            SeleniumWaits.elementToBeClickable(webDriver,
                    By.xpath("//span[text()='" + character + "']")).click();
        }
        return this;
    }

    public TerminalLoginModal enterValuePassword(String value) {
        String character;
        SeleniumWaits.elementToBeClickable(webDriver,
                By.xpath("(//div[@class='password-wrap'])")).click();
        SeleniumWaits.elementToBeClickable(webDriver,
                By.xpath("(//span[@class='special'])[2]")).click();

        for (int index = 0; index < value.length(); index++) {
            character = String.valueOf(value.charAt(index));
            //System.out.println(character);
            SeleniumWaits.elementToBeClickable(webDriver,
                    By.xpath("//span[text()='" + character + "']")).click();
        }
        return this;
    }

    public TerminalLoginModal clickLoginButton() {
        WebElement loginBtn = SeleniumWaits.elementToBeClickable(webDriver,
                By.xpath("//div[@class='toolbox']"));
        loginBtn.click();
        //JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //js.executeScript("arguments[0].click();", loginBtn);
        return this;
    }

    public TerminalLoginModal clickPaperRollMessage() {
        WebElement loginBtn = SeleniumWaits.elementToBeClickable(webDriver,
                By.xpath("//a[@class='button activated']"));
        loginBtn.click();
        //JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //js.executeScript("arguments[0].click();", loginBtn);
        return this;
    }

    public TerminalLoginModal clickTryAgainInvalidCredentials() {
        WebElement loginBtn = SeleniumWaits.elementToBeClickable(webDriver,
                try_again);
        loginBtn.click();
        //JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //js.executeScript("arguments[0].click();", loginBtn);
        return this;
    }

    public TerminalLoginModal selectTrainingModeTab() {
        WebElement loginBtn = SeleniumWaits.elementToBeClickable(webDriver,
                training);
        loginBtn.click();
        return this;
    }



}
