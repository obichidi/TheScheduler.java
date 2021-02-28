package Controller;

import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCustomerController implements Initializable {
    @FXML private TextField titleModify;
    @FXML private Label test;
    @FXML private ComboBox<Appointment> starHourModify;
    @FXML private ComboBox<Appointment> startMinuteModify;
    @FXML private ComboBox<Appointment> endHourModify;
    @FXML private ComboBox<Appointment> endMinuteModify;
    @FXML private ComboBox<Appointment> locationModify;
    @FXML private ComboBox<Appointment> contactModify;
    @FXML private Button modifyButton;
    @FXML private DatePicker dateModify;
    @FXML private ComboBox<Appointment> customerModify;

    private final ObservableList<Appointment>  selectedAppointment = FXCollections.observableArrayList();

//    selectedAppointment =

    @FXML
    void modifyAppointment(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(MainAppointmentController.selectAppointment);
    }

}