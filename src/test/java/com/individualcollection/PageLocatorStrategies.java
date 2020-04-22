package com.individualcollection;

import com.drivers.TestBase;
import com.implementation.BrowserInteractionServiceImplementation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.services.BrowserInteractionService;

import static org.assertj.core.api.Assertions.assertThat;
public class PageLocatorStrategies extends TestBase {

    private By hamburgerMenu = By.cssSelector("#nav-hamburger-menu");

    private By hmenuCustomerPorofile = By.cssSelector("#hmenu-customer-profile");

    private BrowserInteractionService browserInteractionService;
    private String auTestURL = "https://www.amazon.com/";

    public PageLocatorStrategies() {
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
    }


    @Test
    void launchAmazonForUI() {
        webDriverInstance.get(auTestURL);
        assertThat(webDriverInstance.getCurrentUrl().compareToIgnoreCase(auTestURL));
    }

    @Test(dependsOnMethods ="launchAmazonForUI" )
    void validateSideNavHamburgerIconIsDisplayed() {
        assertThat(waitforInterface.waitTillWebElementToBeClickable(hamburgerMenu).isDisplayed());
    }

    @Test (dependsOnMethods ="validateSideNavHamburgerIconIsDisplayed")
    void validateSideNavHamburgerMenuIsDisplayed() {
        waitforInterface.waitTillWebElementToBeClickable(hamburgerMenu).click();
        waitforInterface.waitForJS();
        waitforInterface.waitForAngularRequestsToFinish();

        assertThat(waitforInterface.webDriverWaitTillVisibilityOfElementBy(webDriverInstance,hmenuCustomerPorofile).isDisplayed());
    }

    @Test (dependsOnMethods ="validateSideNavHamburgerMenuIsDisplayed")
    void validateSideNavHamburgerMenuLabel() {
        String expectedHmenuText = "Hello, Sign in";
        WebElement hmenuCustomerPorofilewebElement = waitforInterface.webDriverWaitTillVisibilityOfElementBy(webDriverInstance,hmenuCustomerPorofile);
        String signing = hmenuCustomerPorofilewebElement.getText();
        assertThat(signing).containsIgnoringCase(expectedHmenuText);
    }

}
