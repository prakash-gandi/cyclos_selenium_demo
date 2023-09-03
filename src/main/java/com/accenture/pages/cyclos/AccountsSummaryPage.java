package com.accenture.pages.cyclos;

import com.accenture.utils.ConfigReader;
import com.accenture.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Properties;

public class AccountsSummaryPage {
    private WebDriver driver;
    private ConfigReader configReader = new ConfigReader();
    private Properties prop;
    private Duration waitTimeInSecs;
    private ElementUtil elementUtil = new ElementUtil();
    private String debitAccntBlnc, orgAccntBlnc;
    //locators
    private By debitAccountBalanceFld = By.xpath("//tr//td[contains(text(), \"Debit account\")]//following-sibling::td[1]");
    private By orgAccountBalanceFld = By.xpath("//tr//td[contains(text(), \"Organization account\")]//following-sibling::td[1]");

    //constructor
    public AccountsSummaryPage(WebDriver driver) {
        this.driver = driver;
        prop = configReader.init_prop();
        waitTimeInSecs = Duration.ofSeconds(Long.parseLong(prop.getProperty("explicit_Wait_In_Secs")));
    }

    public String getDebitAccountBalance() {
        debitAccntBlnc = elementUtil.getLabelTxt(driver, debitAccountBalanceFld, waitTimeInSecs);
        return debitAccntBlnc;
    }

    public String getOrgAccountBalance() {
        orgAccntBlnc = elementUtil.getLabelTxt(driver, orgAccountBalanceFld, waitTimeInSecs);
        return orgAccntBlnc;
    }

}
