package Controller;

import Database.AppointmentDatabase;
import Database.CustomerDatabase;


import Model.Customer;
import Model.User;
import Util.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;



import java.time.LocalDate;

import java.util.ResourceBundle;

/**
 * This class handles all the logic for the AddAppointment scene
 */

public class AddAppointmentController implements Initializable {
    @FXML private Pane scheduleAppointment;
    @FXML private TextField appointmentTitleAdd;
    @FXML private ComboBox<String> locationAdd;
    @FXML private DatePicker datePicker;
    @FXML private TextArea appointmentDescription;
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, Integer> IdCustomer;
    @FXML private TableColumn<Customer, String> nameCustomer;
    @FXML private ComboBox<String> contact;
    @FXML private ComboBox<String> appointmentType;
    @FXML private Label userInfo;
    @FXML private Label appointmentInfo;
    @FXML private Button addAppointment;
    @FXML private Button back;
    @FXML private ComboBox<String> startTime;
    @FXML private ComboBox<String> endTime;
    @FXML private ComboBox<String> startMinutes;
    @FXML private ComboBox<String> endMinutes;


    public static Customer selectCustomer;


    ObservableList<String> startTimes = FXCollections.observableArrayList();
    ObservableList<String> endTimes = FXCollections.observableArrayList();
    ObservableList<String> startsMinutes = FXCollections.observableArrayList();
    ObservableList<String> endsMinutes = FXCollections.observableArrayList();
    ObservableList<String> Locations = FXCollections.observableArrayList();

    /**
     * Ths is the initialize function that configures the setting on scene load.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();
        IdCustomer.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCustomer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        contact.setItems(AppointmentDatabase.ContactList());
        userInfo.setText("Welcome " + User.currentUser.getUsername());
        appointmentType.setItems(AppointmentDatabase.TypeList());
        //change this!!!
        contact.setValue("Anika Costa");
         appointmentType.setValue("De-Briefing");
       appointmentTitleAdd.setText("Group");
        locationAdd.setItems(AppointmentDatabase.LocationList());
        try {
            customerTable.setItems(CustomerDatabase.getAllCustomers());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        contact.setItems(AppointmentDatabase.ContactList());


        startTimes.addAll("9", "10", "11", "12", "1", "2", "3", "4", "5");
        endTimes.addAll("9", "10", "11", "12", "1", "2", "3", "4", "5");
        startsMinutes.addAll("00", "15", "30", "45");
        endsMinutes.addAll("00", "15", "30", "45");
        startTime.setItems(startTimes);
        endTime.setItems(endTimes);
        startMinutes.setItems(startsMinutes);
        endMinutes.setItems(endsMinutes);
        Locations.addAll("New York, New York", "London, England", "Paris, France");
        locationAdd.setItems(Locations);


    }

    /**
     * This is the ADD APPOINTMENT function that adds the appointment data into the database
     */

    @FXML
    void addAppointment(ActionEvent event) throws ParseException, SQLException, IOException {

    if(errorChecks() == false){
        selectCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();
        String customerName = selectCustomer.getCustomerName();
        int customerId = selectCustomer.getCustomerId();

        String contactName = contact.getValue();
        int contactId = AppointmentDatabase.findContactId(contactName);

        String type = appointmentType.getValue();

        String description = appointmentDescription.getText();
        String location = locationAdd.getValue();
        String title = appointmentTitleAdd.getText();


        String startTimes = startTime.getValue();
        String endTimes = endTime.getValue();
        LocalDate date = datePicker.getValue();

        Timestamp start = Time.generateStartTimestamp(datePicker, startTime, startMinutes, locationAdd);
        Timestamp end = Time.generateEndTimestamp(datePicker, endTime, endMinutes, locationAdd);





        if (start.after(end)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Check appointment times");
            errorAlert.setContentText("Ensure the end time is after the start time.");
            errorAlert.showAndWait();
            return;
        }


        AppointmentDatabase.addAppointment(customerId, customerName, title, location, description, type, contactId,
                start, end);
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/View/MainAppointment.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex.getMessage());
        }
    }
    }


    /**
     * This Back function that changes the scene back to the Main Menu fxml
     */
    @FXML
    void back(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex.getMessage());
        }

    }

    /**
     * This the ERROR CHECK function that checks to make sure the fields are input correctly
     * @return  retuns a boolean true if there is an error and false if there is not.
     */

    public boolean errorChecks() {
        if (customerTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("You Must select a Customer In order to Add an appointment.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;

        }

        if (appointmentTitleAdd.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a title or write one in.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;

        }

        if (contact.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a contact.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }

        if (locationAdd.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a Location.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }

        if (datePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a date.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }
        if (startTime.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a Start Hour.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }

        if (startMinutes.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a start minute.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }
        if (endTime.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select an End Hour.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }
        if (endMinutes.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select an End Minute.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }
        if (appointmentType.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select an Appointment Type.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }

        if (appointmentDescription.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please Add a description.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }

        return false;
    }
}