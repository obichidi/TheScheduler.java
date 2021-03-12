package Controller;


import Database.AppointmentDatabase;
import Database.UserDatabase;


import Util.TimestampToLocal;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;


import static Util.Login.recordLogin;



/**
 * this us the classn that implements the functionality of the login fxml
 */
public class LoginController implements Initializable {

    @FXML private AnchorPane loginAnchor;
    @FXML private TextField usernameText;
    @FXML private TextField passwordText;
    @FXML private Button exitButton;
    @FXML private Button loginButton;
   @FXML private Label location;
    String change;
    String appointmentAlert = "";
    private String errorLabel;
    private String errorMessage;



//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//
//       Locale locale = Locale.getDefault();
//      rb = ResourceBundle.getBundle("Language.NAT");
//        loginButton.setText(rb.getString("Login"));
//       usernameText.setText((rb.getString(("username"))));
//
//      exitButton.setText(rb.getString(("Exit")));


    /**
     * this function  initializes the settings for when the scene opens
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       usernameText.setText("test");
       passwordText.setText("test");
       location.setText(Locale.getDefault().getCountry());
        try {
            System.out.println(AppointmentDatabase.getAppointmentsIn15Mins());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * this function  configures the login of the user it uses a boolean  to see if the username and password match then the login is recorded to the login report.
     */
    @FXML
    private void Login(ActionEvent event) throws IOException, ParseException {

        String username = usernameText.getText();
        String password = passwordText.getText();
        boolean loginAccepted = UserDatabase.validateLogin(username, password);
        if (loginAccepted) {
            recordLogin();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            String polo = null;
            try {
                polo = AppointmentDatabase.getAppointmentsIn15Mins();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(polo == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("UPCOMING APPOINTMENTS");
                alert.setContentText(("There are no upcoming Appointments in the next 15 minutes."));
                alert.initModality(Modality.NONE);
                alert.showAndWait();

            }
            else{ Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("UPCOMING APPOINTMENTS");
                alert.setContentText((polo));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.showAndWait();}

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setContentText("Please Input the Correct UserName");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            loginError();

            appointmentAlert = AppointmentDatabase.getAppointmentsIn15Mins();
            try {
                if (appointmentAlert.length() > 0) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("There are appointments within 15 minutes.");
                    alert.setContentText(appointmentAlert);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.showAndWait();
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }

        }
    }

    /**
     * this function exits the whole program
     */

        @FXML
        public void exit (ActionEvent event) throws IOException {
            Platform.exit();
        }


    /**
     * this function that prints the recorded unsuccessful login to the login report.
     */


        public void loginError(){
            Date loginTime = Calendar.getInstance().getTime();
            File loginFile = new File(" login_activity.txt");
            if (!loginFile.exists()) {
                try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(" login_activity.txt"), "utf-8"))) {
                    writer.write( " There was an Unsuccessful attempt to log in on " +
                            loginTime + "\r\n");
                } catch (IOException ex) {
                    System.out.println("IOException: " + ex);
                }
            } else {
                try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(" login_activity.txt", true), "utf-8"))) {
                    writer.write("There was an Unsuccessful attempt to log in on" +
                            loginTime + "\r\n");
                } catch (IOException ex) {
                    System.out.println("IOException: " + ex);
                }

            }

        }
    }


