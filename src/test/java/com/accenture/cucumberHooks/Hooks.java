package com.accenture.cucumberHooks;

import com.accenture.factory.DriverFactory;
import com.accenture.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.Properties;

public class Hooks {
    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    private Properties prop;

    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @Before(order = 1)
    public void launchBrowser() throws MalformedURLException {
        String browserName = prop.getProperty("browser").trim();
        String remoteExecFlag = prop.getProperty("remote_execution").trim();
        driverFactory = new DriverFactory();
            driver = driverFactory.init_driver(browserName, remoteExecFlag);
    }

    @After(order = 0)
    public void quitBrowser() {
        driver.quit();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // take screenshot:
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
    }
}
