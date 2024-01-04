Feature: Validating Hireprous login page

  #Scenario-1
  @RegressionTest
  Scenario: Login page field validation
    Given User navigate to Hireprous page
    Then User verifies login page fields
    And User verifies Hireprous logo

  #Scenario-2
  @RegressionTest
  Scenario: Login to application with Invalid user credentials
    Given User is on Hireprous page
    When User enters invalid username and password
    And User clicks on Log IN button
   
  #Scenario-3
  @SmokeTest
  Scenario: Login to application with valid user credentials
    Given User is on Hireprous login page
    When User enters username and password
    And User clicks on Log IN button
    Then User login must be successful
