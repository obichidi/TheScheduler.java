package Controller;


import Database.AppointmentDatabase;
import Database.UserDatabase;


import Lambda.getString;
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
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;


import static Util.Login.recordLogin;



/**
 * this us the class that implements the functionality of the login fxml
 * there is a LAMBDA expression in the login error function for messages.
 */
public class LoginController implements Initializable {

    @FXML private AnchorPane loginAnchor;
    @FXML private TextField usernameText;
    @FXML private Label titleText;
    @FXML private TextField passwordText;
    @FXML private Label usernameLabel;
    @FXML private  Label passwordLabel;
    @FXML private Button exitButton;
    @FXML private Button loginButton;
   @FXML private Label location;
    String change;
    String appointmentAlert = "";
    private String errorLabel;
    private String errorMessage;

/** this is the constructor for the Login controller*/
public  LoginController(){}


    /**
     * this function  initializes the settings for when the scene opens
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        languageCheck("");
       usernameText.setText("test");
       passwordText.setText("test");



//        Locale frenchLocale = new Locale("fr", "FR");
//        Locale.setDefault(frenchLocale);
//     rb = ResourceBundle.getBundle("Language/Nat", Locale.getDefault());
//        if (Locale.getDefault().getLanguage().equals("fr")) {
//
//
//
//            System.out.println(rb.getString("Login"));
//
//
//        }

       location.setText(String.valueOf(ZoneId.systemDefault()));
        try {
            System.out.println(AppointmentDatabase.getAppointmentsIn15Mins());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    String error = "Please Input the Correct Credentials";





/** this function  configures the login of the user it uses a boolean  to see if the username and password match then the login is recorded to the login report.
 * there is a LAMBDA for get string to show repetitive messages easier*/
    @FXML
    private void Login(ActionEvent event) throws IOException, ParseException {

        getString message = s -> s;

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
                alert.setContentText((message.getString("There are no upcoming Appointments in the next 15 minutes.")));
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

            alert.setContentText(message.getString(error));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            loginError();

            appointmentAlert = AppointmentDatabase.getAppointmentsIn15Mins();
            try {
                if (appointmentAlert.length() > 0) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(message.getString("There are appointments within 15 minutes."));
                    alert.setContentText(appointmentAlert);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.showAndWait();
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }

        }
    }

    /**This function changes the languages  message languages for the login scene by locale
     * @param code  */
    private void languageCheck(String code){


        ResourceBundle  rb = ResourceBundle.getBundle("Language/Nat", Locale.getDefault());

        passwordText.setText(rb.getString("Password"));
        titleText.setText(rb.getString("title"));
        usernameLabel.setText(rb.getString("usernameLabel"));
        passwordLabel.setText(rb.getString("passwordLabel"));
        exitButton.setText(rb.getString("exit"));
        loginButton.setText(rb.getString("login"));
        error = rb.getString("error");

    }

    /**
     * this function exits the whole program
     * @throws IOException for errors
     * @param event this is an ActionEvent driven function
     */

        @FXML
        public void exit (ActionEvent event) throws IOException {
            Platform.exit();
        }


    /**
     * this function that prints the recorded unsuccessful login to the login report.
     * there is a LAMBDA expression getString  the lambda is useful  for repeating messages.
     *
     *
     */

    public void loginError(){
         // the get string lambda expression
           getString message = s -> s;

        Date loginTime = Calendar.getInstance().getTime();
        File loginFile = new File("login_activity.txt");
        if (!loginFile.exists()) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("login_activity.txt"), "utf-8"))) {
                writer.write( message.getString("There was an Unsuccessful attempt to log in on") +
                        loginTime + "\r\n");
            } catch (IOException ex) {
                System.out.println("IOException: " + ex);
            }
        } else {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("login_activity.txt", true), "utf-8"))) {
                writer.write(message.getString("There was an Unsuccessful attempt to log in on")+
                        loginTime + "\r\n");
            } catch (IOException ex) {
                System.out.println("IOException: " + ex);
            }

        }

    }
    }


