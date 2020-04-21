package com.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Utilities {

    void generateAlert(String message);

    String getTitleByJS();

    String getPageInnerText();

    void scrollPageDown();

    void scrollByinnerHeight();

    void getAttribute(WebElement element);

    void searchsegment(WebElement element, String SegmentID);


    boolean isElementPresentBy(By by);

    boolean isAlertPresent(WebDriver driver);

    void selectDesiredoptionInDeliveryoptions(String AddOns, List<WebElement> elements);
}
