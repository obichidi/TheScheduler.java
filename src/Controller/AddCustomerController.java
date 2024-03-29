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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

/**
 * This class is the controller class that controls the mechanics of the AddCustomer scene
 */

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
    @FXML private TextField cityAdd;
    @FXML private TextField customerZipText;


    @FXML private ComboBox<String> customerCountryBox;
    @FXML private ComboBox<String> customerDivisionBox;

/**This is the constructor for the AddCustomerController*/
 public  AddCustomerController(){}

    /**
     *  This function adds the  customer data into the database
     * @param event  this function is triggered by an event
     */

    @FXML
   public void addCustomer(ActionEvent event) {

        if(errorChecks() == false){
            String customerName = firstNameAdd.getText() + " " + lastNameAdd.getText() ;
            String customerAddress = customerAddressNum.getText() + " " +customerAddressStreet.getText() + "," +cityAdd.getText();
            String customerDivision = customerDivisionBox.getValue();
            String customerZipCode = customerZipText.getText();
            String customerPhone = phoneAdd1.getText() + "-" +  phoneAdd2.getText() + "-" +  phoneAdd3.getText();
            String customerCountry = customerCountryBox.getValue();
            int customerDivisionId = CustomerDatabase.findDivisionId(customerDivision);




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

        }



    /**
     *  This function the selects the Division with the given countryId and sets the correct Divisions in the division box per country
     * @param event  this function is triggered by an event
     */
    @FXML
   public void chooseDivision(ActionEvent event) {
        String country= customerCountryBox.getValue();
        int countryId = CustomerDatabase.findCountryId(country);




       customerDivisionBox.setItems(CustomerDatabase.DivisionList(countryId));


    }




    /**
     * This function changes the scene to the maincustomer.fxml
     * @param event  this function is triggered by an event
     */

    @FXML
   public void back(ActionEvent event) {
        String fxml =  "/View/MainCustomer.fxml";
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(fxml));
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * This function initializing the initial values when the scene is loaded.
     * @param rb  resource bundle
     * @param url URL
     *
     */

    @Override
    public void initialize (URL url, ResourceBundle rb){

        customerCountryBox.setItems(CustomerDatabase.CountryList());






    }

    /**
     *  This function checks to see if the phone number inputted is in the correct format for London, Canada, and USA
     * @return  boolean true or false if the phone number is entered correctly
     */
    public boolean phoneValidated() {


        String phoneValidate = phoneAdd1.getText() + "-" + phoneAdd2.getText() + "-" + phoneAdd3.getText();
        if (phoneValidate.matches("[0-9]{3}[-]{1}[0-9]{3}[-]{1}[0-9]{4}")
                || phoneValidate.matches("[0-9]{2}[-]{1}[0-9]{3}[-]{1}[0-9]{3}[-]{1}[0-9]{4}")) {
            return true;
        }
        return  false;
    }



    /**
     * This Boolean function checks to make sure there are no errors with the user input.
     * @return  returns true or false depending on if there is an error or not.
     */

    public Boolean errorChecks() {
        System.out.println(phoneValidated());


        if(phoneValidated() == false) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error.");
                alert.setContentText("Please Input phone number in the correct format");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.showAndWait();
                return true;

            }


if(customerCountryBox == null){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error.");
    alert.setContentText("Please Select A DIVISION Before Country");
    alert.initModality(Modality.APPLICATION_MODAL);
    alert.showAndWait();
    return true;
}


        if (customerCountryBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a COUNTRY.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;
        }


        if (customerDivisionBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please select a DIVISION.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;

        }

        if (firstNameAdd.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please Enter FIRST NAME.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;

        }


        if (lastNameAdd.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please Enter LAST NAME.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;

        }


        if (phoneAdd1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please Enter FIRST 3 Digits  of your PHONE NUMBER.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;

        }
        if (phoneAdd2.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please Enter SECOND 3 Digits  of your PHONE NUMBER.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;

        }

        if (phoneAdd3.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please Enter LAST 4 Digits  of your PHONE NUMBER.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;

        }

        if (customerAddressNum.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please Enter LAST 3 Numbers  of your ADDRESS.");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;

        }

        if (customerAddressStreet.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please Enter your STREET NAME .");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;

        }

        if (cityAdd.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please Enter your CITY .");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;


        }

        if (customerZipText.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error.");
            alert.setContentText("Please Enter your POSTAL CODE .");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            return true;


        }
        return false;
    }
}
