package com.individualcollection.test;

import com.drivers.TestBase;
import com.implementation.BrowserInteractionServiceImplementation;
import com.individualcollection.pagefactory.PagePo;
import com.services.BrowserInteractionService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HandlingWaits extends TestBase {
    private BrowserInteractionService browserInteractionService;
    private WebElement countdown;
  private PagePo pagePo;
    private String auTestURL = "http://seleniumsimplified.com/testpages/javascript_countdown.html";

    public HandlingWaits() {
        this.pagePo=new PagePo();
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
    }

/*    @BeforeMethod
    void report(Method method){
        extent.createTest(getClass().getName());
        extent.createTest(method.getName());
    }*/

    @Test
    void lauchjavascriptCountdownURL() {
        webDriverInstance.get(auTestURL);
        webDriverInstance.navigate().refresh();
        waitforInterface.ajaxComplete();
        assertThat(webDriverInstance.getCurrentUrl().compareToIgnoreCase(auTestURL));
    }

    @Test(dependsOnMethods = "lauchjavascriptCountdownURL")
    void validateCountDownIsVisibleWithWebDriverWait() {
        countdown = webDriverInstance.findElement(By.id("javascript_countdown_time"));
        this.countdown = waitforInterface.webDriverWaitTillVisibilityOfElementBy(webDriverInstance, pagePo.getCountdownBy());
        assertThat(countdown.isDisplayed());
    }

    @Test(dependsOnMethods = "validateCountDownIsVisibleWithWebDriverWait")
    public void waitValidatecountdownBy() {
        waitforInterface.waitForAngularRequestsToFinish();
        assertThat(waitforInterface.waitFluentForTexttoBeBy(pagePo.getCountdownBy()).endsWith("04"));
    }


}
