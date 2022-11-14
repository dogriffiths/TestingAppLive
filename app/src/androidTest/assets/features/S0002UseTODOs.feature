Feature: Use TODOs
  As a user
  I want to record TODOs
  So that I can work the air conditioning

  Scenario: Convert from celsius
    Given I have the launched the app
    And a celsius value of -40 has been entered
    When the convert button is tapped
    Then the fahrenheit value will be -40
