package Controller;

import Database.AppointmentDatabase;
import Database.CustomerDatabase;
import Model.Contact;
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

    @FXML private TableView<Contact> contactTable;
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Contact, Integer> contactId;
    @FXML private TableColumn<Contact, String> contactName;
    @FXML private TableColumn<Customer, Integer> IdCustomer;
    @FXML private TableColumn<Customer, String> nameCustomer;

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



    public static  Customer selectCustomer;
    public static  Contact selectContact;

    ObservableList<String> startTimes = FXCollections.observableArrayList();
    ObservableList<String> endTimes = FXCollections.observableArrayList();
    ObservableList<String> startsMinutes = FXCollections.observableArrayList();
    ObservableList<String> endsMinutes = FXCollections.observableArrayList();
    ObservableList<String> Locations = FXCollections.observableArrayList();
    ObservableList<String> zero = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IdCustomer.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCustomer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        contactId.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        contactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
         userInfo.setText("Welcome " + User.currentUser.getUsername());

        appointmentType.setItems(AppointmentDatabase.TypeList());
//            appointmentAdd.getEditor().textProperty().addListener((obs, oldText, newText) -> {
//            appointmentAdd.setValue(newText);
        locationAdd.setItems(AppointmentDatabase.LocationList());


        try {
            customerTable.setItems(CustomerDatabase.getAllCustomers());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            contactTable.setItems(AppointmentDatabase.getAllContacts());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


//        contactAdd.setItems(AppointmentDatabase.ContactList());

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



              errorChecks();


             selectContact = contactTable.getSelectionModel().getSelectedItem();
             selectCustomer = customerTable.getSelectionModel().getSelectedItem();
             String customerName = selectCustomer.getCustomerName();
            int customerId = selectCustomer.getCustomerId();
            int contactId = selectContact.getContactId();
             String contactName = selectContact.getContactName();

            String type = appointmentType.getValue();

            String description = appointmentDescription.getText();
            String location = locationAdd.getValue();
            String title = appointmentTitleAdd.getText();

            String zero = ":00";
            String startTimes = startTime.getValue();
            String endTimes = endTime.getValue();
            LocalDate date =  datePicker.getValue();
            Timestamp start = Time.generateStartTimestamp( datePicker, startTime, startMinutes, locationAdd);
            Timestamp end = Time.generateEndTimestamp(datePicker, endTime, endMinutes, locationAdd);

        if (start.after(end)){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Check appointment times");
            errorAlert.setContentText("Ensure the end time is after the start time.");
            errorAlert.showAndWait();
            return;
        }


        AppointmentDatabase.addAppointment(customerId, customerName, title,  location,  description, type, contactId,
        start,  end );
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


//        String j = endTime.getValue().substring(0,(endTime.getValue().length()  - 5));
//        String i = startTime.getValue().substring(0,(startTime.getValue().length()  - 5));
//        String b = ":00";
//        Timestamp startDateTime = Timestamp.valueOf(date + " " + i + b);
//        Timestamp endTime = Timestamp.valueOf(date + " " + j + b);



//        String inputDateInString=  startDateTime;
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyy hh:mm:ss");

//        Date parsedDate = dateFormat.parse("inputDateInString");
//
//        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
////
////                  timestamp.getTime();

//        String time = startDateTime;
//        LocalTime localTimeObj = LocalTime.parse(time);

//        Timestamp timestamp = Timestamp.valueOf(String.valueOf(startDateTime));

            appointmentInfo.setText(" Contact Name: "+ contactId + "\nCustomer ID: " + customerId +
                 customerName + "\nLocation: " + location + "\n Description: "+ description +"\nTitle:  "+ title + "\nS: "
                    + start+ "\n End: " + end +"\n End Time: "+
                     "\nDate: " + date +  "\nUser: " + currentUser.getUserId());



//        String exceptions = "";
//
//
//
//        exceptions += Validations.validateAppointment(appointmentAdd.getValue(), contactAdd, locationAdd, datePicker, startTime,  endTime);
//        if(exceptions.length()!=0){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error.");
//            alert.setContentText(exceptions);
//            alert.initModality(Modality.APPLICATION_MODAL);
//            alert.showAndWait();
//            return;
//        }


//        Timestamp start = Time.Timestamp(datePicker, startTime,  locationAdd);
//        Timestamp end = Time.Timestamp(datePicker, endTime, locationAdd);
//        if (start.after(end)){
//            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
//            errorAlert.setHeaderText("Check appointment times");
//            errorAlert.setContentText("Ensure the end time is after the start time.");
//            errorAlert.showAndWait();
//            return;
//        }

//
//        String overlap = AppointmentDatabase.isAppointmentOverlapping(start, end, customerId,  appointmentId);
//        if (!overlap.isEmpty()){
//            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
//            errorAlert.setHeaderText("Appointment is overlapping");
//            errorAlert.setContentText(overlap);
//            errorAlert.showAndWait();
//            return;
//        }

//        AppointmentDatabase.addAppointment(appointmentId, customerId, title, location, contact,  start, end);
//        try {
//            ((Node) (event.getSource())).getScene().getWindow().hide();
//            Stage stage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("/View/MainAppointment.fxml"));
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException ex) {
//            System.out.println("IO Exception: " + ex.getMessage());
//        }

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
            alert.setContentText("You have not selected a customer for appointment scheduling.");
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

        if(contactTable.getSelectionModel().isEmpty()){
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
