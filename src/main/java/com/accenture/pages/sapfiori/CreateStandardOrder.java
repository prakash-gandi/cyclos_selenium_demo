package com.accenture.pages.sapfiori;

import com.accenture.utils.ConfigReader;
import com.accenture.utils.ElementUtil;
import com.accenture.utils.GenericUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Properties;

public class CreateStandardOrder {
private WebDriver driver;
    private ConfigReader configReader;
    private Properties prop;
    private Duration waitTimeInSecs;
    private ElementUtil elementUtil = new ElementUtil();
    private GenericUtil genericUtil = new GenericUtil();
    //locators

    private By soldToPartyTxtFld = By.xpath("//span[contains(text(), \"Sold-To Party\")]/preceding-sibling::input");
    private By shipToPartyTxtFld = By.xpath("//span[contains(text(), \"Ship-to party\")]/preceding-sibling::input");
    private By custReferenceTxtFld = By.xpath("//span[text()=\"Customer Reference\"]/preceding-sibling::input");
    private By pytTermsTxtFld = By.xpath("//input[contains(@title, \"Terms of payment\")]");
    private By incoTermsTxtFld = By.xpath("//input[contains(@title, \"Incoterms (Part 1)\")]");
    private By incoLocationTxtFld = By.xpath("//input[contains(@title, \"Incoterms Location\")]");
    private By saveButton = By.xpath("//div[contains(@title, \"Save\")]");
    private By infoPopUpContinueButton = By.xpath("//div[@title=\"Continue (Enter)\"]");
    private By salesOrderInfoLbl = By.xpath("//span[@role=\"presentation\"]");
    private String salesOrderInfoMsg, salesOrderNo;

    //constructor
    public CreateStandardOrder(WebDriver driver) {
        this.driver= driver;
        configReader = new ConfigReader();
        prop = configReader.init_prop();
        waitTimeInSecs = Duration.ofSeconds(Long.parseLong(prop.getProperty("explicit_Wait_In_Secs")));

    }
    //page actions
    public void createSalesOrder(){
        elementUtil.sendText(driver, soldToPartyTxtFld, waitTimeInSecs, "25");
        elementUtil.sendText(driver, shipToPartyTxtFld, waitTimeInSecs, "25");
        elementUtil.sendText(driver, custReferenceTxtFld, waitTimeInSecs, "Test001");
        elementUtil.sendText(driver, pytTermsTxtFld, waitTimeInSecs, "0001");
        elementUtil.sendText(driver, incoTermsTxtFld, waitTimeInSecs, "CIF");
        elementUtil.sendText(driver, incoLocationTxtFld, waitTimeInSecs, "Pune");
        elementUtil.clickButton(driver, saveButton, waitTimeInSecs);
        elementUtil.clickButton(driver, infoPopUpContinueButton,waitTimeInSecs);
        salesOrderInfoMsg = elementUtil.getLabelTxt(driver, salesOrderInfoLbl, waitTimeInSecs);

    }
    public String getSalesOrderNumber(){
        salesOrderNo=genericUtil.extractNumberFromString(salesOrderInfoMsg);
        return salesOrderNo;

    }
}
