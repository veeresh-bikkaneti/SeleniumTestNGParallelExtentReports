package com.implementation;

import com.services.DateConverterService;
import com.services.WebDriverManagement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WebDriverManagementImp implements WebDriverManagement {
    private static final String IMG_NAME = "screenshot.png";
    // A static image stored under classpath
    private static final String IMG_PATH = "src/test/resources/" + IMG_NAME;
    // Using the same OUTPUT_PATH as set in extent.properties
    private static final String OUTPUT_PATH = "test-output/HtmlReport/";
    DateConverterService dateConverter = new DateConverter();
    private WebDriver driver;

    public WebDriverManagementImp(WebDriver driver) {
        this.driver = driver;
    }

    String getImage() throws IOException {
        Files.copy(new File(IMG_PATH).toPath(), new File(OUTPUT_PATH + IMG_NAME).toPath());
        return IMG_NAME;
    }

    @Override
    public String failureScreenshots(String methodName) {
        File screenshotpath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Capture the screenshot and store it in the specified location.
        //Create a new directory under Target for failure screenshots. This folder gets cleaned or created at every execution.
        String pathname = System.getProperty("user.dir") + "/target/Screenshots/" + methodName + "_" + System.currentTimeMillis() + ".png";
        File file = new File(pathname);
        if (!file.exists()) {
            System.out.println("File created " + file);
            file.mkdir();
        }
        try {
            //copyFileToDirectory
            FileUtils.moveFile(screenshotpath,
                    new File(file + pathname));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathname;
    }

    @Override
    public WebDriver setupWebDriverInstance() {
        WebDriverManager.chromedriver().setup();
        /**
         * Creates a new ChromeDriver instance.
         */
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-web-security",
                "--start-maximized",
//                        "--headless",
                "enable-automation",
                "--disable-gpu",
                "--disable-infobars",
                "--disable-dev-shm-usage",
                "--no-sandbox",
                "--ignore-certificate-errors",
                "--disable-web-security",
                "--allow-running-insecure-content"
        );
        options.setHeadless(Boolean.FALSE);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        return driver;
    }

    @Override
    public void quitWebDriverInstance() {
        /**
         * Close the current window, quitting the browser if it's the last window currently open.
         */
        driver.close();

        /**
         * Quits this driver, closing every associated window.
         */
        driver.quit();
    }


}
