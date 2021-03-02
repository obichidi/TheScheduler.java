package Controller;

import Database.AppointmentDatabase;
import Database.CustomerDatabase;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
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
    @FXML private ComboBox<String> starHourModify;
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
    ObservableList<String> zero = FXCollections.observableArrayList();
    private static Appointment appointmentToUpdate;


    @FXML
    void test(ActionEvent event) throws ParseException, SQLException {

       int appointmentId = appointmentToUpdate.getAppointmentId();
//        ObservableList<Appointment> customerName = AppointmentDatabase.getAllAppointments();
//        int customerId = appointmentToUpdate.getCustomerId();

//        String title = titleModify.getText();
//        String description = descriptionModify.getText();
//        String location = locationModify.getValue();
//        String contact = contactModify.getValue();
//        String type = typeModify.getValue();
//
//        Timestamp start = Time.generateStartTimestamp(dateModify, starHourModify, startMinuteModify, locationModify);
//        Timestamp end = Time.generateEndTimestamp(dateModify, endHourModify, endMinuteModify, locationModify);
//        tester.setText(title + location + type + description + contact + start + end);

    }

    @FXML
    private void showWeeklyAppointments(ActionEvent event){
        int appointmentId = appointmentToUpdate.getAppointmentId();
        System.out.println(appointmentId);
    }

    @FXML
    void modifyAppointment(ActionEvent event) throws ParseException, SQLException {
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
//        String title = titleModify.getText();
//        String description = descriptionModify.getText();
//        String location = locationModify.getValue();
//        String contact = contactModify.getValue();
//        String type = typeModify.getValue();
//
//        Timestamp start = Time.generateStartTimestamp(dateModify, starHourModify, startMinuteModify, locationModify);
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


            descriptionModify.setText(appointmentToUpdate.getAppointmentDescription());

//    starHourModify.setItems();
            customerModify.setItems(CustomerDatabase.CustomerList());
//  customerModify.setPromptText(appointmentToUpdate.);

            contactModify.setItems(AppointmentDatabase.ContactList());
            contactModify.setPromptText(appointmentToUpdate.getAppointmentContact());

            locationModify.setItems(AppointmentDatabase.LocationList());
            locationModify.setPromptText(appointmentToUpdate.getAppointmentLocation());

            titleModify.setText(appointmentToUpdate.getAppointmentTitle());


        }
}





