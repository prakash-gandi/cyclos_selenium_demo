package com.accenture.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementUtil {
    WebDriverWait webDriverWait;

    public void waitForElementVisibility(WebDriver driver, By element, Duration waitTime) {
        webDriverWait = new WebDriverWait(driver, waitTime);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForFrameLoadAndSwitchToIt(WebDriver driver, By element, Duration waitTime) {
        webDriverWait = new WebDriverWait(driver, waitTime);
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public void waitForElementToBeClickable(WebDriver driver, By element, Duration waitTime) {
        webDriverWait = new WebDriverWait(driver, waitTime);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickButton(WebDriver driver, By element, Duration waitTime) {
        waitForElementVisibility(driver, element, waitTime);
        waitForElementToBeClickable(driver, element, waitTime);
        driver.findElement(element).click();
    }

    public void sendText(WebDriver driver, By element, Duration waitTime, String text) {
        waitForElementVisibility(driver, element, waitTime);
        driver.findElement(element).sendKeys(text);
    }

    public void clickLink(WebDriver driver, By element, Duration waitTime) {
        waitForElementVisibility(driver, element, waitTime);
        waitForElementToBeClickable(driver, element, waitTime);
        driver.findElement(element).click();
    }

    public String getLabelTxt(WebDriver driver, By element, Duration waitTime) {
        waitForElementVisibility(driver, element, waitTime);
        return driver.findElement(element).getText();
    }

    public void selectRadioButton(WebDriver driver, By element, Duration waitTime) {
        waitForElementVisibility(driver, element, waitTime);
        waitForElementToBeClickable(driver, element, waitTime);
        driver.findElement(element).click();
    }
}
