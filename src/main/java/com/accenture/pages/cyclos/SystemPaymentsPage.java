package com.accenture.pages.cyclos;

import com.accenture.utils.ConfigReader;
import com.accenture.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Properties;

public class SystemPaymentsPage {
    private WebDriver driver;
    private ConfigReader configReader = new ConfigReader();
    private Properties prop;
    private Duration waitTimeInSecs;
    private ElementUtil elementUtil = new ElementUtil();
    //locators
    private By debitAcctBlncRadioBtn = By.xpath("//span[contains(text(), \"Debit account - Balance\")]//preceding::div[1]");
    private By amountTxtFld = By.xpath("//input[@type=\"text\" and @class=\"inputField large rightAligned\"]");
    private By descriptionTxtFld = By.xpath("//td/textarea[@name=\"description\"]");
    private By submitButton = By.xpath("//button[@type=\"button\" and contains(text(),\"Submit\")]");
    private By confirmPaymentButton = By.xpath("//button[@type=\"button\" and contains(text(),\"Confirm\")]");
    private By successfulPaymentMsg = By.xpath("//div[contains(text(), \"The payment was successful\")]");
    private By closeButton = By.xpath("//button[@type=\"button\" and contains(text(),\"Close\")]");

    //constructor
    public SystemPaymentsPage(WebDriver driver) {
        this.driver = driver;
        prop = configReader.init_prop();
        waitTimeInSecs = Duration.ofSeconds(Long.parseLong(prop.getProperty("explicit_Wait_In_Secs")));

    }

    //page actions
    public void doThePayment(String amount) {
        elementUtil.selectRadioButton(driver, debitAcctBlncRadioBtn, waitTimeInSecs);
        elementUtil.sendText(driver, amountTxtFld, waitTimeInSecs, amount);
        elementUtil.sendText(driver, descriptionTxtFld, waitTimeInSecs, "Transferring $1 from users debit account to org account");
        elementUtil.clickButton(driver, submitButton, waitTimeInSecs);

    }

    public void confirmPayment() {
        elementUtil.clickButton(driver, confirmPaymentButton, waitTimeInSecs);

    }

    public String getSuccessfulPaymentMsg() {
        return elementUtil.getLabelTxt(driver, successfulPaymentMsg, waitTimeInSecs);
    }

    public void closePaymentMsgButton() {
        elementUtil.clickButton(driver, closeButton, waitTimeInSecs);

    }
}
