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

public class ContactEmailController implements Initializable {


    @FXML private ComboBox<String> customerNameBox;
    @FXML private ComboBox<String> contactNameBox;
    @FXML private ComboBox<String> appointmentBox;
    @FXML private Label contactName;
    @FXML private Label contactEmailText;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        customerNameBox.setItems(CustomerDatabase.CustomerList());
        appointmentBox.setItems(CustomerDatabase.CustomerAppointmentList(customerNameBox.getValue()));



    }


    @FXML
    void getContactEmail(ActionEvent event) {
     String  contactNames = contactNameBox.getValue();
        String contactEmail = contactEmailText.getText();
     System.out.println(contactEmail);
       contactEmailText.setText(String.valueOf(CustomerDatabase.ContactEmailList(contactNames)));

    }



    @FXML
    void generateEmailReport(ActionEvent  event){
        Date reportTime = Calendar.getInstance().getTime();
        String selectCustomerName = customerNameBox.getValue();

        String selectedAppointment = appointmentBox.getValue();
        String contactName =  contactNameBox.getValue();
        String contactEmail = contactEmailText.getText();

        File reportsByMonthFile = new File(" ContactEmail_4Customer.txt");
        if(!reportsByMonthFile.exists()){
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" ContactEmail_4Customer.txt"), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\n" +
                        "Report:\n" +
                        "The Contact Email for Customer: " +  selectCustomerName + " For an appointment ON : "  +  selectedAppointment +   "is :" + contactEmail + " Contact Name: " + contactName + "\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
        else {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" ContactEmail_4Customer.txt",true), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\n" +
                        "Report:\n" +
                        "The Contact Email for Customer: " +  selectCustomerName + " For an Appointment  ON : "  +  selectedAppointment +   " is :" + contactEmail + " Contact Name: " + contactName + "\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }


    }


    @FXML
    void getAppointment(ActionEvent event) {

           String selectedCustomer  = customerNameBox.getValue();
            String selectedAppointment = appointmentBox.getValue();


            contactNameBox.setItems(CustomerDatabase.appointmentContactList(selectedCustomer, selectedAppointment));

//         System.out.println(contactName);

//
//            System.out.println();
       contactNameBox.setPromptText(String.valueOf(CustomerDatabase.appointmentContactList(selectedCustomer, selectedAppointment)));

    }

    @FXML
    void getContact(ActionEvent event) {

        String contactName = contactNameBox.getValue();

        contactNameBox.setPromptText(contactName);
    }
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



    @FXML
    void getCustomerName(ActionEvent event) {
            String selectedCustomer = customerNameBox.getValue();


        appointmentBox.setItems(CustomerDatabase.CustomerAppointmentList(selectedCustomer));

    }


}
