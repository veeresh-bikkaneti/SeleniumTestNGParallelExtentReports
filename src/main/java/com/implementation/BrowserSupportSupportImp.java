package com.implementation;

import com.drivers.TestBase;
import com.services.BrowserSupportService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class BrowserSupportSupportImp extends TestBase
        implements BrowserSupportService, WebDriver, JavascriptExecutor, TakesScreenshot,
        WrapsDriver, HasInputDevices, HasTouchScreen,
        Interactive, HasCapabilities {
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriverInstance;

    public BrowserSupportSupportImp() {
        super();
    }

    @Override
    public void maximizeWindow() {
        webDriverInstance.manage().window().maximize();
    }

    @Override
    public void minimizeWindow() {
        webDriverInstance.manage().window().setPosition(new Point(0, -2000));
    }

    /*
    Workinprogress
     */
    @Override
    public void resizeWindow(int width, int height) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriverInstance;
        Object innerWidth = javascriptExecutor.executeScript("return window.innerWidth;").toString();
        webDriverInstance.manage().window().getSize().getWidth();
        javascriptExecutor.executeScript("return window.innerWidth;").toString();
        webDriverInstance.manage().window().setSize(new Dimension(width, height));
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


}
