package Model;

import Util.ConnectorDb;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * this the class for the user model
 *
 */
public class User {
    public static User currentUser;
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private final SimpleIntegerProperty userId = new SimpleIntegerProperty();
    private final SimpleStringProperty username = new SimpleStringProperty();
    private final SimpleStringProperty userPassword = new SimpleStringProperty();


    /**
     * this is the constructor method for the user class
     * @param id  the user id
     * @param name the user name
     * @param password  the user password
     */
    public User (int id, String name, String password) {
        setUserId(id);
        setUsername(name);
        setUserPassword(password);
    }



    /**
     * this is the setter method for the username
     * @param username   returns users name
     */
    public void setUsername(String username) {
        this.username.set(username);
    }

    /**
     * this is the getter method for the  userName
     * @return  userName returns users name
     */
    public String getUsername() {
        return username.get();
    }


    /**
     * this is the setter method for the userId
     * @param userId   returns  the users id
     */
    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    /**
     * this is the getter method for the  userId
     * @return  userId returns users Id
     */

    public int getUserId() {
        return userId.get();
    }


    /**
     * this is the setter method for the userPassword
     * @param userPassword returns users password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword.set(userPassword);
    }


    /**
     * this is the getter method for the  userPassword
     * @return  userPassword returns users password
     */
    public String getUserPassword() {
        return userPassword.get();
    }




}
