package Controller;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;


import Database.AppointmentDatabase;
import Model.User;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * this is the class that implements the main menu fxml
 *
 */
public class MainMenuController implements  Initializable{
   @FXML private Label loginInfo;
    @FXML private Button tester;


/**This is the controller for the MainMenuController*/
    public MainMenuController(){}


    /**
     * this function changes the scene to the AddAppointment fxml
     * initializes the settings of the MainMenu fxml
     */
@Override

public void initialize(URL url, ResourceBundle rb) {

    LocalDateTime now = LocalDateTime.now();
    ZoneId zoneId = ZoneId.systemDefault();
    ZonedDateTime zoneNow = now.atZone(zoneId);
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG);


}

/**
     * this function function exits the program
     * @param event this is an event driven function
     *
     */

    @FXML
    public void exit(ActionEvent event) {
        Platform.exit();
    }



    /**
     * this function changes the scene to the Main Appointment fxml
     * @param event this is an event driven function
     *
     */
    @FXML
   public void showAppointments(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/View/MainAppointment.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }




    /**
     * this function changes the scene to the Main Appointment fxml
     * @param event this is an event driven function
     *
     */
    @FXML
   public void showCustomers(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/View/MainCustomer.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }


    /**
     * this function changes the scene to the Reports fxml
     * @param event this is an event driven function
     *
     */
   @FXML
   public void Report(ActionEvent event) {
       try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
           Scene scene = new Scene(root);
           stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
          System.out.println("Reports Main Error: " + e.getMessage());
      }

   }
}



