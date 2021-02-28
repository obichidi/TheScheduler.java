package Controller;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;


import Model.User;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;


import javafx.stage.Stage;

public class MainMenuController implements  Initializable{
   @FXML private Label loginInfo;



@Override

public void initialize(URL url, ResourceBundle rb) {
//   loginInfo.setText("The User  " + User.currentUser.getUsername() + " successfully logged in on " + Calendar.getInstance().getTime() + " From " + Locale.getDefault());


}

    @FXML
    void addAppointment(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(" Error: " + e.getMessage());
        }

    }





    @FXML
    void exit(ActionEvent event) throws IOException {
        Platform.exit();
    }

    @FXML
    void showAppointments(ActionEvent event) {
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

    @FXML
    void showCustomers(ActionEvent event) {
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

    @FXML
    void showLogs(ActionEvent event) {
        File file = new File("log.txt");
        if (file.exists()) {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    System.out.println("Error Opening Log File: " + e.getMessage());
                }
            }
        }

    }

   @FXML
    void showReports(ActionEvent event) {
       try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("View/Report.fxml"));
           Scene scene = new Scene(root);
           stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
          System.out.println("Reports Main Error: " + e.getMessage());
      }

   }
}



