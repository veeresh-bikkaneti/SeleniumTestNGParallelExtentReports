package com.cogmento.testcases;

import com.cogmento.pages.HomePO;
import com.cogmento.pages.LoginPO;
import com.drivers.TestBase;
import com.implementation.BrowserInteractionServiceImplementation;
import com.implementation.WaitImp;
import com.services.BrowserInteractionService;
import com.services.WaitforInterface;
import com.utilities.PropertiesReader;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HomePageNavBar extends TestBase {
    private PropertiesReader propertiesReader;
    private String auTestURL;
    private HomePO homePO;
    private LoginPO loginPO;

    private String userName;
    private String password;
    private WaitforInterface waitforInterface;
    private BrowserInteractionService browserInteractionService;

    public HomePageNavBar() {
        super();
        this.propertiesReader = new PropertiesReader();
        this.auTestURL = propertiesReader.getAut();
        this.homePO = new HomePO();
        this.loginPO=new LoginPO();
        this.userName = propertiesReader.getUserName();
        this.password = propertiesReader.decodePassword(propertiesReader.getPassword());
    }

    @Test(alwaysRun = true)
    void launchAutCogmeto() {
        webDriverInstance.get(auTestURL);
        assertThat(webDriverInstance.getCurrentUrl().compareToIgnoreCase(auTestURL));
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
        this.browserInteractionService.insertIntoField(loginPO.getUserIdfield(),userName);
        this.browserInteractionService.insertIntoField(loginPO.getPasswordField(),password);
        this.browserInteractionService.clickTheButton(loginPO.getLoginButton());
    }


    @Test(dependsOnMethods = "launchAutCogmeto", groups = "navbar")
    void testHomeNavBarUserNameVisibility() {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.webDriverWaitTillVisibilityOfBy(homePO.getUserName()).isDisplayed());
    }


    @Test(dependsOnMethods = "testHomeNavBarUserNameVisibility", groups = "navbar")
    void testNavBarUserNameTextVisibility() {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.waitFluentForTexttoBeBy(homePO.getUserName()).compareToIgnoreCase("Veer Neti"));
    }

    @Test(dependsOnMethods = "testNavBarUserNameTextVisibility", groups = "navbar")
    void testNavBarTrailIndicatorVisibility() {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(homePO.getTrialIndicator()).isDisplayed());
    }



}
