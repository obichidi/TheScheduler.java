package Database;

import Model.Customer;
import Model.User;
import Util.ConnectorDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;




/**
 * this is the CustomerDatabase class that holds all the logic for database manipulation
 *
 *
 */
public class CustomerDatabase {

    /**This the constructor for the CustomerDatabase class*/
          public CustomerDatabase(){}

    /**
     * this is the get all customers function which gets all the customers information from the database
     * @throws ParseException throws parse exception
     * @throws SQLException throws sql exception
     * @return    returns allCustomers observable array list
     */
    public static ObservableList<Customer> getAllCustomers() throws ParseException, SQLException{
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        try {
            try (Statement statement = ConnectorDb.connectDb().createStatement()) {
                String query = "SELECT  c.Customer_ID, co.Country, f.Division, c.Customer_Name, c.Address, c.Phone, c.Postal_Code, c.Division_ID" +
                        " FROM customers as c " +
                        " INNER JOIN first_level_divisions as f " +
                        " ON c.Division_ID = f.Division_ID  " +
                        "INNER JOIN countries as co " +
                        " ON co.COUNTRY_ID = f.COUNTRY_ID  ORDER BY c.Customer_ID ASC";
                ResultSet rs = statement.executeQuery(query);
                while(rs.next()) {
                    int  customerDivisionId = rs.getInt("c.Division_ID");
                    int customerId = rs.getInt("c.Customer_ID");
                    String customerDivision = rs.getString("f.Division");
                    String customerPhone = rs.getString("c.Phone");
                    String customerCountry = rs.getString("co.Country");
                    String customerName = rs.getString("c.Customer_Name");
                    String customerAddress = rs.getString("c.Address");
                    String customerZipCode = rs.getString("c.Postal_Code");

                    Customer customer = new Customer( customerId, customerDivision, customerName,  customerCountry,  customerAddress, customerPhone,  customerZipCode,  customerDivisionId);

                    allCustomers.add(customer);
                }
            }


        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }

        return allCustomers;
    }

    public static int findCountryId(String Country){
        int selectCountryId = 0;
        try {

            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT f.Country_ID, Country FROM first_level_divisions as f " +
                    " INNER JOIN countries as c " +
                    " ON c.Country_ID = f.Country_ID;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                if (Country.equals(rs.getString("Country"))){
                    selectCountryId = rs.getInt("Country_ID");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return selectCountryId;
    }
    /**
     * this is function modifies the customer information and stores the inputted changes to the database
     * @param customerId The Id of the customer
     * @param customerName the  name of the customer
     * @param customerPhone the phone number of the customer
     * @param customerAddress the address of the customer
     * @param customerZipCode  the zip code of the customer
     * @param customerDivisionId  the customers division Id

     */

    public static void modifyCustomer(int customerId, String customerName, String customerPhone, String customerAddress, String customerZipCode,  int customerDivisionId) {
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement(
                    "UPDATE customers SET Address = '" + customerAddress + "', Customer_Name = '" + customerName + "', Division_ID =  '" + customerDivisionId + "', Phone = '" + customerPhone + "', Postal_Code = '" + customerZipCode + "',  Last_Update = CURRENT_TIMESTAMP, last_Updated_By = '" + User.currentUser.getUsername()
                            + "' WHERE Customer_ID = '" + customerId + "';");
            statement.executeUpdate();
            System.out.println("Address updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }




    }

    /**
     * this function gets a list of divisions from the database
     * @param countryId the country Id
     *
     *
     * @return    returns allCustomers observable array list
     */

    public static ObservableList<String> DivisionList(int countryId) {
        ObservableList<String> divisions = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT  Division, Division_ID FROM countries as c " +
                            " INNER JOIN first_level_divisions as f " +
                            " ON f.Country_ID = c.Country_ID " +
                            " Where f.Country_ID = ? ;");
            statement.setInt(1,countryId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if(!divisions.contains(rs.getString("Division"))){
                    divisions.add(rs.getString("Division"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return divisions;
    }


    /**
     * this is the get all customers function which gets all the customers information from the database
     *
     * @return    returns countries observable array list
     */

    public static ObservableList<String> CountryList() {
        ObservableList<String> countries = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement(" SELECT   Country, Country_ID FROM  countries");



            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if(!countries.contains(rs.getString("Country"))) {
                    countries.add(rs.getString("Country"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return countries;
    }

    /**
     * This method retrieves a list of appointments by customer name
     * @param customerName  the name of the customer
     * @return customerAppointments
     */
    public static ObservableList<String> CustomerAppointmentList(String customerName ) {
        ObservableList<String> customerAppointments = FXCollections.observableArrayList();
        ObservableList<String> customerAppointments_x = FXCollections.observableArrayList();
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zoneNow = now.atZone(zoneId);
        LocalDateTime localNow = zoneNow.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();

        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT  a.Start FROM appointments a " +
                    "INNER JOIN customers as c " +
                    "ON c.Customer_ID = a.Customer_ID " +
                    " WHERE Customer_Name = ? ;");
                     statement.setString(1,customerName);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if(!customerAppointments.contains(rs.getString("Start"))) {
                    customerAppointments.add(rs.getString("Start"));



                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }


        return customerAppointments;
    }

    /**
     * this method retrieves  from the database all the contacts by contact name and appointment start
     * @param customerName  the customers name
     * @param appointmentStart the appointment start date and time
     * @return  returns appointment contacts
     */
    public static ObservableList<String> appointmentContactList(String customerName , String appointmentStart) {
        ObservableList<String> appointmentContacts = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement(
                    "SELECT  Contact_Name FROM appointments as a " +
                    " INNER JOIN customers as cu " +
                    " ON cu.Customer_ID = a.Customer_ID " +
                    " INNER JOIN contacts as c " +
                    " ON c.Contact_ID = a.Contact_ID " +
                    " WHERE Customer_Name = ?  AND a.Start = ? ;");
            statement.setString(1,customerName);
            statement.setString(2,appointmentStart);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if(!appointmentContacts.contains(rs.getString("Contact_Name"))) {
                    appointmentContacts.add(rs.getString("Contact_Name"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return appointmentContacts;
    }






    /**
     * this method retrieves  a list of customers from the database
     *
     * @return  returns customers
     */

    public static ObservableList<String> CustomerList() {
        ObservableList<String> customers = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT DISTINCT  Customer_Name FROM customers ORDER BY Customer_Name ASC;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if(!customers.contains(rs.getString("Customer_Name"))) {
                    customers.add(rs.getString("Customer_Name"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return customers;
    }



    /**
     * this method adds  customer data to the database
     * @param customerName  the customers name
     * @param customerDivisionId  the customers Id
     * @param customerAddress  the customers address
     * @param phone  the customers phone number
     * @param postalCode  the postal code of the customer
     *
     *
     */
    public static void addCustomer(String customerAddress, String postalCode, String phone, String customerName, int customerDivisionId) {
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement(
                    "INSERT INTO customers ( Division_ID,Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By)"
                            + " VALUES ('" + customerDivisionId+ "' ,'" + customerName + "' ,'" + customerAddress + "',   '" + postalCode
                            + "', '" + phone + "', CURRENT_TIMESTAMP, '" + User.currentUser.getUsername()
                            + "', CURRENT_TIMESTAMP, '" + User.currentUser.getUsername() + "')");
            statement.executeUpdate();
            System.out.println("Address successfully added!");
        } catch (SQLException ex) {
            System.out.println(ex);

        }


    }

    /**
     * this method retrieves  athe first_level_divisions countryID
     * @param Division  the selected division
     * @return  returns selectDivisionCountryId
     */
    public static int findDivisionCountryId(String Division){
        int selectDivisionCountryId = 0;
        try {

            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT Country_ID, Division FROM first_level_divisions;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                if (Division.equals(rs.getString("Division"))){
                    selectDivisionCountryId = rs.getInt("Country_ID");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return selectDivisionCountryId;
    }






    /**
     * this method retrieves   the number appointments for a selected customer name
     * @throws ParseException throws a parse exception
     * @param customerName  the selected customer name
     * @return  returns appointmentCount
     */

    public static int getAllAppointmentCountForCustomer(String customerName) throws ParseException{
   int appointmentCount = 0;

        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "Select COUNT(Appointment_ID)  from  appointments as a "+
            " Inner JOIN customers as c "+
           " on c.Customer_ID = a.Customer_ID "+
            " Where c.Customer_Name = '" + customerName + "';";
            ResultSet rs = statement.executeQuery(query);

                if (rs.next()) {
                     appointmentCount = rs.getInt(1);
                    System.out.println("numberOfRows= " + appointmentCount);

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex);
        }
        return appointmentCount;
    }


    /**
     * this method retrieves   the number appointments for a selected customer name
     * @param customerName  the selected customer name and appointment type
     *
     * @param appointmentType  the appointment type
     * @return  returns appointmentCount
     */
    public static int getAllAppointmentCountByTypeCustomer(String customerName, String appointmentType) {
        int numberOfRows = 0;

        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "Select COUNT(Appointment_ID)  from  appointments as a "+
                    " Inner JOIN customers as c "+
                    " on c.Customer_ID = a.Customer_ID "+
                    " Where c.Customer_Name = '" + customerName + "'  AND a.Type = '" + appointmentType + "' ;";
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                numberOfRows = rs.getInt(1);
                System.out.println("numberOfRows= " + numberOfRows);

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex);
        }
        return numberOfRows;
    }




    /**
     * this method retrieves  the number appointments for a selected customer name by selected month
     * @throws ParseException throws a parse exception
     * @param selectCustomerName  the selected customer name
     * @param appointmentMonth  the selected  appointment month
     * @return  returns appointmentCount
     */

    public static int getAllAppointmentCountByMonthCustomer(String selectCustomerName, Integer appointmentMonth) throws ParseException{
        int appointmentCount = 0;
        String monthPlusOne = Integer.toString(appointmentMonth+1);
        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "Select COUNT(Appointment_ID)  from  appointments as a "+
                    " Inner JOIN customers as c "+
                    " on c.Customer_ID = a.Customer_ID "+
                    " Where  MONTH(Start) = '" + monthPlusOne + "' AND Customer_Name = '" + selectCustomerName + "' ;";
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                appointmentCount = rs.getInt(1);
                System.out.println("numberOfRows= " + appointmentCount);

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex);
        }
        return appointmentCount;
    }


    /**
     * this method retrieves   the  Division Id from the firs_level_divisions table using the division name
     * @param Division the division name
     * @return  returns selectedDivisionId
     */
    public static int findDivisionId(String Division){
        int selectDivisionId = 0;
        try {

            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT Division_ID, Division FROM first_level_divisions;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                if (Division.equals(rs.getString("Division"))){
                    selectDivisionId = rs.getInt("Division_ID");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return selectDivisionId;
    }



    /**
     * this method deletes the customer from the database using the customers Id
     * @param customerId  the selected customers Id

     */

    public static void deleteCustomer( int  customerId){

        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement(
                    "DELETE FROM appointments WHERE Customer_ID = '" + customerId + "';");
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }


      try {
        PreparedStatement statement = ConnectorDb.connectDb().prepareStatement(
                "DELETE FROM customers WHERE Customer_ID = '" + customerId + "';");
        statement.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("SQL Exception: " + ex);
    }

}



}
