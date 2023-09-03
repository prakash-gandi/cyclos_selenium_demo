package com.accenture.stepdefinitions;

import com.accenture.factory.DriverFactory;
import com.accenture.pages.sapfiori.CreateSalesDocument;
import com.accenture.pages.sapfiori.CreateStandardOrder;
import com.accenture.pages.sapfiori.SapHomePage;
import com.accenture.pages.sapfiori.SapLoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SapFiori {
    SapLoginPage sapLoginPage = new SapLoginPage(DriverFactory.getDriver());
    SapHomePage sapHomePage;
    CreateSalesDocument createSalesDocument;
    CreateStandardOrder createStandardOrder;
    public static String salesOrderNo;

    @Given("user login to SAP Fiori application with valid credentials")
    public void user_login_to_sap_fiori_application_with_valid_credentials() {
        sapLoginPage.launchFioriApp();
        sapHomePage= sapLoginPage.loginToFioriApp();
    }
    @When("User creates the sales order")
    public void user_creates_the_sales_order() {
    createSalesDocument= sapHomePage.clickSalesOrderLink();
    createStandardOrder= createSalesDocument.fillSalesOrderPage();
    createStandardOrder.createSalesOrder();
    }
    @Then("sales order should be created successfully")
    public void sales_order_should_be_created_successfully() {
        salesOrderNo= createStandardOrder.getSalesOrderNumber();
        Assert.assertNotNull(salesOrderNo);

    }

 }
