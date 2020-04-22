package com.implementation;

import com.paulhammant.ngwebdriver.NgWebDriver;
import com.services.BrowserInteractionService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class BrowserInteractionServiceImplementation implements BrowserInteractionService
        , WebDriver, JavascriptExecutor, TakesScreenshot,
        WrapsDriver, HasInputDevices, HasTouchScreen,
        Interactive, HasCapabilities {
    final long timeOutInSeconds = 20L;
    final int WAITONDATA = 10;
    WebDriver driver;
    NgWebDriver ngWebDriver = null;
    JavascriptExecutor javascriptExecutor = null;
    FluentWait<WebDriver> fluentWait = null;

    public BrowserInteractionServiceImplementation(WebDriver driver) {
        this.driver = driver;
    }

    public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        // after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    @Override
    public void generateAlert(String message) {
        javascriptExecutor.executeScript("alert('" + message + "')");
    }

    @Override
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    @Override
    public void minimizeWindow() {
        driver.manage().window().setPosition(new Point(0, -2000));
    }

    /*
    Workinprogress
     */
    @Override
    public void resizeWindow(int width, int height) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        Object innerWidth = javascriptExecutor.executeScript("return window.innerWidth;").toString();
        driver.manage().window().getSize().getWidth();
        javascriptExecutor.executeScript("return window.innerWidth;").toString();
        driver.manage().window().setSize(new Dimension(width, height));
    }

    @Override
    public Object executeScript(String script, Object... args) {
        return null;
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        return null;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }

    @Override
    public void get(String url) {

    }

    @Override
    public String getCurrentUrl() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Override
    public String getPageSource() {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void quit() {

    }

    @Override
    public Set<String> getWindowHandles() {
        return null;
    }

    @Override
    public String getWindowHandle() {
        return null;
    }

    @Override
    public TargetLocator switchTo() {
        return null;
    }

    @Override
    public Navigation navigate() {
        return null;
    }

    @Override
    public Options manage() {
        return null;
    }

    @Override
    public WebDriver getWrappedDriver() {
        return null;
    }

    @Override
    public Capabilities getCapabilities() {
        return null;
    }

    @Override
    public Keyboard getKeyboard() {
        return null;
    }

    @Override
    public Mouse getMouse() {
        return null;
    }

    @Override
    public TouchScreen getTouch() {
        return null;
    }

    @Override
    public void perform(Collection<Sequence> actions) {

    }

    @Override
    public void resetInputState() {

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

    @Override
    public void insertIntoField(By by, String inputText) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(inputText);
    }

    @Override
    public void clickTheButton(By by) {
        driver.findElement(by).click();
    }
}
