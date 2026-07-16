Feature: Home Loan EMI Calculator

Scenario: Extract year wise table data

Given User opens Home Loan EMI Calculator
When User enters home loan details
And User extracts year wise table
Then Data should be stored in excel