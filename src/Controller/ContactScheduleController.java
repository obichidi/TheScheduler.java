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



/**
 * this class implements the ContactScheduleController for the ContactSchedule fxml
 *
 */
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
    @FXML private ComboBox<String> getAppointmentMonth;
    @FXML private ComboBox<String> appointmentMonth;
    @FXML private ComboBox<String> appointmentType;
    @FXML private TableView<Appointment> contactTable;
    @FXML private Label appointMonthText;

    @FXML private Button back;

    @FXML private Button printAppointmentByMonth;
    @FXML private Button printAppointmentByType;

    static String selectedContact;
    static Integer selectedMonth;
    static String selectedType;


    /**This is the constructor for ContactScheduleController */
ContactScheduleController(){}

    /**
     * This changes the scene back to the reports fxml
     * @param event  this is an event driven function
     */
    @FXML
   public void back(ActionEvent event) {

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


    /**
     * this function gets the selected contacts appointments and sets them in the in the contact table
     * @param event  this is an event driven function
     *
     */
    @FXML
   public void getContactsAppointments(ActionEvent event) {
        selectedContact = contactNameBox.getValue();

        try {
            contactTable.getItems().clear();
            contactTable.setItems(AppointmentDatabase.getAllContactAppointments(selectedContact));


        } catch (ParseException | SQLException ex) {
            Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }








    /**
     * this function gets the contact appointment data by type
     * @param event  this is an event driven function
     *
     */
    @FXML
   public void getContactAppointmentByType(ActionEvent event) {
        contactTable.getItems().clear();
        selectedType = appointmentType.getValue();

        try {
            contactTable.getItems().clear();
            contactTable.setItems(AppointmentDatabase.getAllContactAppointmentsByType(selectedType,selectedContact));


        } catch (ParseException | SQLException ex) {
            Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * this function gets the contact appointment data by Month
     * @param event  this is an event driven function
     *
     */
    @FXML
   public void getAllAppointmentContactsByMonth(ActionEvent event) {
        try {
            contactTable.getItems().clear();
            contactTable.setItems(AppointmentDatabase.getContactsMonthlyAppointments(appointmentMonth.getSelectionModel().getSelectedIndex(), selectedContact));
        } catch (ParseException ex) {
            Logger.getLogger(ContactScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    /**
     * this function initializes the settings for the
     *  ContactSchedule controller fxml
     * @param url  URL
     * @param rb  Resource Bundle
     *
     */

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


    /**
     * this function  prints the report of the selected appointments by  month to a text file
     * @param event  this i s an event driven function
     * @throws ParseException throws a parse exception
     */
    @FXML
  public  void printAppointmentByMonth(ActionEvent event) throws ParseException {





        Date reportTime = Calendar.getInstance().getTime();

        File reportsByMonthFile = new File("ReportsByMonth.txt");
        if(!reportsByMonthFile.exists()){
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("ReportsByMonth.txt"), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime +  contactNameBox.getValue() );
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
        else {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("ReportsByMonth.txt", true), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\r\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
    }


    /**
     * this function  prints the report of the selected appointments by type to a text file
     * @param event  this is an event driven function
     *
     */
    @FXML
   public void printAppointmentByType(ActionEvent event) {

        Date reportTime = Calendar.getInstance().getTime();

        File reportsByMonthFile = new File(" ReportsByType.txt");
        if(!reportsByMonthFile.exists()){
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("ReportsByType.txt"), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime +  "\r\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }
        else {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("ReportsByType.txt", true), "utf-8"))) {
                writer.write(currentUser.getUsername() + " Generated This report on :" +
                        reportTime + "\r\n");
            } catch (IOException ex) {
                System.out.println("IOEception: " + ex);
            }
        }

    }
    }

