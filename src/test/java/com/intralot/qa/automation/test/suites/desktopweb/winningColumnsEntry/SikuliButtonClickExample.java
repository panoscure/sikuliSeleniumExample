package com.intralot.qa.automation.test.suites.desktopweb.winningColumnsEntry;

import com.intralot.qa.automation.base.test.BaseSetupForApplication;
import com.intralot.qa.automation.core.driver.engine.DriverFactory;
import com.intralot.qa.automation.core.jira.Store_ticket_status_list;
import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.core.utilities.Log;
import com.intralot.qa.automation.page.objects.web.desktop.lotteryUI.LotteryUIAuthorizationRequest;
import com.intralot.qa.automation.page.objects.web.desktop.lotteryUI.LotteryUIDrawClose;
import com.intralot.qa.automation.page.objects.web.desktop.lotteryUI.LotteryUIEnterWinningNumbers;
import com.intralot.qa.automation.utilities.Generic;
import io.qameta.allure.Step;
import org.sikuli.script.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.intralot.qa.automation.utilities.sikuliX;

import java.io.File;
import java.util.Arrays;

public class SikuliButtonClickExample extends BaseSetupForApplication {

    @BeforeMethod(alwaysRun = true)
    public void initializeWebDriver() {
        webDriver = DriverFactory.initializeAndGetChromeDriverBoni(CustomProperties.getPropertyValue("taiwan.resolution"),false);
    }

    @Step("Validate_3_Digit")
    @Test(priority=1, description = "Validate_3_Digit")
    public void QAAU23_15683() throws InterruptedException, FindFailed {


        LotteryUIDrawClose lotteryUI = new LotteryUIDrawClose(webDriver);
        LotteryUIAuthorizationRequest lUI = new LotteryUIAuthorizationRequest(webDriver);
        LotteryUIEnterWinningNumbers win_numbers = new LotteryUIEnterWinningNumbers(webDriver);



        /*******************************Enter Winning Numbers from Lottery UI**********************************************/
        webDriver.get(CustomProperties.getPropertyValue("taiwan.lottery.ui.url"));
        lUI.lotteryUILogin(CustomProperties.getPropertyValue("back.office.user"),CustomProperties.getPropertyValue("back.office.password"));
        webDriver.get(CustomProperties.getPropertyValue("taiwan.lottery.ui.url"));

        // Create a SikuliX Screen object
        Screen screen = new Screen();

        // Define the path to the button image
        String buttonImagePath1 = "C:\\Users\\maurogiannopoulos\\IdeaProjects\\operations-tlc\\src\\test\\java\\com\\intralot\\qa\\automation\\test\\suites\\desktopweb\\winningColumnsEntry\\sikuliImages\\sikuli2JPG.JPG";
        String buttonImagePath2 = "C:\\Users\\maurogiannopoulos\\IdeaProjects\\operations-tlc\\src\\test\\java\\com\\intralot\\qa\\automation\\test\\suites\\desktopweb\\winningColumnsEntry\\sikuliImages\\SikuliWinningNumbers.JPG";

        // Create a SikuliX Pattern object for the button image with a percentage configuration
        Pattern buttonPattern = new Pattern(buttonImagePath1).similar(0.7f); // Adjust the similarity percentage as needed
        Pattern buttonPattern2 = new Pattern(buttonImagePath2).similar(0.7f); // Adjust the similarity percentage as needed

        try {
            // Click the button using SikuliX
            screen.click(buttonPattern);
            Generic.delay(2);
            screen.click(buttonPattern2);
            Generic.delay(2);
            System.out.println("Button clicked successfully!");

        } catch (Exception e) {
            System.out.println("Button not found or unable to click: " + e.getMessage());
        }
        sikuliX getText = new sikuliX();
        String text_from_screenshot;
        text_from_screenshot = getText.takeScreenshotReturnText("C:\\Users\\maurogiannopoulos\\IdeaProjects\\operations-tlc\\src\\test\\java\\com\\intralot\\qa\\automation\\test\\suites\\desktopweb\\winningColumnsEntry\\sikuliImages");
        Log.info(text_from_screenshot);
        /******************************Winning Numbers Entered******************************/

    }



    @AfterMethod
    public void updateJiraTc(ITestResult result) {
        webDriver.quit();
        Log.info("Jira Status: " + CustomProperties.getPropertyValue("jira.enable.status"));
        if(CustomProperties.getPropertyValue("jira.enable.status").equals("1"))
        {
            System.out.println("Will update jira");
        Store_ticket_status_list.update_tc_status_full_flow(result,
                CustomProperties.getPropertyValue("jira.cycle.id"),
                CustomProperties.getPropertyValue("jira.cycle.folder.name"));
        }
    }

}
