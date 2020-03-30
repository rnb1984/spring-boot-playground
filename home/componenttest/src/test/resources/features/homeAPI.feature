@tag
Feature: Test Home API
  I want create a home entity and retreave the homes from db

  @tag1
  Scenario Outline: I create a home
    Given a "create home" form
      | id         | <Id>         |
      | name       | <Name>       |
      | users      | <Users>      |
      | neighbours | <Neighbours> |
    When a "POST" request is sent to the path ending "/home" for a "create home" Form
    Then recieve a "<Status>" message with code "<Status_Code>"

    Examples: 
      | Name  | Id | Neighbours | Users         | Status  | Status_Code |
      | name1 |  5 |    4,5,6,7 | user_1,user_2 | success |         201 |
