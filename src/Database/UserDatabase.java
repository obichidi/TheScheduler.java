package Database;

import Model.User;
import Util.ConnectorDb;

import java.sql.PreparedStatement;
import static Model.User.currentUser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDatabase {
    public static void addUser(int userId, String username, String password){
        PreparedStatement statement;
        try {
            statement = ConnectorDb.connectDb().prepareStatement("INSERT INTO "
                    + "users (User_Id, User_Name, password, active, createBy, createDate, lastUpdate,"
                    + " lastUpdatedBy) VALUES ('" + userId + "', '" + username + "', '" + password
                    + "', '0', '" + currentUser.getUsername() + "', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '" + currentUser.getUsername() + "');");
            statement.executeUpdate();
            System.out.println("User successfully added!");
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
    }
    public static void modifyUser(int userId, String username, String password, String currentUser){
        PreparedStatement statement;
        try {
            statement = ConnectorDb.connectDb().prepareStatement("UPDATE users "
                    + "SET User_Name = '" + username + "', password = '"
                    + password + "', lastUpdate = CURRENT_TIMESTAMP, lastUpdatedBy = '"
                    + currentUser + "' WHERE User_Id = '" + userId + "';");
            statement.executeUpdate();
            System.out.println("User successfully modified!");
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
    }
    public static void deleteUser(int userId){
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement(
                    "DELETE FROM users WHERE User_Id = '" + userId + "';");
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
    }
    public static int generateUserId() {
        int userId = 0;
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT "
                    + "userId FROM users ORDER BY User_Id ASC;");
            ResultSet rs = statement.executeQuery();
            if (rs.last()){
                userId = ((Number) rs.getObject(1)).intValue() + 1;
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
        return userId;
    }

    public static Boolean validateLogin(String username, String password) {
        try {
            Statement statement = ConnectorDb.connectDb().createStatement();
            String query = "SELECT * FROM users WHERE User_Name='" + username + "' AND password='" + password +"'";
            ResultSet results = statement.executeQuery(query);
            if(results.next()) {
                currentUser = new User(results.getInt("User_Id"),
                        results.getString("User_Name"),
                        results.getString("password"));
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



}