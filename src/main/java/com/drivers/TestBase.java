package com.drivers;

import com.implementation.RestServiceUtilImplimentation;
import com.implementation.WaitImp;
import com.implementation.WebDriverManagementImp;
import com.services.ExtentManagerService;
import com.services.RestServiceUtil;
import com.services.WaitforInterface;
import com.services.WebDriverManagement;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class TestBase {

    protected WebDriver webDriverInstance;
    protected RestServiceUtil restServiceUtil;
    protected WebDriverManagement webDriverManagement;
    protected WaitforInterface waitforInterface;
    protected ExtentManagerService extentManagerService;

    public TestBase() {
        super();
        this.restServiceUtil = new RestServiceUtilImplimentation();
        this.webDriverManagement = new WebDriverManagementImp(webDriverInstance);
    }


    @BeforeClass
    public void beforeTest() {
        this.webDriverInstance = webDriverManagement.setupWebDriverInstance();
        this.waitforInterface = new WaitImp(webDriverInstance);

    }

    @AfterClass
    public void tearDown() {
        webDriverManagement.quitWebDriverInstance();
    }


}
