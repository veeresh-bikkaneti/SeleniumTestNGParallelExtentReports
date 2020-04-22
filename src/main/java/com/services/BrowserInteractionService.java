package com.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface BrowserInteractionService {
    void maximizeWindow();
    void minimizeWindow();
    void resizeWindow(int width, int height);

    void generateAlert(String message);

    String getTitleByJS();

    String getPageInnerText();

    void scrollPageDown();

    void scrollByinnerHeight();

    void getAttribute(WebElement element);

    void searchsegment(WebElement element, String SegmentID);

    void insertIntoField(By by, String inputText);

    void clickTheButton(By by);

    boolean isElementPresentBy(By by);

    boolean isAlertPresent(WebDriver driver);

    void selectDesiredoptionInDeliveryoptions(String AddOns, List<WebElement> elements);
}
