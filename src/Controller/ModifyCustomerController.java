package Controller;

import Database.AppointmentDatabase;
import Database.CustomerDatabase;

import Model.Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCustomerController implements Initializable {
    @FXML private ComboBox<String> customerCountryModify;
    @FXML private ComboBox<String> customerDivisionModify;
    @FXML private TextField customerNameModify;
    @FXML private TextField customerPhoneModify;
    @FXML private TextField customerAddressModify;
    @FXML private TextField customerZipCodeModify;
    @FXML private Button test;
    @FXML private Button back;
    @FXML private Button modifyCustomer;
    @FXML private Label testerTest;
    private static Customer customerToUpdate;

    @FXML
    void modifyCustomer(ActionEvent event) {
        int customerId = customerToUpdate.getCustomerId();
        String customerName = customerNameModify.getText();
        String customerPhone = customerPhoneModify.getText();
        String customerAddress = customerAddressModify.getText();
        String customerZipCode = customerZipCodeModify.getText();
        String customerCountry = customerCountryModify.getValue();
        String customerDivision = customerDivisionModify.getValue();
        int customerDivisionId = CustomerDatabase.findDivisionId(customerDivision) ;


       CustomerDatabase.modifyCustomer( customerId,  customerName,  customerPhone,  customerAddress,  customerZipCode,  customerDivisionId);
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainCustomer.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex.getMessage());
        }

        testerTest.setText("Customer Id: " + customerId +"\nCustomer Name: " + customerName +  "\nCustomer Phone: "+ customerPhone + "\ncustomer Address: "+ customerAddress + "\nCustomer Zip Code: "+ customerZipCode + "\nCustomer Country: " + customerCountry +
       "\nCustomer Division: " + customerDivision);
    }

    @FXML
    void test(ActionEvent event) {
        System.out.println(customerToUpdate.getCustomerName());

    }

    @FXML
    void back(ActionEvent event) throws IOException {

        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainCustomer.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {

        customerToUpdate = MainCustomerController.getSelectCustomer();


        customerCountryModify.setItems(CustomerDatabase.CountryList());
        customerCountryModify.setPromptText(customerToUpdate.getCustomerCountry());

        customerDivisionModify.setItems(CustomerDatabase.DivisionList());
        customerDivisionModify.setPromptText(customerToUpdate.getCustomerDivision());


       customerNameModify.setText(customerToUpdate.getCustomerName());
       customerPhoneModify.setText(customerToUpdate.getCustomerPhone());
       customerAddressModify.setText(customerToUpdate.getCustomerAddress());
       customerZipCodeModify.setText(MainCustomerController.getSelectCustomer().getCustomerZipCode());
    }

}