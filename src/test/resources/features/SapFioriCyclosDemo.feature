Feature: Validate the SAP Fiori, Cyclos Web and Rest API flows. The implementation shows the
  capability to automate fiori, web and Rest in a single test scenario

  @PositiveScenario @demo
  Scenario: Create a sales order using sap-fiori and perform the payment using Cyclos application
  for the created sales order and validate the account balance through Rest API and web
    Given user login to SAP Fiori application with valid credentials
    When User creates the sales order
    Then sales order should be created successfully
    When user "demo1" login to the cyclos application
    And does a payment of 1$ to user "John" for the created sales order
    Then Validate the user payment is successfully done
    And validate the user account balance is decreased by 1$ using rest api


