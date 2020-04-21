package com.implementation;

import com.paulhammant.ngwebdriver.NgWebDriver;
import com.services.Utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;

public class UtilitiesImplementation implements Utilities {
    final long timeOutInSeconds = 20L;
    final int WAITONDATA = 10;
    NgWebDriver ngWebDriver = null;
    JavascriptExecutor javascriptExecutor = null;
    FluentWait<WebDriver> fluentWait = null;
    private WebDriver driver;

    @Override
    public void generateAlert(String message) {
        javascriptExecutor.executeScript("alert('" + message + "')");
    }

    @Override
    public String getTitleByJS() {
        String title = javascriptExecutor.executeScript("return document.title;").toString();
        return title;
    }

    @Override
    public String getPageInnerText() {
        String pageText = javascriptExecutor.executeScript("return document.documentElement.innerText;").toString();
        return pageText;
    }

    @Override
    public void scrollPageDown() {
        javascriptExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    @Override
    public void scrollByinnerHeight() {
        javascriptExecutor.executeScript("window.scrollTo(document.body.scrollHeight, window.innerHeight)");
    }


    @Override
    public void getAttribute(WebElement element) {
        System.out.println("clicked:  " + element.getAttribute("innerText"));
        element.click();
    }

    @Override
    public void searchsegment(WebElement element, String SegmentID) {
        element.clear();
        element.sendKeys(SegmentID);
        element.sendKeys(Keys.ENTER);
    }



    @Override
    public boolean isElementPresentBy(By by) {
        try {
            System.out.println("isElementPresent::");
            WebElement element = driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @Override
    public void selectDesiredoptionInDeliveryoptions(String AddOns, List<WebElement> elements) {
        for (WebElement ele : elements) {
            String labelAddOn = ele.getText();
            if (labelAddOn.equalsIgnoreCase(AddOns)) {
                if (!Boolean.parseBoolean(ele.getAttribute("aria-checked"))) {
                    ele.click();
                    System.out.println("Checked:  " + labelAddOn);
                }
            }
        }
    }

}
