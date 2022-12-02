@regression
Feature:user should be able to register to the web page
  Scenario: user login with valid Email and valid password
    Given user navigates to the website register
    And press into register
    When choose the gender
    And write the firstname "Mohamed"
    And write the lastname "Fahmy"
    And select day "11"
    And select month "8"
    And select year "1995"
    And write Email"mohamedalifahmy2021@gmail.com"
    And write the company "ITI"
    And enter the password "123456"
    And confirm password "123456"
    And press register button
    Then user could register successfully




  Scenario Outline: user register with invalid data
    Given user navigates to the website register
    And press into register
    When choose the gender
    And write the firstname "<firstname>"
    And write the lastname "<lastname>"
    And select day "<day>"
    And select month "<month>"
    And select year "<year>"
    And write Email"<email>"
    And write the company "<company>"
    And enter the password "<password>"
    And confirm password "<confirm password>"
    And press register button
    Then error message "<email>" "<firstname>" "<lastname>" "<confirm password>" "<password>"

    Examples:
    |firstname|lastname|day|month|year|          email              |company|password|confirm password|
    |         |Fahmy   |11 |8    |1995|mohamedalifahmy2021@gmail.com|ITI    |123456  |123456          |
    |Mohamed  |        |30 |8    |1995|mohamedalifahmy2021@gmail.com|ITI    |123456  |123456          |
    |Mohamed  |Fahmy   |11 |12    |1995|mohamedalifahmy2021@gmail.com|ITI   |123456  |123          |