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
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * this class is for the main appointment controller for the Main appointment fxml
 */
public class MainAppointmentController implements Initializable {




   @FXML private TableView<Appointment> appointmentTable;

    @FXML private TableColumn<Appointment, Integer> appointmentId;
    @FXML private TableColumn<Appointment, String> appointmentType;

    @FXML private TableColumn<Appointment, String> appointmentTitle;
    @FXML private TableColumn<Appointment, String> appointmentLocation;
    @FXML private TableColumn<Appointment, String> appointmentDescription;
    @FXML private TableColumn<Appointment, Integer> customerId;
    @FXML private TableColumn<Appointment, String> customerNameC;

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
    @FXML private ToggleGroup toggleAppointment;

    static Appointment selectAppointment;


    private final ObservableList<Appointment>  refreshAppointments = FXCollections.observableArrayList();

/**This is the constructor for the MainAppointmentController */
public MainAppointmentController(){}

    /**
     * this function changes the scene to the AddAppointment fxml
     * @param event  this i s an event driven function
     * @throws IOException throws an io exception
     */

    @FXML
  public  void addAppointment(ActionEvent event) throws IOException {
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


    /**
     * this function exits the program
     * @param event  this is an event driven function

     */
    @FXML
   public void exit(ActionEvent event)  {


        Platform.exit();
    }

    /**
     * this function changes the scene to the MainMenu fxml
     * @param event  this is an event driven function
     */
    @FXML
   public void back(ActionEvent event)  {


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

    /**
     * this function sets the columns of the appointmentTable with the data from the database
     * @param event this is an event driven function
     */
    @FXML
   public void showAllAppointments(ActionEvent event)  {
        try {
            appointmentTable.getItems().clear();
            appointmentTable.setItems(AppointmentDatabase.getAllAppointments());


        } catch (ParseException | SQLException ex) {
            Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    /**
     * this function Deletes the selected appointment from the database and refreshes the appointmentTable
     * @param event  this is an event driven function
     * @throws  ParseException throws a parse exception
     * @throws SQLException throws a sqLException
     *
     */
    @FXML
   public void deleteAppointmentButton(ActionEvent event) throws ParseException, SQLException {
        selectAppointment = (Appointment) appointmentTable.getSelectionModel().getSelectedItem();
        if (appointmentTable.getSelectionModel().getSelectedItem() == null) {
            Alert appointmentAlert = new Alert(Alert.AlertType.ERROR);
            appointmentAlert.setTitle("Error.");
            appointmentAlert.setContentText("Please select an appointment.");
            appointmentAlert.initModality(Modality.APPLICATION_MODAL);
            appointmentAlert.showAndWait();
            return;

        }


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRM");
        alert.setContentText("Are you sure you want to cancel The appointment on :" + "\n" +  selectAppointment.getAppointmentStartDate()+"\n" +"At: " + selectAppointment.getAppointmentStartTime() + "\n"+
               "Customer: " + selectAppointment.getCustomerName() + "\n"  + "Created By: "+ selectAppointment.getUserName() );
            alert.initModality(Modality.APPLICATION_MODAL);

        Optional<ButtonType> decision = alert.showAndWait();
        if (decision.get() == ButtonType.OK) {



            AppointmentDatabase.deleteAppointment(selectAppointment);
            refreshAppointments.clear();
            refreshAppointments.addAll(AppointmentDatabase.getAllAppointments());
            appointmentTable.setItems(refreshAppointments);

        } else {
            return;
        }


    }

    /**
     * this function changes the scene to ModifyAppointment controller fxml with the selected appointment
     * @param event this is an event driven function
     *
     */

    @FXML
   public void modifyAppointment(ActionEvent event)  {
        if(appointmentTable.getSelectionModel().getSelectedItem() != null) {
            selectAppointment = (Appointment) appointmentTable.getSelectionModel().getSelectedItem();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SELECT AN APPOINTMENT");
            alert.setContentText("You MUST select an appointment In order to modify it..");
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

    /**
     * this function displays all weekly appointments in the appointmentTable
     * @param event  this is an event driven function
     *
     */

    @FXML
   public void showWeeklyAppointments(ActionEvent event) {
        try {
            appointmentTable.getItems().clear();
            appointmentTable.setItems(AppointmentDatabase.getWeeklyAppointments());


        } catch (ParseException | SQLException ex) {
            Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * this function gets the the selected appointment
     * @return  selectAppointment
     *
     */
    public static Appointment getSelectedAppointment() {
        return selectAppointment;
    }





    /**
     * this function displays all the monthly appointments in the appointmentTable
     * @param event this is an event driven function
     *
     */
    @FXML
    public void showMonthlyAppointments(ActionEvent event) {
        if(showMonthly.isSelected()){
            try {
                appointmentTable.getItems().clear();
                appointmentTable.setItems(AppointmentDatabase.getMonthlyAppointments());
            } catch (ParseException ex) {
                Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    /**
     * this function initializes the settings for the MainAppointment Controller
     * @param url URL
     * @param rb  ResourceBundle
     *
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Locale  englishLocale = new Locale("en", "EN");
        Locale.setDefault(englishLocale);
        appointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));



        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));

        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        customerNameC.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentContact.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
          System.out.println("gfiygfkug" + appointmentStartTime);
        appointmentStartTime.setCellValueFactory(new PropertyValueFactory<>("appointmentStartTime"));
        appointmentEndTime.setCellValueFactory(new PropertyValueFactory<>("appointmentEndTime"));
        appointmentStartDate.setCellValueFactory(new PropertyValueFactory<>("appointmentStartDate"));

        try {
            System.out.println(AppointmentDatabase.getAppointmentsIn15Mins());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            appointmentTable.setItems(AppointmentDatabase.getAllAppointments());

        } catch (ParseException | SQLException e) {
            Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("ParseException: " + e);
        }

    }
}