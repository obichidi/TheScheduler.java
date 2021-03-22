package Controller;

import Database.CustomerDatabase;
import Lambda.displayMessage;
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
 * There is a LAMBDA declaration
 */
public class FindContactController implements Initializable {


    @FXML private ComboBox<String> customerNameBox;
    @FXML private ComboBox<String> contactNameBox;
    @FXML private ComboBox<String> appointmentBox;
    @FXML private Label contactNameText;

    displayMessage message = s -> System.out.println(s);

    /**
     * This function initializes the settings for when the scene loads
     * @param url URL
     * @param rb RB
     *Theres a LAMBDA expression display message where instead of a system out print line.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerNameBox.setItems(CustomerDatabase.CustomerList());
        appointmentBox.setItems(CustomerDatabase.CustomerAppointmentList(customerNameBox.getValue()));




    }



    /**
     * This function generates a report of the emails of the contacts for a selected appointment and prints it to a text file
     * @param event  this is an event driven function
     * There is a LAMBDA expression instead of a system out message.display message
     */
    @FXML
   public void generateContactReport(ActionEvent event) {
        Date reportTime = Calendar.getInstance().getTime();
        String selectCustomerName = customerNameBox.getValue();

        String selectedAppointment = appointmentBox.getValue();
        String contactName = contactNameBox.getValue();


        File reportsByMonthFile = new File(" Contact$Customer.txt");
        if (!reportsByMonthFile.exists()) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("Contact$Customer.txt"), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\n" +
                        "Report:\n" +
                        "The Contact  for Customer: " + selectCustomerName + " For an Appointment  ON : " + selectedAppointment + " is Contact Name: " + contactName + "\n");
            } catch (IOException ex) {
                message.displayMessage("IO Exception: " + ex);
            }
        } else {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("Contact$Customer.txt", true), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\n" +
                        "Report:\n" +
                        "The Contact  for Customer: " + selectCustomerName + " For an Appointment  ON : " + selectedAppointment + " is Contact Name: " + contactName + "\n");
            } catch (IOException ex) {
                message.displayMessage("IO Exception: " + ex);
            }
        }


    }

    /**
     * This Back function that changes the scene back to the Main Menu fxml
     * @param event this is an event driven function
     */

    @FXML
   public void getAppointment(ActionEvent event) {

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
     * @param event this is an invent driven function
     *There is a display message LAMBDA instead of a system out print line
     */
    @FXML
   public void back(ActionEvent event) {



        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        } catch (IOException ex) {

            message.displayMessage("IO Exception: " + ex);

        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This function gets the customer name of the selected appointment
     * @param event this is an even driven function
     */
    @FXML
   public void getCustomerName(ActionEvent event) {
        String selectedCustomer = customerNameBox.getValue();


        appointmentBox.setItems(CustomerDatabase.CustomerAppointmentList(selectedCustomer));

    }



}
