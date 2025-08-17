Feature: Validate the Contact Us module in the Event Management System

Background:
  Given The user opens the Contact Us module

Scenario: Submit with all valid fields
  When The user enters valid details in all fields
  And Submits the Contact Us form
  Then A success message should be displayed

Scenario: Submit with invalid name
  When The user enters an invalid name and all other fields are valid
  And Submits the Contact Us form
  Then A name error message should be displayed

Scenario: Submit with invalid email format
  When The user enters an invalid email ID and all other fields are valid
  And Submits the Contact Us form
  Then An email format error message should be displayed
