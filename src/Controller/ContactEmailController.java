package Controller;

import Database.CustomerDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import static Model.User.currentUser;


/**
 * This class is the controller which contains  logic for the ContactEmail fxml
 */
public class ContactEmailController implements Initializable {


    @FXML private ComboBox<String> customerNameBox;
    @FXML private ComboBox<String> contactNameBox;
    @FXML private ComboBox<String> appointmentBox;
    @FXML private Label contactNameText;



    /**
     * This function initializes the settings for when the scene loads
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerNameBox.setItems(CustomerDatabase.CustomerList());
        appointmentBox.setItems(CustomerDatabase.CustomerAppointmentList(customerNameBox.getValue()));

//        customerNameBox.setPromptText();


    }



    /**
     * This function generates a report of the emails of the contacts for a selected appointment
     */
    @FXML
    void generateContactReport(ActionEvent event) {
        Date reportTime = Calendar.getInstance().getTime();
        String selectCustomerName = customerNameBox.getValue();

        String selectedAppointment = appointmentBox.getValue();
        String contactName = contactNameBox.getValue();


        File reportsByMonthFile = new File(" Contact$Customer.txt");
        if (!reportsByMonthFile.exists()) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" Contact$Customer.txt"), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\n" +
                        "Report:\n" +
                        "The Contact  for Customer: " + selectCustomerName + " For an Appointment  ON : " + selectedAppointment + " is Contact Name: " + contactName + "\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        } else {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" Contact$Customer.txt", true), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\n" +
                        "Report:\n" +
                        "The Contact  for Customer: " + selectCustomerName + " For an Appointment  ON : " + selectedAppointment + " is Contact Name: " + contactName + "\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }


    }

    /**
     * This Back function that changes the scene back to the Main Menu fxml
     */

    @FXML
    void getAppointment(ActionEvent event) {

        String selectedCustomer = customerNameBox.getValue();
        String selectedAppointment = appointmentBox.getValue();


        contactNameBox.setItems(CustomerDatabase.appointmentContactList(selectedCustomer, selectedAppointment));




        String contactName = CustomerDatabase.appointmentContactList(selectedCustomer, selectedAppointment).toString();
        contactNameBox.setPromptText(contactName);
        contactNameBox.setValue(contactName);
        contactNameText.setText(contactNameBox.getValue());
    }


    /**
     * This Back function that changes the scene back to the Main Menu fxml
     */
    @FXML
    void back(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This function gets the customer name of the selected appointment
     */
    @FXML
    void getCustomerName(ActionEvent event) {
        String selectedCustomer = customerNameBox.getValue();


        appointmentBox.setItems(CustomerDatabase.CustomerAppointmentList(selectedCustomer));

    }



}
