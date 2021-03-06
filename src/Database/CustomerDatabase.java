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
                        " ON c.Division_ID = f.Division_ID  " +
                        "INNER JOIN countries as co " +
                        " ON co.COUNTRY_ID = f.COUNTRY_ID";
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

                    Customer customer = new Customer(customerId,customerDivision,customerName,customerPhone, customerAddress, customerZipCode,customerCountry, customerDivisionId);
                    allCustomers.add(customer);
                }
            }


        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }

        return allCustomers;
    }


    public static void modifyCustomer( int CustomerId, String customerName,String customerPhone,String customerAddress, String customerZipCode, String customerCountry, int customerDivisionId, String customerDivision){
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement(
                    "UPDATE customers SET Address = '" + customerAddress+ "', Customer_Name = '" + customerName + "', Division = '" + customerDivision + "', Division_ID =  '" + customerDivisionId + "', Phone = '" + customerPhone + "', Postal_Code = '" + customerZipCode + "',  Last_Update = CURRENT_TIMESTAMP, lastUpdateBy = '" + User.currentUser.getUsername()
                            + "' WHERE Customer_Id = '" + CustomerId + "';");
            statement.executeUpdate();
            System.out.println("Address updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement(
                    "UPDATE countries SET  = Division ='" + customerDivision+ "',  Country = '" + customerCountry + ", Phone = '" + customerPhone + "', Postal_Code = '" + customerZipCode + "',  Last_Update = CURRENT_TIMESTAMP, lastUpdateBy = '" + User.currentUser.getUsername()
                            + "' WHERE Division_Id = '" + customerDivisionId + "';");
            statement.executeUpdate();
            System.out.println("Address updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    public static ObservableList<String> DivisionList() {
        ObservableList<String> divisions = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT Division, Division_ID FROM first_level_divisions ORDER BY Division ASC;");
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
