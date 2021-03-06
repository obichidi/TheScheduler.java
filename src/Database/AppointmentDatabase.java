package Database;

import Model.Appointment;
import Model.Contact;
import Model.User;
import Util.ConnectorDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentDatabase {




    public static ObservableList<Contact> getAllContacts() throws ParseException, SQLException {
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "SELECT * FROM contacts";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");

                Contact contact = new Contact(contactId,contactName,contactEmail);
                allContacts.add(contact);
            }
        }
        return allContacts;
    }





    public static ObservableList<Appointment> getAllAppointments() throws ParseException, SQLException {
        ObservableList<Appointment> allAppointments=FXCollections.observableArrayList();
        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "SELECT a.Appointment_ID, a.Customer_ID, cu.Customer_Name, a.Title , a.Type , a.Location , a.Description, c.Contact_Name, a.Start,a.End\n" +
                    " From appointments   as a   \n" +
                    " inner join customers as cu\n" +
                    " on cu.Customer_ID = a.Customer_ID\n" +
                    " inner join contacts as c\n" +
                    " On c.Contact_ID = a.Contact_ID  ORDER BY Appointment_ID  ASC";

            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {

                int appointmentId = rs.getInt("a.Appointment_ID");

                String customerName = rs.getString("cu.Customer_Name");
                String appointmentTitle = rs.getString("a.Title");

                int  customerId = rs.getInt("a.Customer_ID");

                String appointmentDescription = rs.getString("a.Description");
                String appointmentLocation = rs.getString("a.Location");
                String appointmentType = rs.getString("a.Type");
                String appointmentContact= rs.getString("c.Contact_Name");

                LocalDate appointmentStartDate = rs.getDate("a.Start").toLocalDate();
                LocalTime appointmentStartTime = rs.getTime("a.Start").toLocalTime();
                LocalTime appointmentEndTime = rs.getTime("a.End").toLocalTime();

                Appointment appointment = new Appointment(appointmentId,  appointmentTitle,  customerId, customerName,  appointmentType,  appointmentLocation,  appointmentDescription, appointmentContact, appointmentStartDate,  appointmentStartTime, appointmentEndTime);
                allAppointments.add(appointment);

            }
        }
        return allAppointments;
    }


    public static ObservableList<Appointment> getWeeklyAppointments() throws ParseException ,SQLException {
        ObservableList<Appointment> weeklyAppointments=FXCollections.observableArrayList();
        LocalDate now = LocalDate.now();
        LocalDate week = LocalDate.now().plusWeeks(1);
        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "SELECT a.Appointment_ID,  a.Title, a.Type, a.Customer_ID, cu.Customer_Name, "
                    + " a.Description, a.Location, cu.Customer_ID, c.Contact_Name, "
                    + " a.Start, a.End " +
                    " FROM appointments as a " +
                    " INNER JOIN contacts as c " +
                    " ON c.Contact_ID = a.Contact_ID" +
                    " INNER JOIN customers as cu " +
                    " ON cu.Customer_ID = a.Customer_ID "
                    + "  WHERE a.Start >= '" + now + "' AND a.End <= '" + week + "';";

            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                int appointmentId = rs.getInt("a.Appointment_ID");
                String appointmentTitle = rs.getString("a.Title");
                String customerName = rs.getString("cu.Customer_Name");
                int  customerId = rs.getInt("a.Customer_ID");
                String appointmentDescription = rs.getString("a.Description");
                String appointmentLocation = rs.getString("a.Location");
                String appointmentType = rs.getString("a.Type");
                String appointmentContact= rs.getString("c.Contact_Name");
                LocalDate appointmentStartDate = rs.getDate("a.Start").toLocalDate();
                LocalTime appointmentStartTime = rs.getTime("a.Start").toLocalTime();
                LocalTime appointmentEndTime = rs.getTime("a.End").toLocalTime();


                Appointment appointment = new Appointment(appointmentId,  appointmentTitle,  customerId, customerName,  appointmentType,  appointmentLocation,  appointmentDescription, appointmentContact, appointmentStartDate,  appointmentStartTime, appointmentEndTime);
                weeklyAppointments.add(appointment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return weeklyAppointments;
    }


    public static ObservableList<Appointment> getMonthlyAppointments(Integer month) throws ParseException{
        ObservableList<Appointment> allAppointments=FXCollections.observableArrayList();
        String monthPlusOne = Integer.toString(month+1);
        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "SELECT a.Appointment_ID, cu.Customer_Name, a.Title, a.Customer_ID, a.Type, c.Contact_Name, "
                    + "a.Description, a.Location, "
                    + "a.Start, a.End " +
                    " FROM appointments as a " +
                    " INNER JOIN contacts as c " +
                    " ON c.Contact_ID = a.Contact_ID " +
                    " INNER JOIN customers as cu ON cu.Customer_ID "
                    + " = a.Customer_ID " +
                    " WHERE  MONTH(Start) = '" + monthPlusOne+ "';";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                int appointmentId = rs.getInt("a.Appointment_ID");
                String customerName = rs.getString("cu.Customer_Name");
                String appointmentTitle = rs.getString("a.Title");
                int  customerId = rs.getInt("a.Customer_ID");
                String appointmentDescription = rs.getString("a.Description");
                String appointmentLocation = rs.getString("a.Location");
                String appointmentType = rs.getString("a.Type");
                String appointmentContact= rs.getString("c.Contact_Name");
                LocalDate appointmentStartDate = rs.getDate("a.Start").toLocalDate();
                LocalTime appointmentStartTime = rs.getTime("a.Start").toLocalTime();
                LocalTime appointmentEndTime = rs.getTime("a.End").toLocalTime();


                Appointment appointment = new Appointment( appointmentId,  appointmentTitle,  customerId, customerName,  appointmentType,  appointmentLocation,  appointmentDescription, appointmentContact, appointmentStartDate,  appointmentStartTime, appointmentEndTime);
                allAppointments.add(appointment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allAppointments;
    }


    public static String getAppointmentsFor15Mins() throws ParseException{
        String upcomingAppointments = "";
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zoneNow = now.atZone(zoneId);
        LocalDateTime localNow = zoneNow.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        LocalDateTime localInFifteen = localNow.plusMinutes(15);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "SELECT a.Appointment_ID, cu.Customer_Name, a.Title, "
                    + " a.Description, a.Location, c.Contact_Name,  "
                    + "a.Start, a.End  "
                    + " FROM appointments as a " +
                    " INNER JOIN contacts as c" +
                    " ON c.Contact_ID = a.Contact_ID " +
                    " INNER JOIN customers  as cu " +
                    " ON a.Customer_ID = cu.Customer_ID"
                    + " WHERE Start BETWEEN '" + localNow + "' AND '" + localInFifteen + "';";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                String customer = rs.getString("cu.Customer_Name");
                Timestamp startTimeStamp = rs.getTimestamp("a.Start");
                LocalDateTime startTime = startTimeStamp.toLocalDateTime();
                String location = rs.getString("a.Location");
                String contact = rs.getString("c.Contact_Name");
                LocalDateTime localDateTime = LocalDateTime.parse(startTime.toString());
                ZonedDateTime startZonedTime = localDateTime.atZone(ZoneId.of("UTC"));
                ZonedDateTime localStart = startZonedTime.withZoneSameInstant(zoneId);
                upcomingAppointments += customer + " has an appointment at " + localStart.format(timeFormatter) + " with " + contact + " at the " + location + " Location. \n";
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex);
        }

        System.out.println(upcomingAppointments);
        return upcomingAppointments;

    }



    public static void addAppointment(int customerId, String customerName, String title, String location, String description, String type, int contactId,
                                      Timestamp start, Timestamp end){
//        appointmentId = AppointmentDatabase.generateAppointmentId();

        PreparedStatement statement;
        try {
            statement = ConnectorDb.connectDb().prepareStatement("INSERT INTO appointments( Title,Contact_ID,  Description, Location, Type," +
                    " Start, End, User_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID)"
                    + "VALUES ('" + title + "','" + contactId + "' ,'" + description + "','" + location + "','" + type+ "','" + start + "','" + end + "','" + User.currentUser.getUserId()+"', CURRENT_TIMESTAMP,  '" + User.currentUser.getUsername()+ "',CURRENT_TIMESTAMP,'" + User.currentUser.getUsername()+ "','" + customerId + "');");


            statement.executeUpdate();

            System.out.println("");
        } catch (SQLException ex) {
            System.out.println("SQL Exception : " + ex);
        }
    }

    public static void modifyAppointment(int appointmentId, int customerId, String type, String customerName, String title, String location,
                                         String description, String contact, Timestamp start, Timestamp end){
        PreparedStatement statement;
        try {
            statement = ConnectorDb.connectDb().prepareStatement(
                    "UPDATE appointments AS a " +
                            " INNER JOIN contacts AS c " +
                            " ON c.Contact_ID = a.Contact_ID " +
                            " INNER JOIN customers  AS cu " +
                            " ON cu.Customer_ID = a.Customer_ID  " +
                            " SET  Title = '" + title + "', Location = '" + location + "'," +
                            " Type = '" + type + "', cu.Customer_Name = '" + customerName + "'," +
                            " Description ='" + description + "', c.Contact_Name = '" + contact + "', Start  = '" + start + "'," +
                            " End = '" + end + "', a.Last_Update = CURRENT_TIMESTAMP, a.Last_Updated_By = '" + User.currentUser + "' " +
                            " WHERE a.Appointment_ID = '" + appointmentId + "';");




            statement.executeUpdate();
            System.out.println("Appointment successfully modified!");
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
    }


    public static void deleteAppointment(Appointment deleteAppointment){
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement(
                    "DELETE FROM appointments WHERE Appointment_ID = '" + deleteAppointment.getAppointmentId() + "';");
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
    }


    public static int generateAppointmentId() {
        int appointmentIdNum = 0;
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT Appointment_ID FROM appointments ORDER BY Appointment_ID ASC;");
            ResultSet rs = statement.executeQuery();
            if (rs.last()){
                appointmentIdNum = ((Number) rs.getObject(1)).intValue() + 1;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return appointmentIdNum;
    }

    public static boolean doesCustomerHaveAppointment(int customerId){
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT Customer_ID FROM appointments;");
            ResultSet rs = statement.executeQuery();
            if (rs.last()){
                if (customerId == rs.getInt("Customer_ID"))
                    return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }


    public static boolean doesContactHaveAppointment(String contact){
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT Contacts FROM Contact_Name;");
            ResultSet rs = statement.executeQuery();
            if (rs.last()){
                if (contact.equals(rs.getString("Contact_Name")))
                    return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }


    public static String isAppointmentOverlapping(Timestamp start, Timestamp end, int customerId, String contact, int appointmentId){
        String overlapMessage = "";
        Boolean errorMessage1 = false;
        Boolean errorMessage2 = false;
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT COUNT(*) FROM appointments WHERE (( '" + start + "' BETWEEN Start AND End) OR ( '" +
                    end + "' BETWEEN Start AND End) OR (Start < '" + start + "' AND End > '" + end + "')) and (Appointment_ID <> '" + appointmentId + "');");
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                if (rs.getInt(1)>0){
                    errorMessage1 = true;
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT COUNT(*) FROM appointments WHERE (Customer_ID = '" + customerId + "') AND (( '" + start + "' BETWEEN Start AND End) OR ( '" +
                    end + "' BETWEEN Start AND End) OR (Start < '" + start + "' AND End > '" + end + "')) and (Appointment_ID <> '" + appointmentId + "');");
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                if (rs.getInt(1)>0){
                    errorMessage2 = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
        if (errorMessage1) overlapMessage = overlapMessage + "The selected employee contact has an overlapping appointment.\n";
        if (errorMessage2) overlapMessage = overlapMessage + "The selected customer has an overlapping appointment.\n";
        return overlapMessage;
    }


    public static ObservableList<String> TypeList() {
        ObservableList<String> types = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT Type FROM appointments ORDER BY Type ASC;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                types.add(rs.getString("appointments.Type"));
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return types;
    }



    public static ObservableList<String> ContactList() {
        ObservableList<String> contacts = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT Contact_Name, Contact_ID FROM contacts ORDER BY Contact_Name ASC;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
//                if(!contacts.contains(rs.getString("contacts.Contact_Name"))){
                contacts.add(rs.getString("contacts.Contact_Name" ));
//                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return contacts;
    }

    public static ObservableList<String> LocationList() {
        ObservableList<String> locations = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT Location FROM appointments ORDER BY Location ASC;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
//                if(!locations.contains(rs.getString("appointments.Location"))){
                locations.add(rs.getString("appointments.Location"));
//                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return locations;
    }




    public static String OverlappedAppointment(Timestamp start, Timestamp end, int customerId, String contactName, int appointmentId){
        String overlapMessage = "";
        Boolean errorMessage1 = false;
        Boolean errorMessage2 = false;
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT COUNT(*) FROM appointment as a  WHERE (Contact_Name = '" + contactName + "') AND (( '" + start + "' BETWEEN start AND end) OR ( '" +
                    end + "' BETWEEN Start AND End) OR (Start < '" + start + "' AND End > '" + end + "')) and (Appointment_ID <> '" + appointmentId + "'" +
                    "INNER JOIN  contacts as c" +
                    " ON c.Contact_ID = a.Contact_ID);");
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                if (rs.getInt(1)>0){
                    errorMessage1 = true;
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT COUNT(*) FROM appointments WHERE (Customer_ID = '" + customerId + "') AND (( '" + start + "' BETWEEN start AND end) OR ( '" +
                    end + "' BETWEEN Start AND End) OR (Start < '" + start + "' AND End > '" + end + "')) and (Appointment_ID <> '" + appointmentId + "');");
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                if (rs.getInt(1)>0){
                    errorMessage2 = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
        if (errorMessage1) overlapMessage = overlapMessage + "The selected contact has an overlapping appointment.\n";
        if (errorMessage2) overlapMessage = overlapMessage + "The selected customer has an overlapping appointment.\n";
        return overlapMessage;
    }
//
//
//
//
//

}

