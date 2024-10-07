package com.intralot.qa.automation.page.objects.web.desktop.retailerPulseUI;

import com.intralot.qa.automation.core.driver.engine.SeleniumWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.intralot.qa.automation.utilities.Generic.delay;

public class RetailerPulseUI {

    WebDriver webDriver;

    public static By user_name = By.xpath("//input[@id='username']");
    public static By password_element = By.xpath("//input[@id='password']");

    public static By network_path = By.xpath("//span[text()='Network']");
    public static By employees_path = By.xpath("//a[text()='Employees']");

    public static By terminal = By.xpath("//input[@name='code']");
    public static By search_button_path = By.xpath("//button[text()='Search']");
    public static By edit_button_path = By.xpath("//i[@title='Edit']");
    public static By employee_user_name_path = By.xpath("//input[@name='userName']");
    public static By unlock_path = By.xpath("//span[@title='Unlock']");
    public static By unlock_ok_path = By.xpath("(//button[text()='Ok'])[2]");


    public String pin;

    public RetailerPulseUI(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public RetailerPulseUI rmsUILogin(String username, String password) {
        enterRmsUIUsername(username);
        enterRmsUIPassword(password);
        clickLoginButton();
        return this;
    }

    public RetailerPulseUI enterRmsUIUsername(String username) {
        WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
                user_name);
        games.sendKeys(username);
        return this;
    }



    public RetailerPulseUI clickLoginButton() {
        WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
                By.xpath("//button[@id='btnLogin']"));
        games.click();
        return this;
    }

    public RetailerPulseUI enterRmsUIPassword(String password) {
        WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
                password_element);
        games.sendKeys(password);
        return this;
    }

    public RetailerPulseUI selectNetworkTab() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                network_path);
        button.click();
        return this;
    }

    public RetailerPulseUI selectEmployeesTab() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                employees_path);
        button.click();
        return this;
    }

    public RetailerPulseUI selectAndTypeEmployeeUserName(String employee_user_name) {
        WebElement games = SeleniumWaits.visibilityOfElementLocated(webDriver,
                employee_user_name_path);
        games.sendKeys(employee_user_name);
        return this;
    }


    public RetailerPulseUI selectSearchButton() {
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                search_button_path);
        button.click();
        return this;
    }

    public RetailerPulseUI selectUnlockButton() throws InterruptedException {
        delay(1);
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                unlock_path);
        button.click();
        return this;
    }

    public RetailerPulseUI unlockOkButton() throws InterruptedException {
        delay(1);
        WebElement button = SeleniumWaits.visibilityOfElementLocated(webDriver,
                unlock_ok_path);
        button.click();
        return this;
    }

}
