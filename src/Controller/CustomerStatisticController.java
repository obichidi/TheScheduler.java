package Controller;

import Database.AppointmentDatabase;
import Database.CustomerDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

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

    }

    @FXML
    void printReportByMonth(ActionEvent event) {

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

    }

    @FXML
    void showCustomerMonth(ActionEvent event) throws ParseException {


            try{

                String selectCustomerName = customerNameBox.getValue();


              appointmentMonthText.setText(String.valueOf(CustomerDatabase. getAllAppointmentCountByMonthCustomer(selectCustomerName, customerAppointmentMonth.getSelectionModel().getSelectedIndex())));
            } catch (ParseException ex) {
                Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            }

//
//
//        appointmentMonthText.setText(CustomerDatabase.getAllAppointmentCountByMonthCustomer(customerAppointmentMonth.getSelectionModel().getSelectedIndex()));


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

    @FXML
    void test(ActionEvent event) throws ParseException {




    }




}
