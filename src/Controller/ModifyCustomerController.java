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
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;




/**
 * This class is for the controller class of the modify customer fxml
 */

public class ModifyCustomerController implements Initializable {
    @FXML private ComboBox<String> customerCountryModify;
    @FXML private ComboBox<String> customerDivisionModify;
    @FXML private TextField customerNameModify;
    @FXML private TextField customerPhoneModify;
    @FXML private TextField customerAddressModify;
    @FXML private TextField customerZipCodeModify;

    String Division;
int countryId;


/**Theis the constructor for the ModifyCustomerController */
public ModifyCustomerController(){}
    private static Customer customerToUpdate;



    /**
     * this function modifies the customer data through the ModifyCustomer fxml
     * @param event this is an event driven function
     *
     */

    @FXML
       public  void modifyCustomer(ActionEvent event) {
        customerToUpdate = MainCustomerController.getSelectCustomer();



           if(phoneValidated() == false){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
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



        }


    }







    /**
     * this function cchanges the sce to the MainCustomer fxml
     * @param event  this is an event driven function
     * @throws IOException throws an IO exception
     */

    @FXML
 public   void back(ActionEvent event) throws IOException {

        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainCustomer.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    /**
     * this function  initializes  the configuration
     * @param rb  ResourceBundle
     * @param url URL
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        customerToUpdate = MainCustomerController.getSelectCustomer();

        customerCountryModify.setItems(CustomerDatabase.CountryList());
        customerCountryModify.setPromptText(customerToUpdate.getCustomerCountry());
        customerCountryModify.setValue(customerCountryModify.getPromptText());





//        customerDivisionModify.setItems(CustomerDatabase.DivisionList(countryId));

        customerDivisionModify.setPromptText(customerToUpdate.getCustomerDivision());
        customerDivisionModify.setValue(customerDivisionModify.getPromptText());

        customerNameModify.setText(customerToUpdate.getCustomerName());
        customerPhoneModify.setText(customerToUpdate.getCustomerPhone());
        customerAddressModify.setText(customerToUpdate.getCustomerAddress());
        customerZipCodeModify.setText(MainCustomerController.getSelectCustomer().getCustomerZipCode());
    }



    /**
     * this function validates the phone number is in correct format
     * @return    returns a boolean

     */

    public boolean phoneValidated(){

          String phoneValidate = customerPhoneModify.getText();
        if(phoneValidate.matches("[0-9]{3}[-]{1}[0-9]{3}[-]{1}[0-9]{4}")
                || phoneValidate.matches("[0-9]{2}[-]{1}[0-9]{3}[-]{1}[0-9]{3}[-]{1}[0-9]{4}")){
            return true;
        }
        else{ return false;}
    }



    /**
     * this function  finds the Division from the country Id
     * @param event this is an event driven function
     */
    @FXML
   public void chooseDivision(ActionEvent event) {
        String country= customerCountryModify.getValue();
        int countryId = CustomerDatabase.findCountryId(country);




        customerDivisionModify.setItems(CustomerDatabase.DivisionList(countryId));



    }



    /**
     * this function checks for errors in the
     * ModifyCustomer fxml
     */
    public void errorChecks() {



        if ( customerToUpdate == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!");
            alert.setContentText("Please select a CUSTOMER.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;
        }


        if (customerCountryModify.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!");
            alert.setContentText("Please select a COUNTRY");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;



        }



         if(customerDivisionModify.getSelectionModel().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error!!");
             alert.setContentText("Please select a DIVISION.");
             alert.initModality(Modality.APPLICATION_MODAL);
             alert.showAndWait();
             return;


             }



        if (customerNameModify.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!");
            alert.setContentText("Please Enter CUSTOMER NAME.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;

        }


        if (customerPhoneModify.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!");
            alert.setContentText("Please Enter PHONE.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return;

        }


        if (customerAddressModify.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!");
            alert.setContentText("Please Enter the ADDRESS.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();


        }

        }
}