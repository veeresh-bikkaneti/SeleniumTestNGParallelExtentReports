package com.individualcollection;


import com.drivers.TestBase;
import com.implementation.RestServiceUtilImplimentation;
import com.individualcollection.entity.UrlsAndResponses;
import com.utilities.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BrokenURLsinWebPage extends TestBase {
    private PropertiesReader propertiesReader;
    private String auTestURL;

    public BrokenURLsinWebPage() {
        super();
        this.propertiesReader = new PropertiesReader();
        this.auTestURL = propertiesReader.getAut();
    }

    @Test
    void launchAmazonForAPI() {
        webDriverInstance.get(auTestURL);
    }


    @Test(dependsOnMethods = "launchAmazonForAPI")
    void brokenUrls() {
        HttpURLConnection connection = null;
        try {
            connection = restServiceUtil.urlConnectionValidator(new URL(auTestURL));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test(dependsOnMethods = "brokenUrls")
    void validateAllBrokenLinks() {
        HttpURLConnection httpURLConnection = null;
        UrlsAndResponses objectOfUrlAndResponses = new UrlsAndResponses();
        String href;
        restServiceUtil = new RestServiceUtilImplimentation();
        By anchorTag = By.tagName("a");
        List<WebElement> anchorTags = webDriverInstance.findElements(anchorTag);
        List<String> anchorTestList = new ArrayList<>();

        for (WebElement anchor : anchorTags) {
            href = anchor.getAttribute("href");
            if (Objects.nonNull(href)) {
                anchorTestList.add(href.contains("http") ? href : null);
            }
        }
        List<UrlsAndResponses> listAllResults = new ArrayList<>();
        for (String eachAnchor : anchorTestList) {
            if (Objects.nonNull(eachAnchor)) {
                try {
                    objectOfUrlAndResponses.setUrl(eachAnchor);
                    httpURLConnection = restServiceUtil.urlConnectionValidator(new URL(eachAnchor));
                    int codes = httpURLConnection.getResponseCode();
                    objectOfUrlAndResponses.setResponseCode(codes);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Integer responseCode = objectOfUrlAndResponses.getResponseCode();
                if (responseCode >= 400) {
                    objectOfUrlAndResponses.setFlg(Boolean.FALSE);
                } else {
                    objectOfUrlAndResponses.setFlg(Boolean.TRUE);
                }
                objectOfUrlAndResponses.setResponseCode(responseCode);
                listAllResults.add(objectOfUrlAndResponses);
            }

        }

        long count = listAllResults.stream().filter(obj -> obj.getFlg()).count();

    }


}

