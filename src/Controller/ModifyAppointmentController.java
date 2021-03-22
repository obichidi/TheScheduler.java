package Controller;

import Database.AppointmentDatabase;
import Database.CustomerDatabase;
import Database.UserDatabase;
import Model.Appointment;

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
 * this class implements the modify appointment controller for the   Modify appointment  fxml
 * initializes the settings of the MainMenu fxml
 */
public class ModifyAppointmentController implements Initializable {

    @FXML private TextField titleModify;
    @FXML private Label tester;

    @FXML private TextArea descriptionModify;
    @FXML private ComboBox<String> startTime;
    @FXML private ComboBox<String> userNameModify;
    @FXML private ComboBox<String> endAmPm;
    @FXML private ComboBox<String> startAmPm;
    @FXML private ComboBox<String> typeModify;
    @FXML private ComboBox<String> startMinute;
    @FXML private ComboBox<String> endTime;
    @FXML private ComboBox<String> endMinute;
    @FXML private ComboBox<String> locationAdd;
    @FXML private ComboBox<String> contactModify;
    @FXML private Button modifyButton;
    @FXML private Button testerButton;
    @FXML private Button test;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> customerModify;

    ObservableList<String> amPm = FXCollections.observableArrayList();
    ObservableList<String> startTimes = FXCollections.observableArrayList();
    ObservableList<String> endTimes = FXCollections.observableArrayList();
    ObservableList<String> startsMinutes = FXCollections.observableArrayList();
    ObservableList<String> endsMinutes = FXCollections.observableArrayList();
    ObservableList<String> Locations = FXCollections.observableArrayList();


    private static Appointment appointmentToUpdate;
/**This is the constructor for the Modify Appointment controller*/
public ModifyAppointmentController(){}
    /**
     * this cancels the appointment scheduling and changes the scene to the main menu fxml
     * @param event  this is an event driven function
     * @throws IOException throws an io exception
     */

    @FXML
   public void cancel(ActionEvent event) throws  IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainAppointment.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    /**
     * This function Inserts new  appointment data in the database via inputted changes from the ModifyAppointment fxml
     * @param event  this is an event driven function
     */
    @FXML
     public void modifyAppointment(ActionEvent event) {

        if(errorChecks() == false) {

            String description = descriptionModify.getText();
            String customerName = customerModify.getValue();
            String type = typeModify.getValue();
            int customerId = appointmentToUpdate.getCustomerId();
            int appointmentId = appointmentToUpdate.getAppointmentId();
            String title = titleModify.getText();
            String username = userNameModify.getValue();
            int userId = UserDatabase.findUserId(username);
            String location = locationAdd.getValue();
            String contact = contactModify.getValue();
            String startTimes = startTime.getValue();
            String endTimes = endTime.getValue();
            LocalDate date = datePicker.getValue();
            Timestamp start = Time.generateStartTimestamp(datePicker, startTime,  startMinute,  locationAdd, startAmPm);
            Timestamp end = Time.generateEndTimestamp(datePicker, endTime,  endMinute,  locationAdd, endAmPm);
            String overlap = AppointmentDatabase.OverlappedAppointment(start, end, customerId, contact, appointmentId);

            int b3 = start.compareTo(end);
            if (b3 == 0) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Check appointment times");
                errorAlert.setContentText("The End Time  Must be after the start time.");
                errorAlert.showAndWait();
                return;
            }

            if (!overlap.isEmpty()) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Appointment is overlapping");
                errorAlert.setContentText(overlap);
                errorAlert.showAndWait();
                return;
            }

            AppointmentDatabase.modifyAppointment(appointmentId, customerId, type, customerName, userId, title, location,
                    description, contact, start, end);
            try {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/MainAppointment.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println("IO Exception: " + ex.getMessage());
            }

        }
    }

    /**
     * This function initializes the settings of the ModifyAppointment fxml
     */


    @Override
    public void initialize (URL url, ResourceBundle rb){
        appointmentToUpdate = MainAppointmentController.getSelectedAppointment();


        endAmPm.setItems(amPm);

        String endLength = appointmentToUpdate.getAppointmentEndTime() + " ";
        String endLength2 = endLength.substring(5,8).replace(":", " ");

        String startLength = appointmentToUpdate.getAppointmentStartTime() + " ";
       String startLength2 =   startLength.substring(5,8).replace(":", " ");
        endAmPm.setPromptText(endLength2);


        startAmPm.setItems(amPm);
        startAmPm.setPromptText(startLength2);
        amPm.addAll("Am","Pm");
        typeModify.setPromptText(appointmentToUpdate.getAppointmentType());
        typeModify.setValue(typeModify.getPromptText());




String falcon = appointmentToUpdate.getAppointmentStartTime();
String donkey = appointmentToUpdate.getAppointmentLocation();



        startTime.setPromptText(appointmentToUpdate.getAppointmentStartTime().substring(0,2).replace(":", " "));
        startMinute.setPromptText(appointmentToUpdate.getAppointmentStartTime().substring(2,5).replace(":", " "));







        endTime.setPromptText(appointmentToUpdate.getAppointmentEndTime().toString().substring(0,2).replace(":", " "));
        endMinute.setPromptText(appointmentToUpdate.getAppointmentEndTime().toString().substring(2,5).replace(":", " "));
        userNameModify.setItems(UserDatabase.userList());
        userNameModify.setPromptText(User.currentUser.getUsername());
        datePicker.setPromptText(appointmentToUpdate.getAppointmentStartDate());
        typeModify.setItems(AppointmentDatabase.TypeList());


        descriptionModify.setText(appointmentToUpdate.getAppointmentDescription());


        customerModify.setItems(CustomerDatabase.CustomerList());
        customerModify.setPromptText(appointmentToUpdate.getCustomerName());
        customerModify.setValue(customerModify.getPromptText());

        contactModify.setItems(AppointmentDatabase.ContactList());
        contactModify.setPromptText(appointmentToUpdate.getAppointmentContact());
        contactModify.setValue(appointmentToUpdate.getAppointmentContact());

        locationAdd.setItems(AppointmentDatabase.LocationList());
        locationAdd.setPromptText(appointmentToUpdate.getAppointmentLocation());
        locationAdd.setValue(locationAdd.getPromptText());

        titleModify.setText(appointmentToUpdate.getAppointmentTitle());

        startTimes.addAll("8","9", "10", "11", "12", "1", "2", "3", "4", "5","6","7","8","9");
        endTimes.addAll("8","9", "10", "11", "12", "1", "2", "3", "4", "5","6","7","8","9");

        startsMinutes.addAll("00", "15", "30", "45");
        endsMinutes.addAll("00", "15", "30", "45");

        startTime.setItems(startTimes);
        endTime.setItems(endTimes);
        startMinute.setItems(startsMinutes);
        endMinute.setItems(endsMinutes);
        Locations.addAll("New York, New York", "London, England", "Paris, France");
        locationAdd.setItems(Locations);




    }













    /**
     * Thi function checks for errors in the inputted data from  the modify appointment fxml
     * @return   returns a boolean of true opr false if there is an error or not.
     */
    public boolean errorChecks(){

        if(userNameModify.getSelectionModel().isEmpty()){

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



        if(userNameModify.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a User.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;

        }
        if(titleModify.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a TITLE.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }

        if(contactModify.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a CONTACT.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }

        if(locationAdd.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a LOCATION.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }

        if(datePicker.getValue() == null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a date.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();

            return true;
        }
        if(startTime.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a Start Hour.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();


            return true;
        }

        if(startMinute.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a start minute.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }
        if(endTime.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select an End Hour.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }
        if(endMinute.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select an End Minute.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }
        if(typeModify.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a TYPE.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }

        if(customerModify.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a CUSTOMER.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }

        if(descriptionModify.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a DESCRIPTION.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }

return false;
    }
}



