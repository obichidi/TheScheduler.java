package Controller;

import Database.CustomerDatabase;
import Model.Appointment;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainCustomerController  implements Initializable {

    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, Integer> customerIdTable;
    @FXML private TableColumn<Customer, String> nameTable;
    @FXML private TableColumn<Customer, String> addressTable;
    @FXML private TableColumn<Customer, String> postalCodeTable;
    @FXML private TableColumn<Customer, String> phoneTable;
    @FXML private TableColumn<Customer, String> divisionIdTable;
    @FXML private TableColumn<Customer, String> customerCountry;
    @FXML private TableColumn<Appointment, String> customerDivisionTable;
    @FXML private Button customerAddButton;
    @FXML private Button editCustomer;
    @FXML private Button deleteCustomer;
    @FXML private Button backButton;
    @FXML private Button testButton;

    static Customer selectCustomer;

    private final ObservableList<Customer> refreshCustomers = FXCollections.observableArrayList();


    public static Customer getSelectCustomer() {
        return selectCustomer;
    }



    @FXML
    void addCustomer(ActionEvent event) throws IOException {

        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void back(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/MainMenuController.fxml"));
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void deleteCustomer(ActionEvent event) throws ParseException, SQLException {

        selectCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();
        if (customerTable.getSelectionModel().getSelectedItem() == null) {


            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("You MUST select a Customer.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRM");
        alert.setContentText("Please confirm the you want to cancel :");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
        Optional<ButtonType> decision = alert.showAndWait();
        if (decision.get() == ButtonType.OK) {


            CustomerDatabase.deleteCustomer(selectCustomer.getCustomerId());
            refreshCustomers.clear();
            refreshCustomers.addAll(CustomerDatabase.getAllCustomers());
            customerTable.setItems(refreshCustomers);
        }
    }
    @FXML
    void editCustomer(ActionEvent event) {
        selectCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/ModifyCustomer.fxml"));
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        selectCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();

        customerIdTable.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameTable.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressTable.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        postalCodeTable.setCellValueFactory(new PropertyValueFactory<>("customerZipCode"));
        phoneTable.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        divisionIdTable.setCellValueFactory(new PropertyValueFactory<>("customerDivisionId"));
        customerDivisionTable.setCellValueFactory(new  PropertyValueFactory<>("customerDivision"));
        customerCountry.setCellValueFactory(new PropertyValueFactory<>("customerCountry"));

        try {
            customerTable.setItems(CustomerDatabase.getAllCustomers());

        } catch (ParseException | SQLException e) {
            Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("ParseException: " + e);
        }
        }



    @FXML
    void test(ActionEvent event) throws ParseException, SQLException {
        selectCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();
        System.out.println(selectCustomer);

    }
}

