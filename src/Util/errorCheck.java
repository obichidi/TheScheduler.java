package Util;

import Model.Appointment;
import Model.Contact;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class errorCheck {

    public static String errorCheckCustomer(String name, String address, String postalCode, String phone, ComboBox boxToValidate){
        String validationExceptions = "";
        if(name.isEmpty()) validationExceptions += "Please enter a customer name.\n";
        if(address.isEmpty()) validationExceptions += "Customer address can not be empty.\n";
        if(postalCode.isEmpty()) validationExceptions += "Please enter a vaild postal code.\n";
        if(phone.length()<10||phone.length()>15) validationExceptions += "Please enter a valid phone number including area code.\n";
        if(boxToValidate.getSelectionModel().isEmpty()) validationExceptions += "Please select a city from the drop down menu.\n";
        return validationExceptions;
    }

    public static boolean errorCheckUsername(String username) {
        if (username.length() > 2 && username.length() < 30) {
            return true;
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Username not valid.");
            errorAlert.setContentText("Please enter a valid username between 2 and 30 characters and try again.");
            errorAlert.showAndWait();
            return false;
        }
    }

    public static String errorCheckAppointment(TextField appointmentTitleAdd, ComboBox<String> contactAdd, ComboBox<String> locationAdd, DatePicker datePicker, ComboBox<String> startTime, ComboBox<String> startMinutes, ComboBox<String> endTime, ComboBox<String> endMinutes){
        String errorExceptions = "";
        if(appointmentTitleAdd == null) errorExceptions += "Please select a title or write one in.\n";
        if(contactAdd.getSelectionModel().isEmpty()) errorExceptions += "Please select an employee contact.\n";
        if(locationAdd.getSelectionModel().isEmpty()) errorExceptions += "Please select an appointment location.\n";
        if(datePicker.getValue() == null) errorExceptions += "Please select a date for the appointment.\n";
        if(startTime.getSelectionModel().isEmpty()) errorExceptions += "Please select a start time (hour) for the appointment.\n";
        if(endTime.getSelectionModel().isEmpty()) errorExceptions += "Please select a end time (hour) for the appointment.\n";
        if(startMinutes.getSelectionModel().isEmpty()) errorExceptions += "Please select a star time (minutes) for the appointment.\n";
        if(endMinutes.getSelectionModel().isEmpty()) errorExceptions += "Please select a end time (minutes) for the appointment.\n";

        return  errorExceptions;
    }
    public static String errorCheckAppointmentCustomer(ComboBox<String> customerName){
        String validationExceptions = "";
        if(customerName.getSelectionModel().isEmpty()) validationExceptions += "Please select a customer.\n";
        return validationExceptions;
    }

}
