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
    @FXML private ComboBox<String> starHourModify;
    @FXML private ComboBox<String> startMinuteModify;
    @FXML private ComboBox<String> endHourModify;
    @FXML private ComboBox<String> endMinuteModify;
    @FXML private ComboBox<String> locationModify;
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

    }

}