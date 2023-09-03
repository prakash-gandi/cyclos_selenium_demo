package com.accenture.pages.sapfiori;

import com.accenture.utils.ConfigReader;
import com.accenture.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

public class CreateSalesDocument {
    private WebDriver driver;
    private ConfigReader configReader;
    private Properties prop;
    private Duration waitTimeInSecs;
    private ElementUtil elementUtil;
    //locators
    private By orderTypeTxtFld = By.xpath("//span[contains(text(), \"Order Type\")]//following::input[1]//ancestor::table[1]//input");
    private By salesOrganisationTxtFld = By.xpath("//input[@title=\"Sales Organization\"]");
    private By distributionChannelTxtFld = By.xpath("//input[@title=\"Distribution Channel\"]");
    private By divisionTxtFld = By.xpath("//input[@title=\"Division\"]");
    private By continueButton = By.xpath("//div[contains(@title,\"Continue\")]");
    private By frameName = By.id("application-SalesDocument-create");

    //constructors
    public CreateSalesDocument(WebDriver driver){
        this.driver= driver;
        configReader = new ConfigReader();
        prop = configReader.init_prop();
        waitTimeInSecs = Duration.ofSeconds(Long.parseLong(prop.getProperty("explicit_Wait_In_Secs")));
    }

    //page actions
    public CreateStandardOrder fillSalesOrderPage(){
        elementUtil = new ElementUtil();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        elementUtil.waitForFrameLoadAndSwitchToIt(driver,frameName ,waitTimeInSecs);
        elementUtil.sendText(driver,orderTypeTxtFld,waitTimeInSecs,"OR");
        elementUtil.sendText(driver,salesOrganisationTxtFld, waitTimeInSecs,"ESB1");
        elementUtil.sendText(driver, distributionChannelTxtFld, waitTimeInSecs, "02");
        elementUtil.sendText(driver, divisionTxtFld, waitTimeInSecs, "00");
        elementUtil.clickButton(driver, continueButton, waitTimeInSecs);
        return new CreateStandardOrder(driver);

    }
}
