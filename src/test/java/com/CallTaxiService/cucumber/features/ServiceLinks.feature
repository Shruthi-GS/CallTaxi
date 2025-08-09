@Services
Feature: Service Cab Type links functionality
Background:
Given Navigate to the url by launching the browser
When Click on Services

@TC_SERV_001
Scenario Outline: Validate the cab type links
And Verify the presence of "<cabType>" link
And Verify the "<cabType>" label
And Click on "<cabType>" cab type link
Then Verify the details displayed for "<cabType>"
Examples:
|cabType|
|Mini|
|Micro|
|Sedan|
|Suv|