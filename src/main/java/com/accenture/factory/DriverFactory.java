package com.accenture.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class DriverFactory {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * this is used to get the driver with ThreadLocal
     *
     * @return
     */
    public static synchronized WebDriver getDriver() {

        return tlDriver.get();
    }

    /**
     * This method is used to initialize the threadlocal driver on the basis of given
     * browser
     *
     * @param browser
     * @return this will return tldriver.
     */
    public WebDriver init_driver(String browser, String remoteExecute) throws MalformedURLException {
        String buildDetails = "E2E_Demo_21stAug_01";
        System.out.println("Browser is: " + browser);
        System.out.println("Execute on LambdaTest remote platform? " + remoteExecute);
        if (remoteExecute.equalsIgnoreCase("No")) {
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--ignore-certificate-errors");
                tlDriver.set(new ChromeDriver(options));
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--ignore-certificate-errors");
                tlDriver.set(new FirefoxDriver(options));
            } else if (browser.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--ignore-certificate-errors");
                tlDriver.set(new EdgeDriver(options));
            } else {
                System.out.println("Please pass the correct browser value either chrome or firefox or edge, but not: " + browser);
            }
        } else if (remoteExecute.equalsIgnoreCase("Yes")) {
            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions browserOptions = new ChromeOptions();
                browserOptions.setPlatformName("Windows 10");
                browserOptions.setBrowserVersion("111.0");
                HashMap<String, Object> ltOptions = new HashMap<String, Object>();
                ltOptions.put("username", "prakash.gandi");
                ltOptions.put("accessKey", "JjjmZZWcHhYtxtISSDIHQG6qxtiBW9Mn6bNqOaCzunz4XrO8P2");
                ltOptions.put("build", buildDetails);
                ltOptions.put("project", "Selenium_demo");
                ltOptions.put("selenium_version", "4.0.0");
                ltOptions.put("w3c", true);
                browserOptions.setCapability("LT:Options", ltOptions);
                tlDriver.set(new RemoteWebDriver(new URL("https://prakash.gandi:JjjmZZWcHhYtxtISSDIHQG6qxtiBW9Mn6bNqOaCzunz4XrO8P2@hub.lambdatest.com/wd/hub"), browserOptions));
            } else if (browser.equalsIgnoreCase("edge")) {
                EdgeOptions browserOptions = new EdgeOptions();
                browserOptions.setPlatformName("Windows 10");
                browserOptions.setBrowserVersion("111.0");
                HashMap<String, Object> ltOptions = new HashMap<String, Object>();
                ltOptions.put("username", "prakash.gandi");
                ltOptions.put("accessKey", "JjjmZZWcHhYtxtISSDIHQG6qxtiBW9Mn6bNqOaCzunz4XrO8P2");
                ltOptions.put("build", buildDetails);
                ltOptions.put("project", "Selenium_demo");
                ltOptions.put("selenium_version", "4.0.0");
                ltOptions.put("w3c", true);
                browserOptions.setCapability("LT:Options", ltOptions);
                tlDriver.set(new RemoteWebDriver(new URL("https://prakash.gandi:JjjmZZWcHhYtxtISSDIHQG6qxtiBW9Mn6bNqOaCzunz4XrO8P2@hub.lambdatest.com/wd/hub"), browserOptions));

            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions browserOptions = new FirefoxOptions();
                browserOptions.setPlatformName("Windows 10");
                browserOptions.setBrowserVersion("109.0");
                HashMap<String, Object> ltOptions = new HashMap<String, Object>();
                ltOptions.put("username", "prakash.gandi");
                ltOptions.put("accessKey", "JjjmZZWcHhYtxtISSDIHQG6qxtiBW9Mn6bNqOaCzunz4XrO8P2");
                ltOptions.put("build", buildDetails);
                ltOptions.put("project", "Selenium_demo");
                ltOptions.put("selenium_version", "4.0.0");
                ltOptions.put("w3c", true);
                browserOptions.setCapability("LT:Options", ltOptions);
                tlDriver.set(new RemoteWebDriver(new URL("https://prakash.gandi:JjjmZZWcHhYtxtISSDIHQG6qxtiBW9Mn6bNqOaCzunz4XrO8P2@hub.lambdatest.com/wd/hub"), browserOptions));

            } else {
                System.out.println("Please pass the correct browser value either chrome or firefox or edge, but not: " + browser);
            }

        } else {
            System.out.println("Please set the remote execution flag as Yes or No in config file: but not" + remoteExecute);

        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        return getDriver();

    }
}
