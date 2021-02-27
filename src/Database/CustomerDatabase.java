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
                String query = "SELECT  *  FROM customers";
                ResultSet rs = statement.executeQuery(query);
                while(rs.next()) {
                    int customerId = rs.getInt("Customer_ID");
                     String customerName = rs.getString("Customer_Name");
                     String customerAddress = rs.getString("Address");
                     String customerPhone = rs.getString("Phone");
                     String customerZipCode = rs.getString("Postal_Code");
                          Customer customer = new Customer(customerId,customerName,customerPhone, customerAddress, customerZipCode);
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

}
