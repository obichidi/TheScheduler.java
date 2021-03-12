package Controller;


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
import javafx.stage.Modality;
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




    @FXML private Label testerTest;
    private static Customer customerToUpdate;

    @FXML
         void modifyCustomer(ActionEvent event) {
        customerToUpdate = MainCustomerController.getSelectCustomer();

           if(phoneValidated() == false){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please enter the correct format for phone number.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }

            else{
            errorChecks();
            int customerId = customerToUpdate.getCustomerId();
            String customerName = customerNameModify.getText();
            String customerPhone = customerPhoneModify.getText();
            String customerAddress = customerAddressModify.getText();
            String customerZipCode = customerZipCodeModify.getText();
            String customerCountry = customerCountryModify.getValue();
            String customerDivision = customerDivisionModify.getValue();
            int customerDivisionId = CustomerDatabase.findDivisionId(customerDivision);


            CustomerDatabase.modifyCustomer(customerId, customerName, customerPhone, customerAddress, customerZipCode, customerDivisionId);
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

            testerTest.setText("Customer Id: " + customerId + "\nCustomer Name: " + customerName + "\nCustomer Phone: " + customerPhone + "\ncustomer Address: " + customerAddress + "\nCustomer Zip Code: " + customerZipCode + "\nCustomer Country: " + customerCountry +
                    "\nCustomer Division: " + customerDivision);

        }


    }

    @FXML
    void test(ActionEvent event) {


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
        customerCountryModify.setValue(customerCountryModify.getPromptText());




        customerDivisionModify.setItems(CustomerDatabase.DivisionList());
        customerDivisionModify.setPromptText(customerToUpdate.getCustomerDivision());
        customerDivisionModify.setValue(customerDivisionModify.getPromptText());

        customerNameModify.setText(customerToUpdate.getCustomerName());
        customerPhoneModify.setText(customerToUpdate.getCustomerPhone());
        customerAddressModify.setText(customerToUpdate.getCustomerAddress());
        customerZipCodeModify.setText(MainCustomerController.getSelectCustomer().getCustomerZipCode());
    }


    public boolean phoneValidated(){

          String phoneValidate = customerPhoneModify.getText();
        if(phoneValidate.matches("[0-9]{3}[-]{1}[0-9]{3}[-]{1}[0-9]{4}")
                || phoneValidate.matches("[0-9]{2}[-]{1}[0-9]{3}[-]{1}[0-9]{3}[-]{1}[0-9]{4}")){
            return true;
        }
        else{ return false;}




    }


    public void errorChecks() {



        if ( customerToUpdate == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a CUSTOMER.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }


        if (customerCountryModify.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a COUNTRY");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;



        }



         if(customerDivisionModify.getSelectionModel().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error.");
             alert.setContentText("Please select a DIVISION.");
             alert.initModality(Modality.APPLICATION_MODAL);
             alert.showAndWait();
             return;


             }



        if (customerNameModify.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please Enter CUSTOMER NAME.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;

        }


        if (customerPhoneModify.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please Enter PHONE.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;

        }


        if (customerAddressModify.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please Enter the ADDRESS.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();


        }

        }
}