# TheScheduler-Final

This is the Scheduler

To begin Login your  username and password
There a three choices buttons to navigate from the Main Menu :

Manage Appointments
Manage Customers
Generate Reports
Exit

Manage Appointments opens to the Main appointments scene

IMPORTANT!!!
The Main Appointments are displayed in the local time of the user.  If the location is London it will be in London
time converted to the local time of the user. 

The prompt will be in the local time of the user.
When Modifying a selected appointment the time inputted will be converted to utc for that location.

Appointments can be located for the month and  week 
For Appointments  for the month  mouseclick on the radio button labeled monthly.

For appointments for the week, the radio button needs only be pressed.

To add an appointment Press the Add appointment Button
To modify or cancel the appointment must be clicked on and highlighted
To Modify and Appointment press the Modify Appointment Button 
To cancel an Appointment press the Cancel Appointment Button while an appointment is selected
To go back to the Main Menu press the back button
To exit the program press the exit button

The Add Appointment scene:

The Title field is for the title of the appointment.

The Contact Dropdown menu displays the name of all the contacts available for selection.
In order to select from the customer Table you must click on then it will be highlighted.
the type Dropdown menu displays the available types of appointments.
The description text area is for the description of the  appointment.
The Customer Table is populated with the available customers names, and their id numbers.
The date picker is to be used to add the date of the appointment.
The location DropDown  is for to select from the available appointment locations 
The start time selection consists of two selection boxes:

Start Time Hour box for the Start hour 
Minutes time box is for the minutes  ex: :00 :15 :30 :45

End Time Hour box for the End hour
Minutes time box is for the minutes  ex: :00 :15 :30 :45

The Add Appointment button is for when all the information has been inputted



The Modify Appointment scene:

The Modify Appointment fields will be pre-populated with your selected appointments data
The Title field is for the title of the appointment.

The Contact Dropdown menu displays the name of all the contacts available for selection.
In order to select from the customer Table you must click on it then it will be highlighted.
the type Dropdown menu displays the available types of appointments.
The description text area is for the description of the  appointment.
The Customer Table is populated with the available customers names, and their id numbers.
The date picker is to be used to add the date of the appointment.
The location DropDown  is for to select from the available appointment locations
The start time selection consists of two selection boxes:

Start Time Hour box for the Start hour
Minutes time box is for the Minutes  ex: :00 :15 :30 :45

End Time Hour box for the End Hour
Minutes time box is for the Minutes  ex: :00 :15 :30 :45

The Modify Appointment Button is for when all the information has been inputted
The cancel button will take the program back to Main Appointment scene




Manage Customers:
The customer table will be populated with all available customer data
To Modify or Delete customer data you must select the customer to where it's highlighted first 

Add Customer Scene:
Customer Name has two sections for First Name and Last Name
Customer Phone  has three sections for the digits of a Phone Number
Customer Address has 3 sections for input address data
Customer Zip  is for the Postal Code data input
Division DropDown is populated with the Division names available for selection
Customer Country is Populated with countries based on The Selected Division
Back button takes the scene back to the Main Customer Scene
The Add customer button will add the Customer if there are no errors with input



Edit Customer Scene:
The Edit customer scene is populated with the information of you selected customer
Name for the customer name 
Phone for the customer phone number
Address for the customers address
Zip code for the customers postal code
Division is populated based on Country
Country is populated with available countries
Back button will take the scene back to Main Customer scene
The Modify Appointment Button will save your settings and navigate to the Main customer scene


Generate Reports opens the Reports' scene where there are 3 buttons for navigation

Contact Schedules scene
displays appointments by ContactName appointment Type or/And appointment Month
You must select a Contact first 
There is a radio button option to refresh the table

Print appointments by type  prints  selected contacts appointment filtered by appointment type  to a text file in the Reports folder
Print appointments by month  prints  selected contacts appointment filtered by appointment month  to a text file in the Reports folder


Customer Appointment Summary button opens the Customer Appointment Statistics Scene
Name DropDown: First you  must select a customer. The number of appointments for that customer will appear in the user interface
Then you can pick the type which will show the number of appointments by type

Print All Appointments: Prints all appointments count  summary to  text file in the reports folder
Print Report By Month: Prints all appointments  by type count  summary to  text file in the reports folder
Print Report By Type: Prints all appointments by month count  summary to  text file in the reports folder*****

Find Contact Button: 
opens the find Contact Scene

Find Contact Scene:
Select the customer name, and  available appointments will populate the appointment dropdown box  once you
Choose an appointment, and the contact for this appointment will pop up.
Generate ContactReport button prints a file message text with this information to the reports file.

Back button will take you to the report menu scene

javadocs are in the root folder

Obiajulu chidi
 IntelliJ Community 2020.03
 Java SE 11.0.9 
 JavaFX-SDK-11.0.2
Generated Reports Are in the root folder next to the read me file
 A3f: is the Find Contact Scene 




















