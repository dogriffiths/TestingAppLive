Feature: Use TODOs
  As a user
  I want to record TODOs
  So that I can be organised

  Scenario: View existing todos
    Given the following todos exist
      | Name              | Complete |
      | Buy some milk     | TRUE     |
      | Buy some shoes    | FALSE    |
      | Buy some bread    | FALSE    |
      | Buy some trousers | FALSE    |
    And I have the launched the app
    When I tap to the TODOs option
    Then I will the following todos
      | Name              | Complete |
      | Buy some milk     | TRUE     |
      | Buy some shoes    | FALSE    |
      | Buy some bread    | FALSE    |
      | Buy some trousers | FALSE    |
