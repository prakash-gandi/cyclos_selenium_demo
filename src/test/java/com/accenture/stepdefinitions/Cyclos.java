package com.accenture.stepdefinitions;

import com.accenture.factory.DriverFactory;
import com.accenture.pages.cyclos.*;
import com.accenture.rest.cyclos.AccountsApi;
import com.accenture.utils.ConfigReader;
import com.accenture.utils.GenericUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.text.ParseException;
import java.util.Properties;

public class Cyclos {
    MainPage mainPage = new MainPage(DriverFactory.getDriver());
    LoginPage loginPage;
    HomePage homePage;
    AccountsSummaryPage accountsSummaryPage;
    BankingPage bankingPage;
    GenericUtil genericUtil = new GenericUtil();
    ConfigReader configReader = new ConfigReader();
    Properties prop = new Properties();
    AccountsApi accountsApi = new AccountsApi();
    SystemPaymentsPage systemPaymentsPage;
    String debitAcctBlnc, orgAcctBlnc, newDebitAcctBlnc, newOrgAcctBlnc;
    String orgUserAcctBalance, newUserAcctBalance;

    @Given("user login to cyclos application with valid credentials")
    public void user_login_to_cyclos_application_with_valid_credentials() {
        mainPage.launchCyclosApp();
        loginPage = mainPage.navigateToLoginPage();
        homePage = loginPage.loginToApplication();
    }

    @Given("User navigates to accounts summary page")
    public void User_navigates_to_accounts_summary_page() {
        homePage.clickBankingMenuItem();
        accountsSummaryPage = homePage.clickAccountSummaryLink();

    }

    @Given("get the debit account balance and organisation account balance")
    public void get_the_debit_account_balance_and_organisation_account_balance() {
        debitAcctBlnc = accountsSummaryPage.getDebitAccountBalance();
        orgAcctBlnc = accountsSummaryPage.getOrgAccountBalance();

    }


    @Given("user navigates to payment between system accounts page")
    public void user_navigates_to_payment_between_system_accounts_page() {
        systemPaymentsPage = homePage.clickPaymentBetweenSysAccntLink();
    }

    @When("Do the payment of {int}$ from debit to organisation account")
    public void do_the_payment_of_$_from_debit_to_organisation_account(Integer amount) {
        systemPaymentsPage.doThePayment(String.valueOf(amount));
        systemPaymentsPage.confirmPayment();

    }

    @Then("Validate the payment is successfully done")
    public void validate_the_payment_is_successfully_done() {
        Assert.assertEquals("The payment was successful", systemPaymentsPage.getSuccessfulPaymentMsg());
        systemPaymentsPage.closePaymentMsgButton();

    }

    @Then("validate the user account balance is decreased by {int}$ and organisation account increased by {int}$ using rest api")
    public void validate_the_user_account_balance_is_decreased_by_$_and_organisation_account_increased_by_$_using_rest_api(Integer dbtAmt, Integer orgAmt) {
        //restUtils = new RestUtils();
        //Response resp = restUtils.getPayload();
        prop = configReader.init_prop();
        Response resp = accountsApi.getAccountDetails(prop.getProperty("CYCLOS_USER_NAME"), prop.getProperty("CYCLOS_PASSWORD"));
        Assert.assertEquals(200, resp.getStatusCode());
        //System.out.println(resp.getBody().asString());
        for (int i = 0; i < 2; i++) {
            String acctType = resp.getBody().jsonPath().getString("type[" + i + "].name");
            if (acctType.equals("Debit account")) {
                //resp.getBody().jsonPath().getString("status[i].balance");
                try {
                    Assert.assertEquals(genericUtil.extractAmount(debitAcctBlnc) - dbtAmt, genericUtil.extractAmount(resp.getBody().jsonPath().getString("status[" + i + "].balance")), 0);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            } else if (acctType.equals("Organization account")) {
                //resp.getBody().jsonPath().getString("status[i].balance");
                try {
                    Assert.assertEquals(genericUtil.extractAmount(orgAcctBlnc) + orgAmt, genericUtil.extractAmount(resp.getBody().jsonPath().getString("status[" + i + "].balance")), 0);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            } else {
                System.out.println("Not a valid Account type");
            }

        }
    }

    @Then("validate the user account balance is decreased by {int}$ and organisation account increased by {int}$ in cyclos application")
    public void validate_the_user_account_balance_is_decreased_by_$_and_organisation_account_increased_by_$_in_cyclos_application(Integer dbtAmt, Integer orgAmt) {
        //genericUtil = new GenericUtil();
        accountsSummaryPage = homePage.clickAccountSummaryLink();
        newDebitAcctBlnc = accountsSummaryPage.getDebitAccountBalance();
        newOrgAcctBlnc = accountsSummaryPage.getOrgAccountBalance();
        try {
            Assert.assertEquals(genericUtil.extractAmount(debitAcctBlnc) - dbtAmt, genericUtil.extractAmount(newDebitAcctBlnc), 0);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            Assert.assertEquals(genericUtil.extractAmount(orgAcctBlnc) + orgAmt, genericUtil.extractAmount(newOrgAcctBlnc), 0);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }


    @And("does a payment of {int}$ to user {string} for the created sales order")
    public void doesAPaymentOf$ToUserAgainstTheSalesOrder(int amount, String userName) {
        homePage.clickHomePageMenuItem();
        bankingPage = homePage.clickPayUserLink();
        bankingPage.doThePaymentToUser(amount, userName, "Sales Order number is# " + SapFiori.salesOrderNo);
    }

    @And("validate the user account balance is decreased by {int}$ using rest api")
    public void validateTheUserAccountBalanceIsDecreasedBy$UsingRestApi(int amount) {
        prop = configReader.init_prop();
        Response resp = accountsApi.getAccountDetails(prop.getProperty("CYCLOS_USER_NAME1"), prop.getProperty("CYCLOS_PASSWORD"));
        Assert.assertEquals(200, resp.getStatusCode());
        try {
            String str = resp.getBody().jsonPath().getString("status[0].balance");
            Assert.assertEquals(genericUtil.extractAmount(orgUserAcctBalance) - amount, genericUtil.extractAmount(resp.getBody().jsonPath().getString("status[0].balance")), 0);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @And("validate the user account balance is decreased by {int}$ in cyclos application")
    public void validateTheUserAccountBalanceIsDecreasedBy$InCyclosApplication(int amount) {
        homePage.clickHomePageMenuItem();
        newUserAcctBalance = homePage.getUserAccountBalance();
        try {
            Assert.assertEquals(genericUtil.extractAmount(orgUserAcctBalance) - amount, genericUtil.extractAmount(newUserAcctBalance), 0);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @When("user {string} login to the cyclos application")
    public void userLoginToTheCyclosApplication(String userName) {
        mainPage.openNewPage();
        loginPage = mainPage.navigateToLoginPage();
        homePage = loginPage.loginToApplication(userName);
        orgUserAcctBalance = homePage.getUserAccountBalance();
    }

    @Then("Validate the user payment is successfully done")
    public void validateTheUserPaymentIsSuccessfullyDone() {
        Assert.assertEquals("The payment was successful", bankingPage.getSuccessfulPaymentMsg());
        bankingPage.closePaymentMsgButton();
    }
}
