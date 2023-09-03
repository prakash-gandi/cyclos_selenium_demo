package com.accenture.pages.cyclos;

import com.accenture.utils.ConfigReader;
import com.accenture.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.time.Duration;
import java.util.Properties;

public class MainPage {
    //locators
    private final By loginButtonLink = By.xpath("//div[contains(text(), \"Login\")]");
    private WebDriver driver;
    private Properties prop;
    private Duration waitTimeInSecs;
    private ConfigReader configReader;
    private ElementUtil elementUtil;

    //constructors
    public MainPage(WebDriver driver) {
        this.driver = driver;
        configReader = new ConfigReader();
        prop = configReader.init_prop();
        waitTimeInSecs = Duration.ofSeconds(Long.parseLong(prop.getProperty("explicit_Wait_In_Secs")));
    }

    //page action

    public void launchCyclosApp() {
        driver.get(prop.getProperty("CYCLOS_URL"));
    }

    public void openNewPage(){
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(prop.getProperty("CYCLOS_URL"));
    }

    public LoginPage navigateToLoginPage() {
        elementUtil = new ElementUtil();
        elementUtil.clickLink(driver, loginButtonLink, waitTimeInSecs);
        return new LoginPage(driver);
    }
}
