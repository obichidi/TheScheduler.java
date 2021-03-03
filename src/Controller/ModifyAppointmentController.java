package Controller;

import Database.AppointmentDatabase;
import Database.CustomerDatabase;
import Model.Appointment;

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

import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.text.ParseException;
import java.time.LocalDate;

import java.util.ResourceBundle;

public class ModifyAppointmentController implements Initializable {

    @FXML private TextField titleModify;
    @FXML private Label tester;

    @FXML private TextArea descriptionModify;
    @FXML private ComboBox<String> startHourModify;
//    @FXML private ComboBox<String> typeModify;
    @FXML private ComboBox<String> startMinuteModify;
    @FXML private ComboBox<String> endHourModify;
    @FXML private ComboBox<String> endMinuteModify;
    @FXML private ComboBox<String> locationModify;
    @FXML private ComboBox<String> contactModify;
    @FXML private Button modifyButton;
    @FXML private Button testerButton;

    @FXML private DatePicker dateModify;
    @FXML private ComboBox<String> customerModify;


    ObservableList<String> startTimes = FXCollections.observableArrayList();
    ObservableList<String> endTimes = FXCollections.observableArrayList();
    ObservableList<String> startsMinutes = FXCollections.observableArrayList();
    ObservableList<String> endsMinutes = FXCollections.observableArrayList();
    ObservableList<String> Locations = FXCollections.observableArrayList();


    private static Appointment appointmentToUpdate;




    @FXML
    void cancel(ActionEvent event) throws ParseException, SQLException, IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
           Stage stage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("/view/MainAppointment.fxml"));
            Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
    }

    @FXML
    void test(ActionEvent event) throws ParseException, SQLException {


//
      // int customerId = CustomerDatabase.findCustomerId(customerName.getValue());
//       String title = titleModify.getText();
//
//        String location = locationModify.getValue();
//        String contact = contactModify.getValue();
//
//        Timestamp start = Time.generateStartTimestamp(dateModify, startHourModify, startMinuteModify, locationModify);
//        Timestamp end = Time.generateEndTimestamp(dateModify, endHourModify, endMinuteModify, locationModify);
////        String overlap = AppointmentDatabase.isAppointmentOverlapping(start, end, customerId, contact, appointmentId);


//        String toka = appointmentToUpdate.getAppointmentStartTime().toString();
//        System.out.println(toka);
    }



    @FXML
    void modifyAppointment(ActionEvent event) throws ParseException, SQLException {

//        Timestamp start = Time.generateStartTimestampModify( dateModify, startHourModify, startMinuteModify, locationModify);
//        Timestamp end = Time.generateEndTimestampModify(dateModify, endHourModify, endMinuteModify, locationModify);

        String description = descriptionModify.getText();
        int customerId = appointmentToUpdate.getCustomerId();
        int appointmentId = appointmentToUpdate.getAppointmentId();
        String title = titleModify.getText();
        String location = locationModify.getValue();
        String contact = contactModify.getValue();
        String startTime = startHourModify.getValue();
        String endTime = endHourModify.getValue();
        LocalDate date =  dateModify.getValue();
//       String type = typeModify.getValue();




        tester.setText("Description: "+ description+ "\n CustomerId " + customerId+ "\n Start: " + startTime
                        + "\n End: " + endTime + "\n Date: " + date
                        + "\n Appointment Id: " + appointmentId + "\n title: " + title
                +"\nContact: " + contact+ "\nLocation: "+ location
                      );
    }




//        String exceptions = "";
//        exceptions += Validations.validateAppointmentCustomer(customerName);
//        exceptions += Validations.validateAppointment(titleComboBox.getValue(), employeeContactBox, locationBox, calendarBox, startTimeHourBox, startTimeMinuteBox, endTimeHourBox, endTimeMinuteBox);
//        if(exceptions.length()!=0){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error.");
//            alert.setContentText(exceptions);
//            alert.initModality(Modality.APPLICATION_MODAL);
//            alert.showAndWait();
//            return;
//        }
//        int appointmentId = appointmentToUpdate.getAppointmentId();
//        ObservableList<Appointment> customerName = AppointmentDatabase.getAllAppointments();
//       int customerId = appointmentToUpdate.getCustomerId();
//
//
//
//
//        Timestamp end = Time.generateEndTimestamp(dateModify, endHourModify, endMinuteModify, locationModify);
//  //      String overlap = AppointmentDatabase.isAppointmentOverlapping(start, end,  contact, appointmentId);
//
//
////        if (start.after(end)){
////            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
////            errorAlert.setHeaderText("Check appointment times");
////            errorAlert.setContentText("Ensure the end time is after the start time.");
////            errorAlert.showAndWait();
////            return;
////        }
////        if (!overlap.isEmpty()){
////            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
////            errorAlert.setHeaderText("Appointment is overlapping");
////            errorAlert.setContentText(overlap);
////            errorAlert.showAndWait();
////            return;
////        }
////
//
//
//
////        try {
////            ((Node) (event.getSource())).getScene().getWindow().hide();
////            Stage stage = new Stage();
////            Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentMain.fxml"));
////            Scene scene = new Scene(root);
////            stage.setScene(scene);
////            stage.show();
////        } catch (IOException ex) {
////            System.out.println("IO Exception: " + ex.getMessage());
////        }
////
////      System.out.println(appointmentToUpdate);
//    }
//
//
//
        @Override
        public void initialize (URL url, ResourceBundle rb){
            appointmentToUpdate = MainAppointmentController.getSelectedAppointment();


//            typeModify.setPromptText(appointmentToUpdate.getAppointmentType());

            startHourModify.setPromptText(appointmentToUpdate.getAppointmentStartTime().toString());

            endHourModify.setPromptText(appointmentToUpdate.getAppointmentEndTime().toString());
            endMinuteModify.setPromptText(appointmentToUpdate.getAppointmentEndTime().toString().substring(2,5));
            startMinuteModify.setPromptText(appointmentToUpdate.getAppointmentStartTime().toString().substring(2,5));


            dateModify.setPromptText(appointmentToUpdate.getAppointmentStartDate().toString());

//            startHourModify.setItems(AppointmentDatabase.);
//            typeModify.setItems(AppointmentDatabase.TypeList());

            descriptionModify.setText(appointmentToUpdate.getAppointmentDescription());

//    starHourModify.setItems();
            customerModify.setItems(CustomerDatabase.CustomerList());

 customerModify.setPromptText(appointmentToUpdate.getCustomerName());

            contactModify.setItems(AppointmentDatabase.ContactList());
            contactModify.setPromptText(appointmentToUpdate.getAppointmentContact());

            locationModify.setItems(AppointmentDatabase.LocationList());
            locationModify.setPromptText(appointmentToUpdate.getAppointmentLocation());

            titleModify.setText(appointmentToUpdate.getAppointmentTitle());

            startTimes.addAll("9 a.m.", "10 a.m.", "11 a.m.", "12 p.m.", "1 p.m.", "2 p.m.", "3 p.m.", "4 p.m.", "5 p.m.");


            endTimes.addAll("9 a.m.", "10 a.m.", "11 a.m.", "12 p.m.", "1 p.m.", "2 p.m.", "3 p.m.", "4 p.m.", "5 p.m.");
        startsMinutes.addAll("00", "15", "30", "45");
        endsMinutes.addAll("00", "15", "30", "45");
        startHourModify.setItems(startTimes);
        endHourModify.setItems(endTimes);
        startMinuteModify.setItems(startsMinutes);
        endMinuteModify.setItems(endsMinutes);
        Locations.addAll("New York, New York", "London, England", "Paris, France");
        locationModify.setItems(Locations);

//            zeroOnly.add("00");
//            hourList.addAll("9 a.m.", "10 a.m.", "11 a.m.", "12 p.m.", "1 p.m.", "2 p.m.", "3 p.m.", "4 p.m.", "5 p.m.");
//            minuteList.addAll("00", "15", "30", "45");
//
//            startTimeHourBox.setItems(hourList);
//            endTimeHourBox.setItems(hourList);
//            startTimeMinuteBox.setItems(minuteList);
//
//            endTimeMinuteBox.setItems(minuteList);
//            officeLocations.addAll("Pheonix, Arizona", "New York, New York", "London, England");
//            locationBox.setItems(officeLocations);
//
//            descriptionText.setText(appointmentToModify.getAppointmentDescription());
//            calendarBox.setValue(DateTimeConverters.calToLocalDate(appointmentToModify.getAppointmentStartTime()));
//            locationBox.setValue(appointmentToModify.getAppointmentLocation());
//            employeeContactBox.setValue(appointmentToModify.getEmployeeContact());
//
//
//            endTimeMinuteBox.setValue(DateTimeConverters.calToStringMin(appointmentToModify.getAppointmentEndTime()));
//            startTimeHourBox.setValue(DateTimeConverters.calToComboBoxHour(appointmentToModify.getAppointmentStartTime(), appointmentToModify.getAppointmentLocation()));
//            endTimeHourBox.setValue(DateTimeConverters.calToComboBoxHour(appointmentToModify.getAppointmentEndTime(), appointmentToModify.getAppointmentLocation()));
//            customerName.setValue(appointmentToModify.getCustomerName());


        }
}





