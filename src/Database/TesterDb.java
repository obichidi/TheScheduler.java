package Database;

import Model.Tester;
import Util.ConnectorDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;


public class TesterDb {

    public static ObservableList<Tester> getAllContacts2() throws ParseException, SQLException {
        ObservableList<Tester> allTesters = FXCollections.observableArrayList();
        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "SELECT * FROM contacts";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");

                Tester tester = new Tester(contactId,contactName,contactEmail);
                allTesters.add(tester);
            }
        }
        return allTesters;
    }

}