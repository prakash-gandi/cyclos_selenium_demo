package com.accenture.pages.cyclos;

import com.accenture.utils.ConfigReader;
import com.accenture.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Properties;

public class HomePage {
    private WebDriver driver;
    private ConfigReader configReader;
    private Properties prop;
    private ElementUtil elementUtil = new ElementUtil();
    private Duration waitTimeInSecs;
    //locators
    private By bankingMenuItemLink = By.xpath("//a[@href=\"#banking.accounts.system-accounts-summary\"]//span[contains(text(), \"Banking\")]");
    private By acctSummaryLink = By.xpath("//a[@href=\"#banking.accounts.system-accounts-summary\"]//span[contains(text(), \"Accounts summary\")]");
    private By paymentBetweenSysAccntsLink = By.xpath("//span[contains(text(), \"Between system accounts\")]");
    private By userAccountBalance = By.xpath("//div[contains(text(), \"Balance\")]/preceding-sibling::div[1]");
    private By payUserLink= By.xpath("//a[contains(@href, \"payment-user-to-user\")]");
    private By homeMenuLink =By.xpath("//a[@href=\"#home\"]");
    // constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        configReader = new ConfigReader();
        prop = configReader.init_prop();
        waitTimeInSecs = Duration.ofSeconds(Long.parseLong(prop.getProperty("explicit_Wait_In_Secs")));
    }

    // page actions
    public void clickBankingMenuItem() {

        elementUtil.clickLink(driver, bankingMenuItemLink, waitTimeInSecs);
    }

    public AccountsSummaryPage clickAccountSummaryLink() {
        elementUtil.clickLink(driver, acctSummaryLink, waitTimeInSecs);
        return new AccountsSummaryPage(driver);
    }

    public SystemPaymentsPage clickPaymentBetweenSysAccntLink() {
        elementUtil.clickLink(driver, paymentBetweenSysAccntsLink, waitTimeInSecs);
        return new SystemPaymentsPage(driver);
    }

    public String getUserAccountBalance(){
        return elementUtil.getLabelTxt(driver, userAccountBalance, waitTimeInSecs);

    }

    public BankingPage clickPayUserLink(){
        elementUtil.clickLink(driver,payUserLink,waitTimeInSecs);
        return new BankingPage(driver);
    }

    public void clickHomePageMenuItem(){
        elementUtil.clickLink(driver, homeMenuLink, waitTimeInSecs);
    }
}
