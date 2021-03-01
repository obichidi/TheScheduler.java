package Controller;

import Model.Appointment;
import Model.Contact;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyAppointmentController implements Initializable {

    @FXML private TextField titleModify;
    @FXML private Label test;
    @FXML private ComboBox<Appointment> starHourModify;
    @FXML private ComboBox<Appointment> startMinuteModify;
    @FXML private ComboBox<Appointment> endHourModify;
    @FXML private ComboBox<Appointment> endMinuteModify;
    @FXML private ComboBox<Appointment> locationModify;
    @FXML private ComboBox<Contact> contactModify;
    @FXML private Button modifyButton;
    @FXML private DatePicker dateModify;
    @FXML private ComboBox<Customer> customerModify;


    ObservableList<String> startTimes = FXCollections.observableArrayList();
    ObservableList<String> endTimes = FXCollections.observableArrayList();
    ObservableList<String> startsMinutes = FXCollections.observableArrayList();
    ObservableList<String> endsMinutes = FXCollections.observableArrayList();
    ObservableList<String> Locations = FXCollections.observableArrayList();
    ObservableList<String> zero = FXCollections.observableArrayList();
    private static Appointment appointmentToUpdate;

    @FXML
    void modifyAppointment(ActionEvent event) {
      appointmentToUpdate = MainAppointmentController.getSelectedAppointment();
      System.out.println(appointmentToUpdate);
    }







@Override
public void initialize(URL url, ResourceBundle rb) {}
}




