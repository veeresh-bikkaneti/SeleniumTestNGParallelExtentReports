package com.cogmento.testcases;

import com.cogmento.pages.LoginPO;
import com.drivers.TestBase;
import com.implementation.BrowserInteractionServiceImplementation;
import com.implementation.WaitImp;
import com.services.BrowserInteractionService;
import com.services.WaitforInterface;
import com.utilities.PropertiesReader;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginWorkflow extends TestBase {
    private PropertiesReader propertiesReader;
    private String auTestURL;
    private LoginPO loginPO;
    private String userName;
    private String password;
    private WaitforInterface waitforInterface;
    private BrowserInteractionService browserInteractionService;

    public LoginWorkflow() {
        super();
        this.propertiesReader = new PropertiesReader();
        this.auTestURL = propertiesReader.getAut();
        this.loginPO = new LoginPO();
        this.userName = propertiesReader.getUserName();
        this.password = propertiesReader.decodePassword(propertiesReader.getPassword());
    }


    @Test(alwaysRun = true)
    void launchAutCogmeto() {
        webDriverInstance.get(auTestURL);
        assertThat(webDriverInstance.getCurrentUrl().compareToIgnoreCase(auTestURL));
    }

    @Test(dependsOnMethods = "launchAutCogmeto",groups = "fields")
    void testLoginFieldVisibility() {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(loginPO.getUserIdfield()).isDisplayed());
    }


    @Test(dependsOnMethods = "testLoginFieldVisibility",groups = "fields")
    void testPasswordFieldVisibility() {
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(loginPO.getPasswordField()).isDisplayed());
    }

    @Test(dependsOnMethods = "testPasswordFieldVisibility",groups = "fields")
    void testCRMProUserButtonVisibility() {
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(loginPO.getCheckFreeCRMorCRMProUserButton()).isDisplayed());
    }

    @Test(dependsOnMethods = "testCRMProUserButtonVisibility",groups = "fields")
    void testSignUpVisibility() {
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(loginPO.getResgisterOrSignUpButton()).isDisplayed());
    }

   /* @Test(groups = "fields")
    void testLogin() {
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
        this.browserInteractionService.insertIntoField(loginPO.getUserIdfield(),userName);
        this.browserInteractionService.insertIntoField(loginPO.getPasswordField(),password);
        this.browserInteractionService.clickTheButton(loginPO.getLoginButton());
    }*/

}
