package Controller;

import Database.CustomerDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class CustomerStatisticController implements Initializable {


    @FXML private ComboBox<String> customerNameBox;
    @FXML private ComboBox<?> customerAppointmentType;
    @FXML private ComboBox<?> customerAppointmentMonth;
    @FXML private RadioButton refresh;
    @FXML private Label customerNameText;
    @FXML private Label appointmentTypeText;
    @FXML private Label appointmentMonthText;


    @Override
    public void initialize(URL url, ResourceBundle rb){
        customerNameBox.setItems(CustomerDatabase.CustomerList());
    }

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void printReportByMonth(ActionEvent event) {

    }

    @FXML
    void printReportByType(ActionEvent event) {

    }

    @FXML
    void refresh(ActionEvent event) {

    }

    @FXML
    void showCustomerMonth(ActionEvent event) {

    }

    @FXML
    void showCustomerName(ActionEvent event) throws ParseException {
       String selectCustomerName = customerNameBox.getValue();
       customerNameText.setText(String.valueOf(CustomerDatabase.getAllAppointmentCountForCustomer(selectCustomerName)));

    }

    @FXML
    void showCustomerType(ActionEvent event) {

    }
}
