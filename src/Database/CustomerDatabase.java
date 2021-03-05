package Database;

import Model.Customer;
import Model.User;
import Util.ConnectorDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class CustomerDatabase {


    public static ObservableList<Customer> getAllCustomers() throws ParseException, SQLException{
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        try {
            try (Statement statement = ConnectorDb.connectDb().createStatement()) {
                String query = "SELECT  c.Customer_ID, co.Country, f.Division, c.Customer_Name, c.Address, c.Phone, c.Postal_Code, c.Division_ID" +
                        " FROM customers as c " +
                        " INNER JOIN first_level_divisions as f " +
                        " ON f.Division_ID = c.Division_ID  " +
                        "INNER JOIN countries as co " +
                        "ON f.COUNTRY_ID = co.COUNTRY_ID";
                ResultSet rs = statement.executeQuery(query);
                while(rs.next()) {
                    int customerId = rs.getInt("c.Customer_ID");
                    String customerDivision = rs.getString("f.Division");
                    String customerCountry = rs.getString("co.Country");
                     String customerName = rs.getString("c.Customer_Name");
                     String customerAddress = rs.getString("c.Address");
                     String customerPhone = rs.getString("c.Phone");
                     String customerZipCode = rs.getString("c.Postal_Code");
                     int  customerDivisionId = rs.getInt("c.Division_ID");
                    Customer customer = new Customer(customerId,customerName,customerPhone, customerAddress, customerZipCode,customerCountry, customerDivisionId, customerDivision);
                    allCustomers.add(customer);
                }
            }



        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }

        return allCustomers;
    }


    public static void modifyCustomer(int addressId, String address,  int cityId,
                                      String postalCode,  int customerId, String customerName){
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement(
                    "UPDATE Address SET Address = '" + address + "',  Postal_Code = '" + postalCode + "', , Last_Update = CURRENT_TIMESTAMP, lastUpdateBy = '" + User.currentUser.getUsername()
                            + "' WHERE addressId = '" + addressId + "';");
            statement.executeUpdate();
            System.out.println("Address updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        try {
            PreparedStatement statement2 = ConnectorDb.connectDb().prepareStatement(
                    "UPDATE customers SET Customer_Name = '" + customerName + "', Create_Date "
                            + "= CURRENT_TIMESTAMP, Created_By = '" + User.currentUser.getUsername() + "' WHERE Customer_ID "
                            + "= '" + customerId + "';");
            statement2.executeUpdate();
            System.out.println("Customer information successfully updated!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    public static ObservableList<String> CustomerList() {
        ObservableList<String> customers = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT Customer_Name FROM customers ORDER BY Customer_Name ASC;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if(!customers.contains(rs.getString("Customer_Name"))){
                    customers.add(rs.getString("Customer_Name"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return customers;
    }


    public static void addCustomer( String address, String postalCode, String phone,  String customerName ,String customerCountry){
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement(
                    "INSERT INTO customers ( Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By)"
                            + " VALUES ('" + customerName + "' ,'" + address + "',   '" + postalCode
                            + "', '" + phone + "', CURRENT_TIMESTAMP, '" + User.currentUser.getUsername()
                            + "', CURRENT_TIMESTAMP, '" + User.currentUser.getUsername() + "')");
            statement.executeUpdate();
            System.out.println("Address successfully added!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        try {
            PreparedStatement statement2 = ConnectorDb.connectDb().prepareStatement(
                    "INSERT INTO countries (Country, Create_Date, Created_By,Last_Update, Last_Updated_By )" +
                            "VALUES('" + customerCountry+ "',CURRENT_TIMESTAMP, '" + User.currentUser.getUsername() + "', CURRENT_TIMESTAMP, '" + User.currentUser.getUsername() + "' )" );


            statement2.executeUpdate();
            System.out.println("Customer succesfully added!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }




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
