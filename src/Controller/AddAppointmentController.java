package Controller;

import Database.AppointmentDatabase;
import Database.CustomerDatabase;

import Model.Appointment;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import static Model.User.currentUser;


import java.time.LocalDate;

import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    @FXML private Pane scheduleAppointment;
    @FXML private  TextField appointmentTitleAdd;



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
    @FXML private Button test;
    @FXML private Button addAppointment;
    @FXML private Button back;
    @FXML private ComboBox<String> startTime;
    @FXML private ComboBox<String> endTime;
    @FXML private ComboBox<String> startMinutes;
    @FXML private ComboBox<String> endMinutes;


        public static Appointment selectAppointment;
    public static  Customer selectCustomer;
//    public static  Contact selectContact;

    ObservableList<String> startTimes = FXCollections.observableArrayList();
    ObservableList<String> endTimes = FXCollections.observableArrayList();
    ObservableList<String> startsMinutes = FXCollections.observableArrayList();
    ObservableList<String> endsMinutes = FXCollections.observableArrayList();
    ObservableList<String> Locations = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IdCustomer.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCustomer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        contact.setItems(AppointmentDatabase.ContactList());
        userInfo.setText("Welcome " + User.currentUser.getUsername());
        appointmentType.setItems(AppointmentDatabase.TypeList());
        locationAdd.setItems(AppointmentDatabase.LocationList());
        try {
            customerTable.setItems(CustomerDatabase.getAllCustomers());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        contact.setItems(AppointmentDatabase.ContactList());


        startTimes.addAll("9 a.m.", "10 a.m.", "11 a.m.", "12 p.m.", "1 p.m.", "2 p.m.", "3 p.m.", "4 p.m.", "5 p.m.");
        endTimes.addAll("9 a.m.", "10 a.m.", "11 a.m.", "12 p.m.", "1 p.m.", "2 p.m.", "3 p.m.", "4 p.m.", "5 p.m.");
        startsMinutes.addAll("00", "15", "30", "45");
        endsMinutes.addAll("00", "15", "30", "45");
        startTime.setItems(startTimes);
        endTime.setItems(endTimes);
        startMinutes.setItems(startsMinutes);
        endMinutes.setItems(endsMinutes);
        Locations.addAll("New York, New York", "London, England", "Paris, France");
        locationAdd.setItems(Locations);



        }


    @FXML
    void addAppointment(ActionEvent event) throws ParseException, SQLException {





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


        appointmentInfo.setText(" Contact Name: " + contactId + "\nCustomer ID: " + customerId +
                customerName + "\nLocation: " + location + "\n Description: " + description + "\nTitle:  " + title + "\nS: "
                + start + "\n End: " + end + "\n End Time: " +
                "\nDate: " + date + "\nUser: " + currentUser.getUserId());


    }


    @FXML
    void test(ActionEvent event){




    }
    @FXML
    void addAppointmentType(ActionEvent event){

    }

    @FXML
    void addContact(ActionEvent event){

    }

    @FXML
    void addDate(ActionEvent event) {

    }

    @FXML
    void addLocation(ActionEvent event) {

    }

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

    public void errorChecks(){
        if(customerTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("You Must select a Customer In order to Add an appointment.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;

        }

        if(appointmentTitleAdd.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a title or write one in.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;

        }

        if(contact.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a contact.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }

        if(locationAdd.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a Location.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }

        if(datePicker.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a date.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }
        if(startTime.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a Start Hour.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }

        if(startMinutes.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a start minute.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }
        if(endTime.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select an End Hour.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }
        if(endMinutes.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select an End Minute.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }
        if(appointmentType.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select an Appointment Type.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }

        if(appointmentDescription.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please Add a description.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }


    }


    @FXML
    void selectCustomer(MouseEvent event) {

    }
}
