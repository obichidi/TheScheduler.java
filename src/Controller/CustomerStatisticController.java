package Controller;

import Database.AppointmentDatabase;
import Database.CustomerDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Model.User.currentUser;

public class CustomerStatisticController implements Initializable {


    @FXML private ComboBox<String> customerNameBox;
    @FXML private ComboBox<String> customerAppointmentType;
    @FXML private ComboBox<String> customerAppointmentMonth;
    @FXML private RadioButton refresh;
    @FXML private Label customerNameText;
    @FXML private Label appointmentTypeText;
    @FXML private Label appointmentMonthText;


    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> month = FXCollections.observableArrayList();
        customerNameBox.setItems(CustomerDatabase.CustomerList());
        customerAppointmentType.setItems(AppointmentDatabase.TypeList());
        customerAppointmentMonth.setItems(month);

        month.addAll("January", "February", "March", "April", "May" ,"June" ,"July" ,"August" ,"September" ,"October" ,"November" ,"December");
        Calendar now = Calendar.getInstance();
       customerAppointmentMonth.getSelectionModel().select(now.get(Calendar.MONTH));


    }

    @FXML
    void back(ActionEvent event) {

        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void printReportByMonth(ActionEvent event) {
        Date reportTime = Calendar.getInstance().getTime();
        String selectCustomerName = customerNameBox.getValue();
        String selectedMonth = customerAppointmentMonth.getValue();



        File reportsByMonthFile = new File(" AppointmentsByMonthFor_Customer.txt");
        if(!reportsByMonthFile.exists()){
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" AppointmentsByMonthFor_Customer.txt"), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\n" +
                        "Report:\n" +
                        "Number of Appointments for :  " + selectCustomerName +" : By Month :  "+ selectedMonth + " : " +appointmentMonthText.getText() + "\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
        else {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" AppointmentsByMonthFor_Customer.txt", true), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\n" +
                        "Report:\n" +
                        "Number of Appointments for :  " + selectCustomerName +" : By Month :  "+ selectedMonth + " : " +appointmentMonthText.getText() + "\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }

    }

    @FXML
    void printReportByType(ActionEvent event) {
        Date reportTime = Calendar.getInstance().getTime();
        String selectCustomerName = customerNameBox.getValue();

        File reportsByMonthFile = new File(" AppointmentsByTypeFor_Customer.txt");
        if(!reportsByMonthFile.exists()){
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" AppointmentsByTypeFor_Customer.txt"), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\n" +
                        "Report:\n" +
                        "Number of Appointments for :  " + selectCustomerName +" : By Type :  "+ customerAppointmentType.getValue() + " : " +appointmentTypeText.getText() + "\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
        else {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" AppointmentsByTypeFor_Customer.txt", true), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\n" +
                        "Report:\n" +
                        "Number of Appointments for :  " + selectCustomerName +" : By Type :  "+ customerAppointmentType.getValue() + " : " +appointmentTypeText.getText() + "\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }

    }

    @FXML
    void refresh(ActionEvent event) {
        customerNameBox.getSelectionModel().clearSelection();
        customerNameBox.setPromptText("Customer Name");
         customerAppointmentType.getSelectionModel().clearSelection();
        customerAppointmentType.setPromptText("Appointment Type");
        customerAppointmentMonth.getSelectionModel().clearSelection();
        customerAppointmentMonth.setPromptText("Appointment Month");
    }

    @FXML
    void showCustomerMonth(ActionEvent event) throws ParseException {
        String selectCustomerName = customerNameBox.getValue();
            int selectMonth = customerAppointmentMonth.getSelectionModel().getSelectedIndex();
        if (selectCustomerName != null) {
            try {
                appointmentMonthText.setText(String.valueOf(CustomerDatabase.getAllAppointmentCountByMonthCustomer(selectCustomerName, selectMonth)));

            } catch (ParseException ex) {
                Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }



    @FXML
    void printAllAppointments(ActionEvent event) {
        Date reportTime = Calendar.getInstance().getTime();
        String selectCustomerName = customerNameBox.getValue();

        File reportsByMonthFile = new File(" All_AppointmentsFor_Customer.txt");
        if(!reportsByMonthFile.exists()){
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" All_AppointmentsFor_Customer.txt"), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime +  "\r\n" +
                        "Report:\n" +
                        "Number of Appointments for : "+ " : " + selectCustomerName + customerNameText.getText()+ "\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
        else {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" All_AppointmentsFor_Customer.txt", true), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\n" +
                        "Report:\n" +
                        "Number of Appointments for :  " + selectCustomerName +" : "+ customerNameText.getText()+ "\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
    }

    @FXML
    void showCustomerName(ActionEvent event) throws ParseException {
       String selectCustomerName = customerNameBox.getValue();
       customerNameText.setText(String.valueOf(CustomerDatabase.getAllAppointmentCountForCustomer(selectCustomerName)));

    }

    @FXML
    void showCustomerType(ActionEvent event) throws ParseException {

        String selectCustomerName = customerNameBox.getValue();
        String selectType = customerAppointmentType.getValue();
        appointmentTypeText.setText(String.valueOf(CustomerDatabase. getAllAppointmentCountByTypeCustomer(selectCustomerName, selectType)));
    }

}
