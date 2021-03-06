package Controller;

import Database.CustomerDatabase;

import Model.Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    @FXML private Button modifyCustomer;
    @FXML private Label testerTest;
    private static Customer customerToUpdate;

    @FXML
    void modifyCustomer(ActionEvent event) {

    }

    @FXML
    void test(ActionEvent event) {

    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {

        customerToUpdate = MainCustomerController.getSelectCustomer();
       customerCountryModify.setItems(CustomerDatabase.CountryList());
       customerDivisionModify.setItems(CustomerDatabase.DivisionList());
//        customerNameModify.setText(MainCustomerController.getSelectCustomer().getCustomerName());
//        customerPhoneModify.setText(MainCustomerController.getSelectCustomer().getCustomerPhone());
//        customerAddressModify.setText(MainCustomerController.getSelectCustomer().getCustomerAddress());
//        customerZipCodeModify.setText(MainCustomerController.getSelectCustomer().getCustomerZipCode());
    }

}