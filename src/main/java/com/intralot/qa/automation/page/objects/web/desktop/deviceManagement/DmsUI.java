package com.intralot.qa.automation.page.objects.web.desktop.deviceManagement;

import com.intralot.qa.automation.core.driver.engine.SeleniumWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import static com.intralot.qa.automation.utilities.Generic.delay;

public class DmsUI {

    WebDriver webDriver;

    public static By user_name = By.id("username");
    public static By password_element = By.id("password");
    public static By sign_in_path = By.id("btnLogin");

    public static By device_path = By.xpath("//a[text()='Devices']");

    public static By configuration_path = By.xpath("//span[text()='Configuration']");


    public static By terminal = By.xpath("//input[@name='code']");
    public static By search_button_path = By.xpath("//button[text()='Search']");
    public static By edit_button_path = By.xpath("//i[@title='Edit']");
    public static By locked_checkbox_path = By.xpath("//div[@class='icheckbox_square-blue checked']");

    public static By user_path = By.xpath("//i[@class='fa fa-user right-navbar']");
    public static By update_btn_path = By.xpath("//button[text()='Update']");

    public static By logout_path = By.xpath("(//a[@href='/login/logout'])[2]");





    public DmsUI(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public DmsUI dmsUILogin(String username, String password) {
        enterDmsUIUsername(username);
        enterDmsUIPassword(password);
        clickLoginButton();
        return this;
    }

    public DmsUI enterDmsUIUsername(String username) {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                user_name);
        button.click();
        button.sendKeys(username);
        return this;
    }



    public DmsUI clickLoginButton() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                sign_in_path);
        button.click();
        return this;
    }

    public DmsUI enterDmsUIPassword(String password) {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                password_element);
        button.click();
        button.sendKeys(password);
        return this;
    }

    public DmsUI selectDeviceTab() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                device_path);
        button.click();
        return this;
    }

    public DmsUI selectConfigurationTab() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                configuration_path);
        button.click();
        return this;
    }

    public DmsUI selectAndTypeTerminalID(String terminal_id) {
        WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
                terminal);
        games.sendKeys(terminal_id);
        return this;
    }


    public DmsUI selectSearchButton() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                search_button_path);
        button.click();
        return this;
    }

    public DmsUI selectEditButton() throws InterruptedException {
        delay(1);
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                edit_button_path);
        button.click();
        return this;
    }

    public DmsUI unlockTerminal() throws InterruptedException {
        delay(1);
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                locked_checkbox_path);
        button.click();
        return this;
    }

    public DmsUI updateTerminal() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                update_btn_path);
        button.click();
        return this;
    }

    public DmsUI logOut() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                user_path);
        button.click();
        WebElement button2 = SeleniumWaits.visibilityOfElementLocated(webDriver,
                logout_path);
        button2.click();
        return this;
    }
}
