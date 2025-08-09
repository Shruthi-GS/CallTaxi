@Booking
Feature: Book a taxi functionality
Testing the booking taxi form

Background: 
Given Navigate to url by launching the browser
When  Click on Booking

@TC_BOOK_001
Scenario: Enter valid details in booking form and validate the details displayed
And Fill all the fields with valid details from "ValidData" sheet
And Click on Book Now
Then Success message with booking details in tabular format should appear
 
@TC_BOOK_002
Scenario Outline: Validate invalid characters in Name field
And Enter invalid data in "name" field like "<Name>" 
And Fill remaining details
| Email                    | PhoneNo    | PassengerCount | Trip       | CabType | CarType | Date     | Time  | TripType  |
| Shruthi.GS@cognizant.com | 1234567890 | 1              | local trip | Mini    | Non-Ac  | 19/08/25 | 10:00 | one way   |
And Click on Book Now
Then Booking should not happen due to invalid "Name"
Examples:
|Name|
|&%*@|
|1234|
|abc!&^|
|abc123|
||

@TC_BOOK_003
Scenario Outline: Validate invalid email format
And  Enter invalid data in "email" field like "<Email>"
And Fill remaining details
| Name    | PhoneNo    | PassengerCount | Trip       | CabType | CarType | Date     | Time  | TripType  |
| shruthi | 1234567890 | 1              | local trip | Mini    | Non-Ac  | 19/08/25 | 10:00 | one way   |
And Click on Book Now
Then Booking should not happen due to invalid "Email"
 Examples:
|Email|
|abc123|
|123&*^|
|abc123@|
|abc123@gmail|
||

@TC_BOOK_004
Scenario Outline: Validate invalid phone number format
And  Enter invalid data in "phoneNo" field like "<PhoneNo>"
And Fill remaining details
| Name    | Email                    | PassengerCount | Trip       | CabType | CarType | Date     | Time  | TripType  |
| shruthi | Shruthi.GS@cognizant.com | 1              | local trip | Mini    | Non-Ac  | 19/08/25 | 10:00 | one way   |
And Click on Book Now
Then Booking should not happen due to invalid "phoneNo"
Examples:
|PhoneNo|
|&%*@|
|abc|
|123456789|
|12345678921|
||
@TC_BOOK_005
Scenario: Validate unselected select trip field (Long trip, Local trip)
And Do not select any option in select trip field
And Fill remaining details
| Name    | Email                    | PhoneNo    | PassengerCount | CabType | CarType | Date     | Time  | TripType  |
| shruthi | Shruthi.GS@cognizant.com | 1234567890 | 1              | Mini    | Non-Ac  | 19/08/25 | 10:00 | one way   |
And Click on Book Now
Then Error message should appear for unselected "trip"

@TC_BOOK_006
Scenario: Validate unselected Cab type dropdown field (Mini,Micro,Sedan,SUV)
And Leave cab type unselected
And Fill remaining details
| Name    | Email                    | PhoneNo    | PassengerCount | Trip       | CarType | Date     | Time  | TripType  |
| shruthi | Shruthi.GS@cognizant.com | 1234567890 | 1              | local trip | Non-Ac  | 19/08/25 | 10:00 | one way   |
And  Click on Book Now
Then Error message should appear for unselected "cabtype"

@TC_BOOK_007
Scenario Outline: Validate invalid date entry
And Enter invalid data in "Date" field like "<Date>"
And Fill remaining details
| Name    | Email                    | PhoneNo    | PassengerCount | CabType | Trip       | CarType | Time  | TripType  |
| shruthi | Shruthi.GS@cognizant.com | 1234567890 | 1              | Mini    | local trip | Non-Ac  | 10:00 | one way   |
And Click on Book Now
Then Booking should not happen due to invalid "Date"
Examples:
|Date|
|20/07/24|
||