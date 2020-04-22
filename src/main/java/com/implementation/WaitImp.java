package com.implementation;

import com.paulhammant.ngwebdriver.NgWebDriver;
import com.services.WaitforInterface;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WaitImp implements WaitforInterface {
    private final long MAXTIMEOUT = 100L;
    protected WebDriver driver;
    NgWebDriver ngWebDriver;
    JavascriptExecutor javascriptExecutor;
    FluentWait fluentWait;

    public WaitImp(WebDriver driver) {
        this.driver = driver;
        if (this.driver == null) {
            this.driver = driver;
        }
        this.javascriptExecutor = (JavascriptExecutor) driver;
    }


    @Override
    public void waitForAngularRequestsToFinish() {
        this.ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
        this.ngWebDriver.waitForAngularRequestsToFinish();
       /* String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
        angularLoads(angularReadyScript);*/
    }

    private void angularLoads(String angularReadyScript) {
        try {
            ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript(angularReadyScript).toString());

            boolean angularReady = Boolean.valueOf(javascriptExecutor.executeScript(angularReadyScript).toString());

            if (!angularReady) {
                new WebDriverWait(driver, MAXTIMEOUT).until(angularLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    @Override
    public void waitForJQueryLoad() {
        try {
            ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) this.driver)
                    .executeScript("return jQuery.active") == 0);

            boolean jqueryReady = (Boolean) javascriptExecutor.executeScript("return jQuery.active==0");

            if (!jqueryReady) {
                new WebDriverWait(driver, MAXTIMEOUT).until(jQueryLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    @Override
    public void ajaxComplete() {
        javascriptExecutor.executeScript("var callback = arguments[arguments.length - 1];"
                + "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
                + "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
                + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
    }

    @Override
    public Boolean waitForJS() {
        return new WebDriverWait(driver, MAXTIMEOUT).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    @Override
    public void clickUsingJSfromDOM(WebElement element) {
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    @Override
    public WebElement webDriverWaitTillVisibilityOfWebElement(WebElement webElement) {
        ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOf(webElement);
        return new WebDriverWait(driver, MAXTIMEOUT * 60).until(condition);
    }
    @Override
    public WebElement webDriverWaitTillVisibilityOfBy(By by) {
        ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOf(driver.findElement(by));
        return new WebDriverWait(driver, MAXTIMEOUT * 60).until(condition);
    }
    @Override
    public WebElement webDriverWaitTillVisibilityOfElementBy(WebDriver driver, By by) {
        WebElement element = driver.findElement(by);
        return new WebDriverWait(driver, MAXTIMEOUT * 60).until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public WebElement refreshBrowserByJS(WebElement webElement) {
        ExpectedCondition<Object> condition = ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
        return (WebElement) new WebDriverWait(driver, MAXTIMEOUT * 60).until(condition);
    }

    @Override
    public void waitForPresenceOfAllElementsLocatedBy(By elementLocator, String elementName) {
        ExpectedCondition<List<WebElement>> condition = ExpectedConditions.presenceOfAllElementsLocatedBy(elementLocator);
        new WebDriverWait(driver, MAXTIMEOUT).until(condition);
    }

    @Override
    public WebElement waitFluentlyForElementToBeVisibleBy(By locator) {
        waitForAngularRequestsToFinish();
        Instant start = Instant.now();
        this.fluentWait = new FluentWait(driver)
                .pollingEvery(1, TimeUnit.SECONDS)
                .withTimeout(MAXTIMEOUT, TimeUnit.MINUTES)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        WebElement element = (WebElement) this.fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).getSeconds();
        System.out.println("[Time in Seconds] waitForElementVisible: " + timeElapsed);
        return element;
    }


    @Override
    public Boolean waitFluentlyTillInvisibilityOfElementLocatedBy(By locator) {
        waitForAngularRequestsToFinish();
        Instant start = Instant.now();
        this.fluentWait = new FluentWait<>(driver)
                .pollingEvery(1, TimeUnit.MILLISECONDS)
                .withTimeout(MAXTIMEOUT, TimeUnit.MINUTES)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        Boolean until = (Boolean) this.fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).getSeconds();
        System.out.println("[Total time in Seconds]wait For Element Not Visible: " + timeElapsed);
        return until;
    }


    @Override
    public WebElement waitFluentlyTillVisibilityOfWebElement(WebElement e) {
        waitForAngularRequestsToFinish();
        Instant start = Instant.now();
        this.fluentWait = new FluentWait<>(driver)
                .pollingEvery(1, TimeUnit.SECONDS)
                .withTimeout(MAXTIMEOUT, TimeUnit.MINUTES)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        WebElement element = (WebElement) this.fluentWait.until(ExpectedConditions.visibilityOf(e));
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).getSeconds();
        System.out.println("[Time in Seconds] waitForElementVisible: " + timeElapsed);
        return element;
    }

    @Override
    public String waitFluentForTexttoBeBy(By by) {
        this.fluentWait = new FluentWait(driver)
                .withTimeout(MAXTIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(MAXTIMEOUT, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement until = (WebElement) this.fluentWait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(by));
        return until.getText();
    }


    @Override
    public Boolean waitTillWebElementToBeSelected(WebElement e) {
        waitForAngularRequestsToFinish();
        Instant start = Instant.now();
        this.fluentWait = new FluentWait<>(driver)
                .pollingEvery(1, TimeUnit.SECONDS)
                .withTimeout(MAXTIMEOUT, TimeUnit.MINUTES)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        Boolean bool = (Boolean) this.fluentWait.until(ExpectedConditions.elementToBeSelected(e));
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).getSeconds();
        System.out.println("[Time in Seconds] waitForElementSelected: " + timeElapsed);
        return bool;
    }


    @Override
    public WebElement waitTillWebElementToBeClickable(By locator) {
//        waitForAngularRequestsToFinish();
        Instant start = Instant.now();
        this.fluentWait = new FluentWait<>(driver)
                .pollingEvery(MAXTIMEOUT, TimeUnit.SECONDS)
                .withTimeout(MAXTIMEOUT, TimeUnit.MINUTES)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        WebElement webElement = (WebElement) this.fluentWait.until(ExpectedConditions.elementToBeClickable(locator));
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).getSeconds();
        System.out.println("[Time in Seconds] waitForElementClickable: " + timeElapsed);
        return webElement;
    }


    @Override
    public WebElement waitTillWebElementToBeClickable(WebElement e) {
        waitForAngularRequestsToFinish();
        Instant start = Instant.now();
        this.fluentWait = new FluentWait<>(driver)
                .pollingEvery(MAXTIMEOUT, TimeUnit.SECONDS)
                .withTimeout(MAXTIMEOUT, TimeUnit.MINUTES)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        Instant finish = Instant.now();
        WebElement element = (WebElement) this.fluentWait.until(ExpectedConditions.elementToBeClickable(e));
        long timeElapsed = Duration.between(start, finish).getSeconds();
        System.out.println("[Time in Seconds] waitForElementClickable: " + timeElapsed);
        return element;
    }

    @Override
    public boolean waitTillTitleContains(String title) {
        waitForAngularRequestsToFinish();
        Instant start = Instant.now();
        this.fluentWait = new FluentWait<>(driver)
                .pollingEvery(MAXTIMEOUT, TimeUnit.SECONDS)
                .withTimeout(MAXTIMEOUT, TimeUnit.MINUTES)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        Boolean flag = (Boolean) this.fluentWait.until(ExpectedConditions.titleContains(title));
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).getSeconds();
        System.out.println("[Time in Seconds] waitForPageTitle: " + timeElapsed);
        return flag;
    }

    @Override
    public WebElement fluentWaitPlain(WebElement element) {
        waitForAngularRequestsToFinish();
        Instant start = Instant.now();
        System.out.println("waitandpoll");
        this.fluentWait = new FluentWait<WebDriver>(driver)
                .pollingEvery(MAXTIMEOUT, TimeUnit.SECONDS)
                .withTimeout(MAXTIMEOUT, TimeUnit.MINUTES)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        WebElement foo = null;
        try {
            foo = (WebElement) this.fluentWait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return element;
                }
            });
        } catch (Exception e) {

        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).getSeconds();
        System.out.println("[Time in Seconds] waitForPageTitle: " + timeElapsed);
        return foo;
    }


    @Override
    public void sleep(long length) {
        try {
            Thread.sleep(length * 1000);

            /*Thread.sleep(4 * 60 * 1000);
            System.out.println("Sleeping for 4 minutes using TimeUnit sleep()");

            TimeUnit.SECONDS.sleep(4);
            TimeUnit.MINUTES.sleep(4);
            TimeUnit.HOURS.sleep(1);
            TimeUnit.DAYS.sleep(1);*/

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    public void waitUntilCondition(ExpectedCondition condition, String timeoutMessage, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(timeoutMessage);
        wait.until(condition);
    }

}
