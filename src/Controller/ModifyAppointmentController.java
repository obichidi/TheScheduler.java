package Controller;

import Database.AppointmentDatabase;
import Database.CustomerDatabase;
import Model.Appointment;

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

public class ModifyAppointmentController implements Initializable {

    @FXML private TextField titleModify;
    @FXML private Label tester;

    @FXML private TextArea descriptionModify;
    @FXML private ComboBox<String> startTimeModify;
    @FXML private ComboBox<String> typeModify;
    @FXML private ComboBox<String> startMinuteModify;
    @FXML private ComboBox<String> endTimeModify;
    @FXML private ComboBox<String> endMinuteModify;
    @FXML private ComboBox<String> locationModify;
    @FXML private ComboBox<String> contactModify;
    @FXML private Button modifyButton;
    @FXML private Button testerButton;

    @FXML private DatePicker datePickerModify;
    @FXML private ComboBox<String> customerModify;


    ObservableList<String> startTimes = FXCollections.observableArrayList();
    ObservableList<String> endTimes = FXCollections.observableArrayList();
    ObservableList<String> startsMinutes = FXCollections.observableArrayList();
    ObservableList<String> endsMinutes = FXCollections.observableArrayList();
    ObservableList<String> Locations = FXCollections.observableArrayList();


    private static Appointment appointmentToUpdate;




    @FXML
    void cancel(ActionEvent event) throws  IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
           Stage stage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("/view/MainAppointment.fxml"));
            Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
    }

    @FXML
    void test(ActionEvent event) throws ParseException, SQLException {



    }



    @FXML
    void modifyAppointment(ActionEvent event) throws ParseException, SQLException {

       errorChecks();

        String description = descriptionModify.getText();
        String customerName = customerModify.getValue();
        String type = typeModify.getValue();
        int customerId = appointmentToUpdate.getCustomerId();
        int appointmentId = appointmentToUpdate.getAppointmentId();
        String title = titleModify.getText();
        String location = locationModify.getValue();
        String contact = contactModify.getValue();
        String startTimes = startTimeModify.getValue();
        String endTimes = endTimeModify.getValue();
        LocalDate date =  datePickerModify.getValue();
        Timestamp start = Time.generateStartTimestampModify( datePickerModify, startTimeModify, startMinuteModify, locationModify);
        Timestamp end = Time.generateEndTimestampModify(datePickerModify, endTimeModify, endMinuteModify, locationModify);
        String overlap = AppointmentDatabase.OverlappedAppointment(start, end, customerId, contact, appointmentId);


        if (start.after(end)){
          Alert errorAlert = new Alert(Alert.AlertType.ERROR);
          errorAlert.setHeaderText("Check appointment times");
          errorAlert.setContentText("The End Time  Must be after the start time.");
          errorAlert.showAndWait();
           return;
      }

        if (!overlap.isEmpty()){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Appointment is overlapping");
            errorAlert.setContentText(overlap);
            errorAlert.showAndWait();
            return;
        }

        AppointmentDatabase.modifyAppointment( appointmentId, customerId, type, customerName, title, location,
                 description, contact,  start, end);
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

        tester.setText("Description: "+ description+ "\n CustomerId " + customerId+ "\n Start: " + start
                        + "\n End: " + end + "\n Date: " + date +"\n Type: " + type
                        + "\n Appointment Id: " + appointmentId + "\n title: " + title + "\n Customer Name: " + customerName
                +"\nContact: " + contact+ "\nLocation: "+ location
                      );
    }





        @Override
        public void initialize (URL url, ResourceBundle rb){
            appointmentToUpdate = MainAppointmentController.getSelectedAppointment();


            typeModify.setPromptText(appointmentToUpdate.getAppointmentType());
            typeModify.setValue(typeModify.getPromptText());


            startTimeModify.setPromptText(appointmentToUpdate.getAppointmentStartTime().toString());
            startMinuteModify.setPromptText(appointmentToUpdate.getAppointmentStartTime().toString().substring(3,5));
//            startTimeModify.setValue(startMinuteModify.getPromptText());

            endTimeModify.setPromptText(appointmentToUpdate.getAppointmentEndTime().toString());
            endMinuteModify.setPromptText(appointmentToUpdate.getAppointmentEndTime().toString().substring(3,5));



           datePickerModify.setPromptText(appointmentToUpdate.getAppointmentStartDate().toString());
           datePickerModify.setValue(LocalDate.parse(appointmentToUpdate.getAppointmentStartDate().toString()));
//            startHourModify.setItems(AppointmentDatabase.);
            typeModify.setItems(AppointmentDatabase.TypeList());
            typeModify.setValue(typeModify.getPromptText());

            descriptionModify.setText(appointmentToUpdate.getAppointmentDescription());

//    starHourModify.setItems();
            customerModify.setItems(CustomerDatabase.CustomerList());
            customerModify.setPromptText(appointmentToUpdate.getCustomerName());
            customerModify.setValue(customerModify.getPromptText());

            contactModify.setItems(AppointmentDatabase.ContactList());
            contactModify.setPromptText(appointmentToUpdate.getAppointmentContact());

            locationModify.setItems(AppointmentDatabase.LocationList());
            locationModify.setPromptText(appointmentToUpdate.getAppointmentLocation());
            locationModify.setValue(locationModify.getPromptText());

            titleModify.setText(appointmentToUpdate.getAppointmentTitle());

            startTimes.addAll("9 a.m.", "10 a.m.", "11 a.m.", "12 p.m.", "1 p.m.", "2 p.m.", "3 p.m.", "4 p.m.", "5 p.m.");
            endTimes.addAll("9 a.m.", "10 a.m.", "11 a.m.", "12 p.m.", "1 p.m.", "2 p.m.", "3 p.m.", "4 p.m.", "5 p.m.");

            startsMinutes.addAll("00", "15", "30", "45");
            endsMinutes.addAll("00", "15", "30", "45");

        startTimeModify.setItems(startTimes);
        endTimeModify.setItems(endTimes);
        startMinuteModify.setItems(startsMinutes);
        endMinuteModify.setItems(endsMinutes);
        Locations.addAll("New York, New York", "London, England", "Paris, France");
        locationModify.setItems(Locations);



        }

    public void errorChecks(){


        if(titleModify.getText().isEmpty()){
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a TITLE.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }

        if(contactModify.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a CONTACT.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }

        if(locationModify.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a LOCATION.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }

        if(datePickerModify.getValue() == null){

             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a date.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();

            return;
        }
        if(startTimeModify.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a Start Hour.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();


            return;
        }

        if(startMinuteModify.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a start minute.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }
        if(endTimeModify.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select an End Hour.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }
        if(endMinuteModify.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select an End Minute.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }
        if(typeModify.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a TYPE.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }

        if(customerModify.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a CUSTOMER.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }

        if(descriptionModify.getText().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a DESCRIPTION.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }


    }
}





