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
    @FXML private TextField customerZipText;
    @FXML private TextField divisionIdText;
    @FXML private Label testData;
    @FXML private ComboBox<String> customerCountryBox;


    @FXML
    void addCustomer(ActionEvent event) {
        String customerName = customerNameText.getText();
        String customerAddress = customerAddressText.getText();

        String customerZipCode = customerZipText.getText();
        String customerPhone = customerPhoneText.getText();
         String customerCountry = customerCountryBox.getValue();

         CustomerDatabase.addCustomer(customerAddress, customerZipCode, customerPhone , customerName, customerCountry);

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

    }


    @Override
    public void initialize (URL url, ResourceBundle rb){

        customerCountryBox.setItems(CustomerDatabase.CustomerList());

    }

}
