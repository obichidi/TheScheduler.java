package Controller;


import Database.TesterDb;
import Model.Tester;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class testerController implements Initializable {
    @FXML private ResourceBundle resources;
    @FXML private TableView<Tester> contactTable;
    @FXML private URL location;
    @FXML private TableColumn<Tester, Integer> contactId;
    @FXML private TableColumn<Tester, String> contactName;
    @FXML private TableColumn<Tester, String> contactEmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contactId.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        contactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contactEmail.setCellValueFactory(new PropertyValueFactory<>("contactEmail"));


        try {
            contactTable.setItems(TesterDb.getAllContacts2());


        } catch (ParseException | SQLException e) {
            Logger.getLogger(MainAppointmentController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("ParseException: " + e);
        }

    }
}

