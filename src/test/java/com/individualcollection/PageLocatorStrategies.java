package com.individualcollection;

import com.drivers.TestBase;
import com.implementation.UtilitiesImplementation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.services.Utilities;

import static org.assertj.core.api.Assertions.assertThat;
public class PageLocatorStrategies extends TestBase {

    private By hamburgerMenu = By.cssSelector("#nav-hamburger-menu");

    private By hmenuCustomerPorofile = By.cssSelector("#hmenu-customer-profile");

    private Utilities utilities;
    private String auTestURL = "https://www.amazon.com/";

    public PageLocatorStrategies() {
        this.utilities = new UtilitiesImplementation();
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

        assertThat(waitforInterface.webDriverWaitTillVisibilityOfElementBy(hmenuCustomerPorofile).isDisplayed());
    }

    @Test (dependsOnMethods ="validateSideNavHamburgerMenuIsDisplayed")
    void validateSideNavHamburgerMenuLabel() {
        String expectedHmenuText = "Hello, Sign in";
        WebElement hmenuCustomerPorofilewebElement = waitforInterface.webDriverWaitTillVisibilityOfElementBy(hmenuCustomerPorofile);
        String signing = hmenuCustomerPorofilewebElement.getText();
        assertThat(signing).containsIgnoringCase(expectedHmenuText);
    }

}
