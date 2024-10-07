package com.intralot.qa.automation.page.objects.web.desktop.lotteryUI;

import com.intralot.qa.automation.core.driver.engine.SeleniumWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class LotteryUIAuthorizationRequest {

    WebDriver webDriver;

    public static By user_name = By.xpath("//input[@id='username']");
    public static By password_element = By.xpath("//input[@id='password']");

    public static By wager_operations_path = By.xpath("//span[text()='Wager Operations']");

    public static By authorization_Request_path = By.xpath("//a[@href='/wagerOperations/authorizationRequests']");

    public static By serial_num_path = By.xpath("//input[@name='serialNo']");

    public static By search_path = By.xpath("//button[text()='Search']");
    public static By view_actions = By.xpath("//span[@title='View']");
    public static By approve_button = By.xpath("//button[text()='Approve']");

    public static By comments_field_path = By.xpath("//textarea[@name='comments']");

    public static By final_approve_button = By.xpath("//button[text()='Perform Action!']");

    public static By pin_path = By.xpath("(//table[@class='table table-data']/tbody/tr/td)[8]");

    public String pin;

    public LotteryUIAuthorizationRequest(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public LotteryUIAuthorizationRequest lotteryUILogin(String username, String password) {
        enterLotteryUIUsername(username);
        enterLotteryUIPassword(password);
        clickLoginButton();
        return this;
    }

    public LotteryUIAuthorizationRequest enterLotteryUIUsername(String username) {
        WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
                user_name);
        games.sendKeys(username);
        return this;
    }



    public LotteryUIAuthorizationRequest clickLoginButton() {
        WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//button[@id='btnLogin']"));
        games.click();
        return this;
    }

    public LotteryUIAuthorizationRequest enterLotteryUIPassword(String password) {
        WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
                password_element);
        games.sendKeys(password);
        return this;
    }

    public LotteryUIAuthorizationRequest selectWagerOperationsTab() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                wager_operations_path);
        button.click();
        return this;
    }

    public LotteryUIAuthorizationRequest selectAuthorizationRequestsTab() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                authorization_Request_path);
        button.click();
        return this;
    }

    public LotteryUIAuthorizationRequest selectSerialNumberField(String sn) {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                serial_num_path);
        button.click();
        sn = sn.substring(0, 32);
        button.sendKeys(sn);
        return this;
    }

    public LotteryUIAuthorizationRequest selectSearchBtn() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                search_path);
        button.click();
        return this;
    }

    public LotteryUIAuthorizationRequest selectViewActions() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                view_actions);
        button.click();
        return this;
    }

    public LotteryUIAuthorizationRequest approveRequest() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                approve_button);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", button);
        WebElement button2 = SeleniumWaits.visibilityOfElementLocated(webDriver,
                comments_field_path);
        button2.click();
        button2.sendKeys("Automated Comment");
        WebElement button3 = SeleniumWaits.visibilityOfElementLocated(webDriver,
                final_approve_button);
        button3.click();
        return this;
    }

    public LotteryUIAuthorizationRequest storePin() throws InterruptedException {
        Thread.sleep(5000);
        WebElement amount = SeleniumWaits.visibilityOfElementLocated(webDriver,
                pin_path);
        setPin(amount.getText());
        return this;
    }

    public LotteryUIAuthorizationRequest setPin(String cancel_pin){
        pin = cancel_pin;
        return null;
    }
    public String getPin(){return pin;}

    public String getTransactionID() {
        WebElement delete = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//i[@title='Delete']"));
        delete.click();
        WebElement amount = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//div[@class='modal-body']"));
        String text = amount.getText();
        text = text.substring(text.length() - 5);
        text = text.replace("?","");
        return text;
    }


    public LotteryUIAuthorizationRequest closeTransactionIDpopup() {
        //WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                //By.xpath("//span[text()='×']"));
        //JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //js.executeScript("arguments[0].click();", button);
        Actions action = new Actions(webDriver);
        action.sendKeys(Keys.ESCAPE).build().perform();
        return this;
    }






}
