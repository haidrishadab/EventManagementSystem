Feature: Validate the Booking module in the Event Management System

Background:
  Given The user opens the Booking module

Scenario: Validate missing phone number
  When The user fills all required booking fields except the phone number
  And Submits the booking form
  Then A phone number error message should be displayed

Scenario: Validate invalid guest count
  When The user fills the booking form with an invalid guest count
  And Submits the booking form
  Then A guest count error message should be displayed

Scenario: Validate default event type selection
  When The user fills the booking form without selecting an event type
  And Submits the booking form
  Then An event type error message should be displayed
