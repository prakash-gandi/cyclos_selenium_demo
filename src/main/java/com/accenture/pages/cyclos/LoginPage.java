package com.accenture.pages.cyclos;

import com.accenture.pages.sapfiori.SapHomePage;
import com.accenture.utils.ConfigReader;
import com.accenture.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Properties;

public class LoginPage {
    private WebDriver driver;
    private ConfigReader configReader;
    private Properties prop;
    private ElementUtil elementUtil = new ElementUtil();
    private Duration waitTimeInSecs;
    //locators
    private By usernameTxtFld = By.xpath("//input[@type=\"text\" or @autocomplete=\"username\"]");
    private By passwordTxtFld = By.xpath("//input[@type=\"password\" or @autocomplete=\"password\"]");
    private By signInButton = By.xpath("//action-button//button[@type=\"button\"]");


    //constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        configReader = new ConfigReader();
        prop = configReader.init_prop();
        waitTimeInSecs = Duration.ofSeconds(Long.parseLong(prop.getProperty("explicit_Wait_In_Secs")));
    }

    //page actions
    public HomePage loginToApplication() {
        //elementUtil = new ElementUtil();
        elementUtil.sendText(driver, usernameTxtFld, waitTimeInSecs, prop.getProperty("CYCLOS_USER_NAME"));
        elementUtil.sendText(driver, passwordTxtFld, waitTimeInSecs, prop.getProperty("CYCLOS_PASSWORD"));
        elementUtil.clickButton(driver, signInButton, waitTimeInSecs);
        return new HomePage(driver);
    }

    public HomePage loginToApplication(String userName1) {
        //elementUtil = new ElementUtil();
        elementUtil.sendText(driver, usernameTxtFld, waitTimeInSecs, userName1);
        elementUtil.sendText(driver, passwordTxtFld, waitTimeInSecs, prop.getProperty("CYCLOS_PASSWORD"));
        elementUtil.clickButton(driver, signInButton, waitTimeInSecs);
        return new HomePage(driver);
    }

}
