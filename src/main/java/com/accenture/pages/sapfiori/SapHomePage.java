package com.accenture.pages.sapfiori;

import com.accenture.utils.ConfigReader;
import com.accenture.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

public class SapHomePage {
    private WebDriver driver;
    private ConfigReader configReader;
    private Properties prop;
    private Duration waitTimeInSecs;
    private ElementUtil elementUtil ;
    //locators
    private By createSalesOrderLink = By.xpath("//div//span[contains(text(), \"Sales Orders\")]");
    //constructors
    public SapHomePage(WebDriver driver) {
        this.driver= driver;
        configReader = new ConfigReader();
        prop = configReader.init_prop();
        waitTimeInSecs = Duration.ofSeconds(Long.parseLong(prop.getProperty("explicit_Wait_In_Secs")));
    }
    //page function
    public CreateSalesDocument clickSalesOrderLink(){
        elementUtil = new ElementUtil();
        //ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        //System.out.println(tabs.size());
        //System.out.println("home page window handle"+ driver.getWindowHandle());
        //System.out.println(driver.getCurrentUrl());
        elementUtil.clickLink(driver,createSalesOrderLink, waitTimeInSecs);
        return new CreateSalesDocument(driver);
    }

}
