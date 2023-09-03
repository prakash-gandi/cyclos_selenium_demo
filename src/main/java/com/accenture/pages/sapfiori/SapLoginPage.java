package com.accenture.pages.sapfiori;

import com.accenture.pages.cyclos.LoginPage;
import com.accenture.utils.ConfigReader;
import com.accenture.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

public class SapLoginPage {
    private WebDriver driver;
    private ConfigReader configReader;
    private Properties prop;
    private Duration waitTimeInSecs;
    private ElementUtil elementUtil;

    //locators
    private By usernameTxtFld = By.xpath("//input[@name=\"sap-user\"]");
    private By passwordTxtFld = By.xpath("//input[@name=\"sap-password\"]");
    private By logOnButton = By.xpath("//button[@id=\"LOGIN_LINK\"]");
    //constructors
    public SapLoginPage(WebDriver driver){
        this.driver= driver;
        configReader = new ConfigReader();
        prop = configReader.init_prop();
        waitTimeInSecs = Duration.ofSeconds(Long.parseLong(prop.getProperty("explicit_Wait_In_Secs")));

    }
    //page action
    public void launchFioriApp() {

        driver.get(prop.getProperty("SAP_FIORI_URL"));

    }

    public SapHomePage loginToFioriApp(){
        elementUtil = new ElementUtil();
        //ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        //System.out.println(tabs.size());
        //System.out.println("login page window handle"+ driver.getWindowHandle());
        //System.out.println(driver.getCurrentUrl());
        elementUtil.sendText(driver, usernameTxtFld, waitTimeInSecs, prop.getProperty("SAP_USERNAME"));
        elementUtil.sendText(driver, passwordTxtFld, waitTimeInSecs, prop.getProperty("SAP_PASSWORD"));
        elementUtil.clickButton(driver, logOnButton, waitTimeInSecs);
        return new SapHomePage(driver);
    }
}
