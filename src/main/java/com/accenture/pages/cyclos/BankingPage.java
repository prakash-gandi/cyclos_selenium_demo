package com.accenture.pages.cyclos;

import com.accenture.utils.ConfigReader;
import com.accenture.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Properties;

public class BankingPage {
    private WebDriver driver;
    private ConfigReader configReader;
    private Properties prop;
    private Duration waitTimeInSecs;
    private ElementUtil elementUtil = new ElementUtil();
    //locators
    private By receiverUserRadioButton = By.xpath("//span[contains(text(), \"User (quick search)\")]//preceding-sibling::div[1]");
    private By enterUserNameTxtFld = By.xpath("//span[contains(text(), \"User (quick search)\")]/parent::div/parent::td/following::td[1]//input");
    private By enterAmountTxtFld = By.xpath("//span[contains(text(), \"Amount\")]/parent::div/parent::td/following-sibling::td//input[contains(@class, \"rightAligned\")]");
    private By submitButton = By.xpath("//button[contains(text(), \"Submit\")]");
    private By enterDescription = By.xpath("//textarea[@name=\"description\"]");
    private By confirmButton = By.xpath("//button[contains(text(), \"Confirm\")]");
    private By successfulPaymentMsg = By.xpath("//div[contains(text(), \"The payment was successful\")]");
    private By closeButton = By.xpath("//button[@type=\"button\" and contains(text(),\"Close\")]");

    //constructor
    public BankingPage(WebDriver driver) {
        this.driver=driver;
        configReader = new ConfigReader();
        prop = configReader.init_prop();
        waitTimeInSecs = Duration.ofSeconds(Long.parseLong(prop.getProperty("explicit_Wait_In_Secs")));

    }
    //page actions
    public void doThePaymentToUser(int amount, String userName, String salesOrder) {
        elementUtil.selectRadioButton(driver, receiverUserRadioButton, waitTimeInSecs);
        elementUtil.sendText(driver, enterUserNameTxtFld, waitTimeInSecs, userName);
        elementUtil.sendText(driver, enterAmountTxtFld, waitTimeInSecs, String.valueOf(amount));
        elementUtil.sendText(driver, enterDescription, waitTimeInSecs, salesOrder);
        elementUtil.clickButton(driver, submitButton, waitTimeInSecs);
        elementUtil.clickButton(driver, confirmButton, waitTimeInSecs);
    }

    public String getSuccessfulPaymentMsg() {
        return elementUtil.getLabelTxt(driver, successfulPaymentMsg, waitTimeInSecs);
    }

    public void closePaymentMsgButton() {
        elementUtil.clickButton(driver, closeButton, waitTimeInSecs);

    }

}
