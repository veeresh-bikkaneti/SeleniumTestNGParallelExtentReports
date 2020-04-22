package com.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface WaitforInterface {
    WebElement webDriverWaitTillVisibilityOfBy(By by);

    void waitForAngularRequestsToFinish();

    void ajaxComplete();

    Boolean waitForJS();

    void clickUsingJSfromDOM(WebElement element);

    void waitForJQueryLoad();

    WebElement refreshBrowserByJS(WebElement webElement);

    WebElement webDriverWaitTillVisibilityOfWebElement(WebElement webElement);

    void waitForPresenceOfAllElementsLocatedBy(By elementLocator, String elementName);

    WebElement waitFluentlyForElementToBeVisibleBy(By locator);

    Boolean waitFluentlyTillInvisibilityOfElementLocatedBy(By locator);

    WebElement webDriverWaitTillVisibilityOfElementBy(WebDriver driver, By by);

    WebElement waitFluentlyTillVisibilityOfWebElement(WebElement e);

    String waitFluentForTexttoBeBy(By by);


    Boolean waitTillWebElementToBeSelected(WebElement e);

    WebElement waitTillWebElementToBeClickable(By locator);

    WebElement waitTillWebElementToBeClickable(WebElement e);

    boolean waitTillTitleContains(String title);

    WebElement fluentWaitPlain(WebElement element);

    void sleep(long length);


}
