package Controller;


import Database.UserDatabase;


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

import java.io.IOException;
import java.net.URL;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private AnchorPane loginAnchor;
    @FXML private TextField usernameText;
    @FXML private TextField passwordText;
    @FXML private Button exitButton;
    @FXML private Button loginButton;
   @FXML private Label location;
    String change;
    String upcoming = "";
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



    @Override
    public void initialize(URL url, ResourceBundle rb) {
       usernameText.setText("test");
       passwordText.setText("test");
       location.setText(Locale.getDefault().getCountry());
    }


    @FXML
    private void Login(ActionEvent event) throws IOException {
        String username = usernameText.getText();
        String password = passwordText.getText();
        boolean loginAccepted = UserDatabase.validateLogin(username, password);
        if (loginAccepted) {
            System.out.println("login Accepted!!!!!!");
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("There are appointments in the next 15 minutes.");
            alert.setContentText("wrong username");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();

        }
//            upcoming = UpcomingAppointment.returnUpcomingAppointments();
        try {
            if (upcoming.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("There are appointments in the next 15 minutes.");
                alert.setContentText(upcoming);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
//            LoginRecorder.recordLogin();

        //    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle(errorLabel);
//            alert.setContentText(errorMessage);
//            alert.initModality(Modality.APPLICATION_MODAL);
//            alert.showAndWait();
//        }
        //}

//    @FXML
//    public void  Login(ActionEvent event) throws IOException {
//
////        System.out.println(usernameText.getText());
////        System.out.println(password.getText());
//
//
//        System.out.println("Default locale:" + Locale.getDefault().toString());
//        System.out.println();
//        Locale frenchLocale = new Locale("fr", "FR");
//        Locale.setDefault(frenchLocale);
   }

        @FXML
        public void exit (ActionEvent event) throws IOException {
            Platform.exit();
        }
    }


