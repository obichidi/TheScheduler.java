package Controller;
import Database.CustomerDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerController  implements Initializable {

    @FXML private Button addCustomerButton;
    @FXML private TextField customerNameText;
    @FXML private TextField customerPhoneText;
    @FXML private TextField customerAddressText;
    @FXML private TextField firstNameAdd;
    @FXML private TextField lastNameAdd;
    @FXML private TextField phoneAdd1;
    @FXML private TextField phoneAdd2;
    @FXML private TextField phoneAdd3;
    @FXML private TextField customerAddressNum;
    @FXML private TextField customerAddressStreet;
    @FXML private TextField customerZipText;

    @FXML private Label testData;
    @FXML private ComboBox<String> customerCountryBox;
    @FXML private ComboBox<String> customerDivisionBox;


    @FXML
    void addCustomer(ActionEvent event) {


        String customerName = firstNameAdd.getText() + " " + lastNameAdd.getText() ;
        String customerAddress = customerAddressNum.getText() + " " +customerAddressStreet.getText();
        String customerDivision = customerDivisionBox.getValue();
        String customerZipCode = customerZipText.getText();
        String customerPhone = phoneAdd1.getText() + "-" +  phoneAdd2.getText() + "-" +  phoneAdd3.getText();
        String customerCountry = customerCountryBox.getValue();
        int customerDivisionId = CustomerDatabase.findDivisionId(customerDivision);



        testData.setText("customerName :" + customerName + "\n Customer Address: " + customerAddress + "\n Customer Division: " + customerDivision
        + "\n Customer Zip:" + customerZipCode + "\n Customer Phone: " + customerPhone + "\n Customer Country: " + customerCountry +"\n Customer Division :" + customerDivisionId);


         CustomerDatabase.addCustomer(customerAddress, customerZipCode, customerPhone , customerName, customerDivisionId );

        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/MainCustomer.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("/View/MainCustomer.fxml"));
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize (URL url, ResourceBundle rb){

        customerCountryBox.setItems(CustomerDatabase.CountryList());
        customerDivisionBox.setItems(CustomerDatabase.DivisionList());

    }

    public void errorCheck(){

    }

}
