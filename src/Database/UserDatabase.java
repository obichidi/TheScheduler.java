package Database;

import Model.Contact;
import Model.User;
import Util.ConnectorDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import static Model.User.currentUser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**This is the class for the User database*/
public class UserDatabase {
    /**This the constructor for the UserDatabase class*/
    public UserDatabase(){}

   /** This method finds the username and password in the database and returns true or false if it matches with the inputted data
    * @param username the username
    * @param password  the password
    * @return  returns true or false if there is an error or not
    * */

    public static Boolean validateLogin(String username, String password) {
        try {
            Statement statement = ConnectorDb.connectDb().createStatement();
            String query = "SELECT * FROM users WHERE User_Name='" + username + "' AND Password='" + password +"'";
            ResultSet results = statement.executeQuery(query);
            if(results.next()) {
                currentUser = new User(results.getInt("User_Id"),
                        results.getString("User_Name"),
                        results.getString("Password"));
                statement.close();
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            return false;
        }

    }

    public static ObservableList<String> userList() {
        ObservableList<String> users = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT DISTINCT User_Name, User_ID FROM users;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
//                if(!contacts.contains(rs.getString("contacts.Contact_Name"))){
                users.add(rs.getString("User_Name"));
//                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return users;
    }



    public static int findUserId(String userName) {
        int selectUserId = 0;
        try {

            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT User_Name, User_Id From users;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (userName.equals(rs.getString("User_Name"))) {
                    selectUserId = rs.getInt("User_ID");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return selectUserId;
    }

}