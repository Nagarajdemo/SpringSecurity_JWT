Feature: verify students insert and check list

  Scenario Outline: search for different products and validate the count
    Given make a call to the students endpoint with different user credentials "<userName>" and "<password>"
    When get the count of the Students list
    Then validate each studet by <id> for user credentials "<userName>" and "<password>"
    Examples:
      | id | userName | password  |
      | 1  | megha    | megha@123 |
      | 2  | adithya  | adi@123  |
      | 3  | raj      | raj@123   |
