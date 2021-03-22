package Controller;

import Database.AppointmentDatabase;
import Database.CustomerDatabase;


import Database.UserDatabase;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
    @FXML private ComboBox startAmPm;
    @FXML private ComboBox endAmPm;
    @FXML private ComboBox<String> startTime;
    @FXML private ComboBox<String> endTime;
    @FXML private ComboBox<String> startMinutes;
    @FXML private ComboBox<String> endMinutes;
    @FXML private ComboBox<String>userBox;


    /**
     * This is for holding the information from the customer selected
     */
    public static Customer selectCustomer;

    ObservableList<String> am_pm = FXCollections.observableArrayList();
    ObservableList<String> startTimes = FXCollections.observableArrayList();
    ObservableList<String> endTimes = FXCollections.observableArrayList();
    ObservableList<String> startsMinutes = FXCollections.observableArrayList();
    ObservableList<String> endsMinutes = FXCollections.observableArrayList();
    ObservableList<String> Locations = FXCollections.observableArrayList();



    /**This is the constructor for the AddAppointment Controller*/
  public  AddAppointmentController(){}

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
        userBox.setItems(UserDatabase.userList());
        userBox.setPromptText(User.currentUser.getUsername());

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

        am_pm.addAll("Am","Pm");
        startTimes.addAll("8","9", "10", "11", "12", "1", "2", "3", "4", "5","6","7","8","9");
        endTimes.addAll("8","9", "10", "11", "12", "1", "2", "3", "4", "5","6","7","8","9");
        startsMinutes.addAll("00", "15", "30", "45");
        endsMinutes.addAll("00", "15", "30", "45");
        startTime.setItems(startTimes);
        endTime.setItems(endTimes);
        startAmPm.setItems(am_pm);
        endAmPm.setItems(am_pm);

        startMinutes.setItems(startsMinutes);
        endMinutes.setItems(endsMinutes);
        Locations.addAll("New York, New York", "London, England", "Paris, France");
        locationAdd.setItems(Locations);


    }




    /**
     * This is the ADD APPOINTMENT function that adds the appointment data into the database
     * @param event  this is an event driven function
     */

    @FXML
   public  void addAppointment(ActionEvent event) {


        if(errorChecks() == false){
            selectCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();
            String customerName = selectCustomer.getCustomerName();
            int customerId = selectCustomer.getCustomerId();

            String contactName = contact.getValue();
            int contactId = AppointmentDatabase.findContactId(contactName);

            String type = appointmentType.getValue();
            int userId = UserDatabase.findUserId(userBox.getValue());
            String description = appointmentDescription.getText();
            String location = locationAdd.getValue();
            String title = appointmentTitleAdd.getText();



            Timestamp start = Time.generateStartTimestamp(datePicker, startTime, startMinutes, locationAdd, startAmPm);
            Timestamp end = Time.generateEndTimestamp(datePicker, endTime, endMinutes, locationAdd,endAmPm);


            int b3 = start.compareTo(end);
               if (b3 == 0){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Check appointment times");
                errorAlert.setContentText("The End Time  Must be after the start time.");
                errorAlert.showAndWait();
              return;
            }


            AppointmentDatabase.addAppointment( customerId,  customerName,  title,  location,  description,  type ,userId,  contactId,
            start,  end);
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
     * @param event  this is an event driven function
     */
    @FXML
     public void back(ActionEvent event) {
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

        if(startAmPm.getValue().equals("Pm" )&&startTime.getValue().equals("10")  ){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error.");
                alert.setContentText("Please Pick a time within business hours!\nBusiness hours are between 8:00Am -10:Pm");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.showAndWait();
                   return true;

            }
        if(startAmPm.getValue().equals("Am" )&&startTime.getValue().equals("1")  ){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please Pick a time within business hours!\nBusiness hours are between 8:00Am - 10:Pm");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();

                return true;
        }
        if(startAmPm.getValue().equals("Am" )&&startTime.getValue().equals("2")  ){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please Pick a time within business hours!\nBusiness hours are between 8:00Am - 10:Pm");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;

        }
        if(startAmPm.getValue().equals("Am" )&&startTime.getValue().equals("3")  ){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please Pick a time within business hours!\nBusiness hours are between 8:00Am - 10:Pm");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();

            return true;
        }
        if(startAmPm.getValue().equals("Am" )&&startTime.getValue().equals("4")  ){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please Pick a time within business hours!\nBusiness hours are between 8:00Am - 10:Pm");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
                return true;

        }
        if(startAmPm.getValue().equals("Am" )&&startTime.getValue().equals("5")  ){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please Pick a time within business hours!\nBusiness hours are between 8:00Am - 10:Pm");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();

                return true;
        }
        if(startAmPm.getValue().equals("Am" )&&startTime.getValue().equals("6")  ){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please Pick a time within business hours!\nBusiness hours are between 8:00Am - 10:Pm");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
                return true;

        }
        if(startAmPm.getValue().equals("Am" )&&startTime.getValue().equals("7")  ){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please Pick a time within business hours!\nBusiness hours are between 8:00Am - 10:Pm");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();

                return true;
        }
        if(startAmPm.getValue().equals("Am" )&&startTime.getValue().equals("12")  ){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please Pick a time within business hours!\nBusiness hours are between 8:00Am - 10:Pm");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
                return true;

        }


        if(userBox.getSelectionModel().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a User");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;


        }
        if(endAmPm.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("You Must select AM or PM.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }

        if(startAmPm.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("You Must select AM or PM.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }

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