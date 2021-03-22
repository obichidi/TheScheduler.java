package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.stage.Stage;

import java.io.*;



/**
 * this is the class for the Report controller for the Report fxml
 *
 */

public class ReportController {

    @FXML private Button contactSchedule;
    @FXML private Button customerAppointmentSummary;
    @FXML private Button contactEmailAppointment;

    @FXML private ChoiceBox<String> reportChoiceBox;


/**This is the constructor for the ReportController*/
public ReportController(){}


    /**
     * this function changes the scene to the FindContact fxml
     * @param event  this is an event driven function
     *
     */
    @FXML
   public void findContact(ActionEvent event) {

        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/FindContact.fxml"));
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    /**
     * this function prints the summary of customer appointments into a text file
     * @param event this is an event driven function
     */
    @FXML
   public void customerAppointmentSummary(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/CustomerStatistics.fxml"));
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    /**
     * this function changes the scene to the MainMenu fxml
     * @param event this is an event driven function
     */
    @FXML
   public void back(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }





    /**
     * this function changes the scene to the AddAppointment fxml
     * @param event this is an event driven function
     */

    @FXML
   public void showContactSchedules(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/ContactSchedules.fxml"));
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}
