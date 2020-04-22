package com.individualcollection.test;

import com.drivers.TestBase;
import com.implementation.BrowserInteractionServiceImplementation;
import com.individualcollection.pagefactory.PagePo;
import com.services.BrowserInteractionService;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PageLocatorStrategies extends TestBase {

    String expectedHmenuText = "Hello, Sign in";
    private PagePo pagePo;
    private BrowserInteractionService browserInteractionService;
    private String auTestURL = "https://www.amazon.com/";


    public PageLocatorStrategies() {
        this.pagePo = new PagePo();
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
    }

    @Test
    void launchAmazonForUI() {
        webDriverInstance.get(auTestURL);
        assertThat(webDriverInstance.getCurrentUrl().compareToIgnoreCase(auTestURL));
    }

    @Test(dependsOnMethods = "launchAmazonForUI")
    void validateSideNavHamburgerIconIsDisplayed() {
        assertThat(waitforInterface.waitTillWebElementToBeClickable(pagePo.getHamburgerMenu()).isDisplayed());
    }

    @Test(dependsOnMethods = "validateSideNavHamburgerIconIsDisplayed")
    void validateSideNavHamburgerMenuIsDisplayed() {
        waitforInterface.waitTillWebElementToBeClickable(pagePo.getHamburgerMenu()).click();
        waitforInterface.waitForJS();
        waitforInterface.waitForAngularRequestsToFinish();
        assertThat(waitforInterface.webDriverWaitTillVisibilityOfElementBy(webDriverInstance, pagePo.getHmenuCustomerPorofile()).isDisplayed());
    }

    @Test(dependsOnMethods = "validateSideNavHamburgerMenuIsDisplayed")
    void validateSideNavHamburgerMenuLabel() {
        WebElement hmenuCustomerPorofilewebElement = waitforInterface.webDriverWaitTillVisibilityOfElementBy(webDriverInstance, pagePo.getHmenuCustomerPorofile());
        String signing = hmenuCustomerPorofilewebElement.getText();
        assertThat(signing).containsIgnoringCase(expectedHmenuText);
    }

}
