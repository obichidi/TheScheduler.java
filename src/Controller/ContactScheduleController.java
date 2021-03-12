package Controller;

import Database.AppointmentDatabase;

import Model.Appointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Model.User.currentUser;



public class ContactScheduleController implements Initializable {

    @FXML private TableColumn<?, ?> appointmentIdColumn;
    @FXML private TableColumn<?, ?> titleColumn;
    @FXML private TableColumn<?, ?> typeColumn;
    @FXML private TableColumn<?, ?> descriptionColumn;
    @FXML private TableColumn<?, ?> dateColumn;
    @FXML private TableColumn<?, ?> startTimeColumn;
    @FXML private TableColumn<?, ?> endTimeColumn;
    @FXML private TableColumn<?, ?> customerId;
    @FXML private ComboBox<String> contactNameBox;
    @FXML private ComboBox<String> appointmentMonth;
    @FXML private ComboBox<String> appointmentType;
    @FXML private TableView<Appointment> contactTable;

    @FXML private RadioButton refresh;
    @FXML private Button back;

    @FXML private Button printAppointmentByMonth;
    @FXML private Button printAppointmentByType;
 //   ObservableList<String> month = FXCollections.observableArrayList();
    static String selectedContact;
    static Integer selectedMonth;
    static String selectedType;





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
    void getContactsAppointments(ActionEvent event) {
        selectedContact = contactNameBox.getValue();

        try {
            contactTable.getItems().clear();
            contactTable.setItems(AppointmentDatabase.getAllContactAppointments(selectedContact));


        } catch (ParseException | SQLException ex) {
            Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void refresh(ActionEvent event) {
        contactTable.getItems().clear();
    }

    @FXML
    void getContactAppointmentByType(ActionEvent event) {
        contactTable.getItems().clear();
        selectedType = appointmentType.getValue();

        try {
            contactTable.getItems().clear();
            contactTable.setItems(AppointmentDatabase.getAllContactAppointmentsByType(selectedType,selectedContact));


        } catch (ParseException | SQLException ex) {
            Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }



    }


    @FXML
    void getAllAppointmentContactsByMonth(ActionEvent event) {
        try {
            contactTable.getItems().clear();
            contactTable.setItems(AppointmentDatabase.getContactsMonthlyAppointments(appointmentMonth.getSelectionModel().getSelectedIndex(), selectedContact));
        } catch (ParseException ex) {
            Logger.getLogger(ContactScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }






    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> month = FXCollections.observableArrayList();
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentStartDate"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentStartTime"));
        contactNameBox.setItems(AppointmentDatabase.ContactList());
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentEndTime"));
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentType.setItems(AppointmentDatabase.TypeList());

        month.addAll("January", "February", "March", "April", "May" ,"June" ,"July" ,"August" ,"September" ,"October" ,"November" ,"December");

        appointmentMonth.setItems(month);
        Calendar now = Calendar.getInstance();
        appointmentMonth.getSelectionModel().select(now.get(Calendar.MONTH));

    }



    @FXML
    void printAppointmentByMonth(ActionEvent event) {
        Date reportTime = Calendar.getInstance().getTime();

        File reportsByMonthFile = new File(" ReportsByMonth.txt");
        if(!reportsByMonthFile.exists()){
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" ReportsByMonth.txt"), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime +  "\r\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
        else {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" ReportsByMonth.txt", true), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\r\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
    }




    @FXML
    void printAppointmentByType(ActionEvent event) {

        Date reportTime = Calendar.getInstance().getTime();

        File reportsByMonthFile = new File(" ReportsByType.txt");
        if(!reportsByMonthFile.exists()){
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" ReportsByType.txt"), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime +  "\r\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
        else {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(" ReportsByMonth.txt", true), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\r\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }

    }
    }

