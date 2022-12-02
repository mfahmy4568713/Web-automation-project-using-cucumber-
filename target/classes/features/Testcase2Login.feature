@regression
Feature:user should be able to login to the web page
  Background: user open browser
     Given user navigates to the website
    And press into login

  Scenario: user login with valid Email and valid password
    When enter a valid Email "mohamedalifahmy2021@gmail.com"
    And enter a valid password "123456"
    And press login button
    Then user could login successfully and go to home page


  Scenario Outline: user login with invalid data
    When enter an Email "<email>"
    And enter an password "<password>"
    And press login button
    Then user couldn't login successfully error message "<email>"

    Examples:
    |         email         |       password       |
    |mohamedalifahmy2021@gmail.com|123             |
    |mohamedalifail.com           |123456          |
    |                             |                |
    |mohamedalifahmy2021@gmail.com|                |
    |                             |123456          |



