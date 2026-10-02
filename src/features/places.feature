Feature: Verifying place apis

  Scenario Outline: Verify Add place API
    Given Add place api request payload "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with post request
    Then api call is success with 200 status code
    And "status" in response is "OK"
    And "scope" in response is "APP"
    And Verify placeid in GET resonse against "<name>"

    Examples:
      | name | language | address |
      | Suresh | Kannada | Kodegehalli |
      | Narayanappa | Telugu | Krishnarajapura|