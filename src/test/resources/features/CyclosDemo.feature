Feature: Validate the web and Rest API flow of Cyclos application. The implementation
  shows the capability to automate both web and Rest in a single test scenario

  Background: login to cyclos application
    Given user login to cyclos application with valid credentials

  @NegativeScenario @demo
  Scenario: Transfer an amount from debit account to organisation account and validate the account balance
    Given User navigates to accounts summary page
    And get the debit account balance and organisation account balance
    And user navigates to payment between system accounts page
    When Do the payment of 1$ from debit to organisation account
    Then Validate the payment is successfully done
    And validate the user account balance is decreased by 2$ and organisation account increased by 2$ in cyclos application

  @PositiveScenario @demo
  Scenario: Transfer an amount from debit account to organisation account and validate the account balance
    Given User navigates to accounts summary page
    And get the debit account balance and organisation account balance
    And user navigates to payment between system accounts page
    When Do the payment of 1$ from debit to organisation account
    Then Validate the payment is successfully done
    And validate the user account balance is decreased by 1$ and organisation account increased by 1$ using rest api    
