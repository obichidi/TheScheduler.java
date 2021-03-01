package Controller;

import Database.AppointmentDatabase;
import Model.Appointment;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainAppointmentController implements Initializable {

    public TableView appointmentTable;


    @FXML private Button exit;


    @FXML private TableView<Appointment> AppointmentTable;
    @FXML private ComboBox<String> pickMonth;
    @FXML private TableColumn<Appointment, Integer> appointmentId;
    @FXML private TableColumn<Appointment, String> appointmentType;
    @FXML private TableColumn<Appointment, String> appointmentTitle;
    @FXML private TableColumn<Appointment, String> appointmentLocation;
    @FXML private TableColumn<Appointment, String> appointmentDescription;
    @FXML private TableColumn<Appointment, Integer> customerId;
    @FXML private TableColumn<Appointment, String> appointmentContact;
    @FXML private TableColumn<Appointment, LocalDate> appointmentStartDate;
    @FXML private TableColumn<Appointment, LocalTime> appointmentStartTime;
    @FXML private TableColumn<Appointment, LocalTime> appointmentEndTime;
    @FXML private Button addAppointmentButton;
    @FXML private Button modifyAppointmentButton;
    @FXML private Button deleteAppointment;
    @FXML private RadioButton showMonthly;
    @FXML private RadioButton showWeekly;
    @FXML private RadioButton allAppointments;

    static Appointment selectAppointment;

    ObservableList<String> months = FXCollections.observableArrayList();
    private final ObservableList<Appointment>  refreshAppointments = FXCollections.observableArrayList();






    @FXML
    void addAppointment(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void exit(ActionEvent event) throws IOException {


        Platform.exit();
    }


    @FXML
    void back(ActionEvent event) throws IOException {


        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void showAllAppointments(ActionEvent event) throws IOException {
        try {
            appointmentTable.getItems().clear();
            appointmentTable.setItems(AppointmentDatabase.getAllAppointments());


        } catch (ParseException | SQLException ex) {
            Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void deleteAppointmentButton(ActionEvent event) throws ParseException, SQLException {
        if(appointmentTable.getSelectionModel().getSelectedItem() != null) {
            selectAppointment = (Appointment) appointmentTable.getSelectionModel().getSelectedItem();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select an appointment.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }
        AppointmentDatabase.deleteAppointment(selectAppointment);
        refreshAppointments.clear();
        refreshAppointments.addAll(AppointmentDatabase.getAllAppointments());
        appointmentTable.setItems(refreshAppointments);
    }

    @FXML
    void modifyAppointment(ActionEvent event) throws IOException {
        if(appointmentTable.getSelectionModel().getSelectedItem() != null) {
            selectAppointment = (Appointment) appointmentTable.getSelectionModel().getSelectedItem();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select appointment to modify.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }


        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/ModifyAppointment.fxml"));
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    private void showWeeklyAppointments(ActionEvent event) {
      try {
            appointmentTable.getItems().clear();
           appointmentTable.setItems(AppointmentDatabase.getWeeklyAppointments());


       } catch (ParseException | SQLException ex) {
           Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

    public static Appointment getSelectedAppointment() {
        return selectAppointment;
    }


    @FXML
    void test(ActionEvent event){
        selectAppointment = (Appointment) appointmentTable.getSelectionModel().getSelectedItem();
        System.out.println(selectAppointment);
    }

    @FXML
    private void showMonthlyAppointments(ActionEvent event) {
        if(showMonthly.isSelected()){
            try {
                appointmentTable.getItems().clear();
                appointmentTable.setItems(AppointmentDatabase.getMonthlyAppointments(pickMonth.getSelectionModel().getSelectedIndex()));
            } catch (ParseException ex) {
                Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


//    public Appointment getSelectedAppointment(){
//
//        return getSelectedAppointment();
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));

        appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentContact.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
        appointmentStartTime.setCellValueFactory(new PropertyValueFactory<>("appointmentStartTime"));
        appointmentEndTime.setCellValueFactory(new PropertyValueFactory<>("appointmentEndTime"));
        appointmentStartDate.setCellValueFactory(new PropertyValueFactory<>("appointmentStartDate"));

        months.addAll("January", "February", "March", "April", "May" ,"June" ,"July" ,"August" ,"September" ,"October" ,"November" ,"December");

        pickMonth.setItems(months);
        Calendar now = Calendar.getInstance();

        pickMonth.getSelectionModel().select(now.get(Calendar.MONTH));

        try {
           appointmentTable.setItems(AppointmentDatabase.getAllAppointments());

        } catch (ParseException | SQLException e) {
           Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("ParseException: " + e);
       }

   }
}
