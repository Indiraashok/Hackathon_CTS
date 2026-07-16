Feature: Loan Calculator Validation

Scenario: Validate Loan Calculator UI

Given User opens Loan Calculator
When User validates EMI calculator
And User switches tenure
And User validates Loan Amount Calculator
And User validates Loan Tenure Calculator
Then All validations should pass