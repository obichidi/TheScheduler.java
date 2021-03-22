package Database;

import Model.Appointment;
import Model.Contact;
import Model.User;
import Util.ConnectorDb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.Location;
import java.sql.*;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this class implements the functionality of the the database calls from the controller classes
 */
public class AppointmentDatabase {


    /**This the constructor for the AppointmentDatabase class*/
    public AppointmentDatabase(){}


    /**
     * this function locates the contact id from the database given the contact name
     * @param Contact  the contact name
     * @return contactId
     */
    public static int findContactId(String Contact) {
        int selectContactId = 0;
        try {

            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT c.Contact_Name, c.Contact_ID  FROM contacts as c;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (Contact.equals(rs.getString("Contact_Name"))) {
                    selectContactId = rs.getInt("Contact_ID");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return selectContactId;
    }

    /**
     * this function extracts all the appointments from the database
     * @throws  ParseException  throws a parse exception
     * @throws  SQLException throws a sql exception
     * @return allAppointments
     */

    public static ObservableList<Appointment> getAllAppointments() throws ParseException, SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zoneNow = now.atZone(zoneId);
        LocalDateTime localNow = zoneNow.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);

        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "SELECT a.Appointment_ID, a.User_ID, a.Customer_ID, a.Last_Updated_By, cu.Customer_Name, a.Title , a.Type , a.Location , a.Description, c.Contact_Name, a.Start,a.End\n" +
                    " From appointments   as a   " +
                    " inner join customers as cu " +
                    " on cu.Customer_ID = a.Customer_ID " +
                    " inner join contacts as c " +
                    " On c.Contact_ID = a.Contact_ID  ORDER BY Appointment_ID  ASC";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {


                int appointmentId = rs.getInt("a.Appointment_ID");
                String customerName = rs.getString("cu.Customer_Name");
                String appointmentTitle = rs.getString("a.Title");
                int customerId = rs.getInt("a.Customer_ID");
                int userId = rs.getInt("a.User_ID");
                String appointmentDescription = rs.getString("a.Description");
                String appointmentLocation = rs.getString("a.Location");
                String appointmentType = rs.getString("a.Type");
                String appointmentContact = rs.getString("c.Contact_Name");

                String userNames = rs.getString("a.Last_Updated_By");

                String appointmentStartDate_x = rs.getString("a.Start");
                Timestamp startTimeStamp = Timestamp.valueOf(appointmentStartDate_x);
                LocalDateTime startDate = startTimeStamp.toLocalDateTime();
                LocalDateTime localDateTime = LocalDateTime.parse(startDate.toString());
                ZonedDateTime startZonedTime = localDateTime.atZone(ZoneId.of("UTC"));
                LocalDate localStart = startZonedTime.withZoneSameInstant(zoneId).toLocalDate();
                String appointmentStartDate = localStart.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));


                String appointmentStartTime_x = rs.getString("a.Start");



                Timestamp startimestamp = Timestamp.valueOf(appointmentStartTime_x);
                LocalDateTime startTime = startimestamp.toLocalDateTime();
                LocalDateTime localStartDateTime = LocalDateTime.parse(startTime.toString());
                ZonedDateTime startTimeZonedTime = localStartDateTime.atZone(ZoneId.of("UTC"));
                ZonedDateTime localStartTime = startTimeZonedTime.withZoneSameInstant(zoneId);
                String appointmentStartTime = localStartTime.format(timeFormatter);


                String appointmentEndTime_x = rs.getString("a.End");
                Timestamp endTimestamp = Timestamp.valueOf(appointmentEndTime_x);
                LocalDateTime endTime = endTimestamp.toLocalDateTime();
                LocalDateTime localEndDateTime = LocalDateTime.parse(endTime.toString());
                ZonedDateTime endTimeZonedTime = localEndDateTime.atZone(ZoneId.of("UTC"));
                ZonedDateTime localEndTime = endTimeZonedTime.withZoneSameInstant(zoneId);
                String appointmentEndTime = localEndTime.format(timeFormatter);


                Appointment appointment = new Appointment(  appointmentId,userId, appointmentTitle, userNames,  customerId,  customerName, appointmentType,  appointmentLocation,  appointmentDescription,  appointmentContact,  appointmentStartDate,  appointmentStartTime,  appointmentEndTime);
                allAppointments.add(appointment);

            }
        }

        return  allAppointments;
    }


    /**
     * this function extracts all the appointments from the database for the week and returns an observable List
     * @throws  ParseException throws a parse exception
     * @throws SQLException throws a sql exception
     * @return weeklyAppointments
     */

    public static ObservableList<Appointment> getWeeklyAppointments() throws ParseException, SQLException {
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zoneNow = now.atZone(zoneId);
        LocalDateTime localNow = zoneNow.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        ObservableList<Appointment> weeklyAppointments = FXCollections.observableArrayList();

        LocalDate week = LocalDate.now().plusWeeks(1);
        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "SELECT a.Appointment_ID,  a.Title, a.Type, a.Customer_ID,a.User_ID, cu.Customer_Name,a.Last_Updated_By, "
                    + " a.Description, a.Location, cu.Customer_ID, c.Contact_Name, "
                    + " a.Start, a.End " +
                    " FROM appointments as a " +
                    " INNER JOIN contacts as c " +
                    " ON c.Contact_ID = a.Contact_ID" +
                    " INNER JOIN customers as cu " +
                    " ON cu.Customer_ID = a.Customer_ID "
                    + " WHERE  a.Start  BETWEEN NOW() AND (NOW() + INTERVAL 7 day) " +
                    "ORDER BY a.Start;";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int appointmentId = rs.getInt("a.Appointment_ID");
                String appointmentTitle = rs.getString("a.Title");
                String userNames = rs.getString("a.Last_Updated_By");
                int userId = rs.getInt("a.User_ID");
                String customerName = rs.getString("cu.Customer_Name");
                int customerId = rs.getInt("a.Customer_ID");
                String appointmentDescription = rs.getString("a.Description");
                String appointmentLocation = rs.getString("a.Location");
                String appointmentType = rs.getString("a.Type");
                String appointmentContact = rs.getString("c.Contact_Name");


                String appointmentStartDate_x = rs.getString("a.Start");
                Timestamp startTimeStamp = Timestamp.valueOf(appointmentStartDate_x);
                LocalDateTime startDate = startTimeStamp.toLocalDateTime();
                LocalDateTime localDateTime = LocalDateTime.parse(startDate.toString());
                ZonedDateTime startZonedTime = localDateTime.atZone(ZoneId.of("UTC"));
                LocalDate localStart = startZonedTime.withZoneSameInstant(zoneId).toLocalDate();
                String appointmentStartDate = localStart.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));

                String appointmentStartTime_x = rs.getString("a.Start");

                Timestamp starTimestamp = Timestamp.valueOf(appointmentStartTime_x);
                LocalDateTime startTime = starTimestamp.toLocalDateTime();
                LocalDateTime localStartDateTime = LocalDateTime.parse(startTime.toString());
                ZonedDateTime startTimeZonedTime = localStartDateTime.atZone(ZoneId.of("UTC"));
                ZonedDateTime localStartTime = startTimeZonedTime.withZoneSameInstant(zoneId);
                String appointmentStartTime = localStartTime.format(timeFormatter);


                String appointmentEndTime_x = rs.getString("a.End");
                Timestamp endTimestamp = Timestamp.valueOf(appointmentEndTime_x);
                LocalDateTime endTime = endTimestamp.toLocalDateTime();
                LocalDateTime localEndDateTime = LocalDateTime.parse(endTime.toString());
                ZonedDateTime endTimeZonedTime = localEndDateTime.atZone(ZoneId.of("UTC"));
                ZonedDateTime localEndTime = endTimeZonedTime.withZoneSameInstant(zoneId);
                String appointmentEndTime = localEndTime.format(timeFormatter);


                Appointment appointment = new Appointment(appointmentId,userId, appointmentTitle, userNames,  customerId,  customerName, appointmentType,  appointmentLocation,  appointmentDescription,  appointmentContact,  appointmentStartDate,  appointmentStartTime,  appointmentEndTime);
                weeklyAppointments.add(appointment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return weeklyAppointments;
    }

    /**
     * this function extracts all the appointments from the database for the selected Month and returns an observable List
     *
     * @throws ParseException throws a parse exception
     * @return monthlyAppointments
     */
    public static ObservableList<Appointment> getMonthlyAppointments() throws ParseException {
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zoneNow = now.atZone(zoneId);
        LocalDateTime localNow = zoneNow.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);


        ObservableList<Appointment> monthlyAppointments = FXCollections.observableArrayList();

        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "SELECT a.Appointment_ID, a.Last_Updated_By, cu.Customer_Name, a.Title, a.User_ID, a.Customer_ID, a.Type, c.Contact_Name, "
                    + "a.Description, a.Location, "
                    + "a.Start, a.End " +
                    " FROM appointments as a " +
                    " INNER JOIN contacts as c " +
                    " ON c.Contact_ID = a.Contact_ID " +
                    " INNER JOIN customers as cu ON cu.Customer_ID "
                    + " = a.Customer_ID " +
                     "AND MONTH(NOW()) = MONTH(a.Start)"+
                         "ORDER BY MONTH(a.Start);";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int appointmentId = rs.getInt("a.Appointment_ID");
                String customerName = rs.getString("cu.Customer_Name");
                String appointmentTitle = rs.getString("a.Title");
                int customerId = rs.getInt("a.Customer_ID");
                int userId = rs.getInt("a.User_ID");
                String appointmentDescription = rs.getString("a.Description");
                String appointmentLocation = rs.getString("a.Location");
                String appointmentType = rs.getString("a.Type");
                String appointmentContact = rs.getString("c.Contact_Name");
                String userNames = rs.getString("a.Last_Updated_By");
                String appointmentStartDate_x = rs.getString("a.Start");
                Timestamp startTimeStamp = Timestamp.valueOf(appointmentStartDate_x);
                LocalDateTime startDate = startTimeStamp.toLocalDateTime();
                LocalDateTime localDateTime = LocalDateTime.parse(startDate.toString());
                ZonedDateTime startZonedTime = localDateTime.atZone(ZoneId.of("UTC"));
                LocalDate localStart = startZonedTime.withZoneSameInstant(zoneId).toLocalDate();
                String appointmentStartDate = localStart.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));

                String appointmentStartTime_x = rs.getString("a.Start");

                Timestamp starTimestamp = Timestamp.valueOf(appointmentStartTime_x);
                LocalDateTime startTime = starTimestamp.toLocalDateTime();
                LocalDateTime localStartDateTime = LocalDateTime.parse(startTime.toString());
                ZonedDateTime startTimeZonedTime = localStartDateTime.atZone(ZoneId.of("UTC"));
                ZonedDateTime localStartTime = startTimeZonedTime.withZoneSameInstant(zoneId);
                String appointmentStartTime = localStartTime.format(timeFormatter);


                String appointmentEndTime_x = rs.getString("a.End");
                Timestamp endTimestamp = Timestamp.valueOf(appointmentEndTime_x);
                LocalDateTime endTime = endTimestamp.toLocalDateTime();
                LocalDateTime localEndDateTime = LocalDateTime.parse(endTime.toString());
                ZonedDateTime endTimeZonedTime = localEndDateTime.atZone(ZoneId.of("UTC"));
                ZonedDateTime localEndTime = endTimeZonedTime.withZoneSameInstant(zoneId);
                String appointmentEndTime = localEndTime.format(timeFormatter);


                Appointment appointment = new Appointment(appointmentId,userId, appointmentTitle, userNames,  customerId,  customerName, appointmentType,  appointmentLocation,  appointmentDescription,  appointmentContact,  appointmentStartDate,  appointmentStartTime,  appointmentEndTime);
                monthlyAppointments.add(appointment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return monthlyAppointments;
    }


    /**
     * this function returns an alert for appointments in the 15 minute range for that instant in time.
     * @throws ParseException throws a parse exception
     * @return appointments
     */


    public static String getAppointmentsIn15Mins() throws ParseException {
        String appointments = null;
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
            while (rs.next()) {
                int appointmentID = rs.getInt("a.Appointment_ID");
                String customer = rs.getString("cu.Customer_Name");
                Timestamp startTimeStamp = rs.getTimestamp("a.Start");
                LocalDateTime startTime = startTimeStamp.toLocalDateTime();

                String location = rs.getString("a.Location");
                String contact = rs.getString("c.Contact_Name");
                LocalDateTime localDateTime = LocalDateTime.parse(startTime.toString());
                ZonedDateTime startZonedTime = localDateTime.atZone(ZoneId.of("UTC"));
                ZonedDateTime localStart = startZonedTime.withZoneSameInstant(zoneId);

                LocalDateTime startDate = startTimeStamp.toLocalDateTime();
                LocalDateTime localDateTime2 = LocalDateTime.parse(startDate.toString());
                ZonedDateTime startZonedTime2 = localDateTime.atZone(ZoneId.of("UTC"));
                LocalDate localStart2 = startZonedTime.withZoneSameInstant(zoneId).toLocalDate();
                String appointmentStartDate = localStart.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));


                appointments = customer + " has an appointment within 15 minutes!! \n" +
                        "Appointment Id: " + appointmentID+"\n on: " +appointmentStartDate+ " \nat: " + localStart.format(timeFormatter) + "\n with: " + contact + "\n at the: " + location + " Location. \n";


            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex);
        }


        return appointments;

    }


    /**
     * this function adds the input from the AddAppointment fxml into the database
     *
     * @param customerId  the customer Id
     * @param userId  the user Id
     * @param customerName the customer name
     * @param title  the customer title
     * @param location the customer location
     * @param description the customer description
     * @param type the customer type
     * @param contactId the contacts Id
     * @param start  the start date and time
     * @param end  the end date and time
     *
     */
    public static void addAppointment(int customerId, String customerName, String title, String location, String description, String type ,int userId, int contactId,
                                      Timestamp start, Timestamp end) {


        PreparedStatement statement;
        try {
            statement = ConnectorDb.connectDb().prepareStatement("INSERT INTO appointments( Title, Contact_ID,  Description, Location, Type," +
                    " Start, End, User_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID)"
                    + "VALUES ('" + title + "','" + contactId + "' ,'" + description + "','" + location + "','" + type + "','" + start + "','" + end + "','" + userId + "', CURRENT_TIMESTAMP,  '" + User.currentUser.getUsername() + "',CURRENT_TIMESTAMP,'" + User.currentUser.getUsername() + "','" + customerId + "');");


            statement.executeUpdate();


        } catch (SQLException ex) {
            System.out.println("SQL Exception : " + ex);
        }
    }


    /**
     * this function Inserts new data into  the database from the ModifyAppointment fxml
     *
     * @param appointmentId  the appointment Id
     * @param customerId  the customer Id
     * @param customerName the customer Name
     * @param title  the appointment title
     * @param location  the appointment location
     * @param description the appointment description
     * @param type the appointment type
     * @param contact the appointment contact
     * @param start  the appointment start day and time
     * @param end the appointment end date and time
     * @param userId  the users Id
     *
     */
    public static void modifyAppointment(int appointmentId, int customerId, String type, String customerName, int userId,String title, String location,
                                         String description, String contact, Timestamp start, Timestamp end) {
        PreparedStatement statement;
        try {
            statement = ConnectorDb.connectDb().prepareStatement(
                    "UPDATE appointments AS a " +
                            " INNER JOIN contacts AS c " +
                            " ON c.Contact_ID = a.Contact_ID " +
                            " INNER JOIN customers  AS cu " +
                            " ON cu.Customer_ID = a.Customer_ID  " +
                            " SET  Title = '" + title + "', User_ID = '" + userId + "', Location = '" + location + "'," +
                            " Type = '" + type + "', cu.Customer_Name = '" + customerName + "'," +
                            " Description ='" + description + "', c.Contact_Name = '" + contact + "', Start  = '" + start + "'," +
                            " End = '" + end + "', a.Last_Update = CURRENT_TIMESTAMP, a.Last_Updated_By = '" + User.currentUser.getUsername() + "' " +
                            " WHERE a.Appointment_ID = '" + appointmentId + "';");


            statement.executeUpdate();
            System.out.println("Appointment successfully modified!");
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
    }

/** this function deletes  the selected appointment from the database
 * @param deleteAppointment  the appointment to be deleted*/
    public static void deleteAppointment(Appointment deleteAppointment) {
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement(
                    "DELETE FROM appointments WHERE Appointment_ID = '" + deleteAppointment.getAppointmentId() + "';");
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
    }


    public static boolean doesCustomerHaveAppointment(int customerId) {
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT Customer_ID FROM appointments;");
            ResultSet rs = statement.executeQuery();
            if (rs.last()) {
                if (customerId == rs.getInt("Customer_ID"))
                    return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }


    public static boolean doesContactHaveAppointment(String contact) {
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT Contacts FROM Contact_Name;");
            ResultSet rs = statement.executeQuery();
            if (rs.last()) {
                if (contact.equals(rs.getString("Contact_Name")))
                    return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    /** this function gets all the appointment types from the database
     *   @return  types*/
    public static ObservableList<String> TypeList() {
        ObservableList<String> types = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT DISTINCT Type FROM appointments ORDER BY Type ASC;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                types.add(rs.getString("appointments.Type"));
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return types;
    }


    /** this function gets all the appointment contacts from the database
     *   @return  contacts*/

    public static ObservableList<String> ContactList() {
        ObservableList<String> contacts = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT DISTINCT Contact_Name, Contact_ID FROM contacts ORDER BY Contact_Name ASC;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
//                if(!contacts.contains(rs.getString("contacts.Contact_Name"))){
                contacts.add(rs.getString("contacts.Contact_Name"));
//                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return contacts;
    }





    /** this function gets all the appointment locations from the database
     *   @return  locations*/
    public static ObservableList<String> LocationList() {
        ObservableList<String> locations = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT  DISTINCT Location FROM appointments ORDER BY Location ASC;");
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


    /** this function checks to see if there is an overlapping appointment
     *   @return  overlap message
     *   @param contactName  the contacts name
     *   @param customerId  the customer id
     *   @param appointmentId  the appointment id
     *   @param end   the end date and time
     *   @param start  the start date and time*/

    public static String OverlappedAppointment(Timestamp start, Timestamp end, int customerId, String contactName, int appointmentId) {
        String overlapMessage = "";
        Boolean errorMessage1 = false;
        Boolean errorMessage2 = false;
        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT COUNT(*) FROM appointment as a  WHERE (Contact_Name = '" + contactName + "') AND (( '" + start + "' BETWEEN start AND end) OR ( '" +
                    end + "' BETWEEN Start AND End) OR (Start < '" + start + "' AND End > '" + end + "')) and (Appointment_ID <> '" + appointmentId + "'" +
                    "INNER JOIN  contacts as c" +
                    " ON c.Contact_ID = a.Contact_ID);");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
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
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
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

    /** this function gets all the appointment by contact name
     *   @return  allContactsAppointments
     *   @param selectContact   the selected contacts name
     *   @throws  ParseException throws parse exception
     *   @throws  SQLException throws sql exception
     *   */
    public static ObservableList<Appointment> getAllContactAppointments(String selectContact) throws ParseException, SQLException {
        ObservableList<Appointment> allContactAppointments = FXCollections.observableArrayList();
        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "SELECT a.Appointment_ID,a.Created_By, a.Customer_ID, cu.Customer_Name, a.Title , a.Type , a.Location , a.Description, c.Contact_Name, a.Start,a.End " +
                    " From appointments as a   " +
                    " INNER JOIN customers as cu " +
                    " on cu.Customer_ID = a.Customer_ID " +
                    " INNER JOIN contacts as c " +
                    " On c.Contact_ID = a.Contact_ID   " +
                    " WHERE c.Contact_Name = '" + selectContact + "';";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                ZoneId zoneId = ZoneId.systemDefault();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
                int appointmentId = rs.getInt("a.Appointment_ID");

                String customerName = rs.getString("cu.Customer_Name");
                String appointmentTitle = rs.getString("a.Title");

                int customerId = rs.getInt("a.Customer_ID");

                String appointmentDescription = rs.getString("a.Description");
                String appointmentLocation = rs.getString("a.Location");
                String appointmentType = rs.getString("a.Type");
                String appointmentContact = rs.getString("c.Contact_Name");



                String appointmentStartDate_x = rs.getString("a.Start");
                Timestamp startTimeStamp = Timestamp.valueOf(appointmentStartDate_x);
                LocalDateTime startDate = startTimeStamp.toLocalDateTime();
                LocalDateTime localDateTime = LocalDateTime.parse(startDate.toString());
                ZonedDateTime startZonedTime = localDateTime.atZone(ZoneId.of("UTC"));
                LocalDate localStart = startZonedTime.withZoneSameInstant(zoneId).toLocalDate();
                String appointmentStartDate = localStart.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                String userNames = rs.getString("a.Created_By");
                String appointmentStartTime_x = rs.getString("a.Start");
                int userId = rs.getInt("a.User_ID");
                Timestamp startimestamp = Timestamp.valueOf(appointmentStartTime_x);
                LocalDateTime startTime = startimestamp.toLocalDateTime();
                LocalDateTime localStartDateTime = LocalDateTime.parse(startTime.toString());
                ZonedDateTime startTimeZonedTime = localStartDateTime.atZone(ZoneId.of("UTC"));
                ZonedDateTime localStartTime = startTimeZonedTime.withZoneSameInstant(zoneId);
                String appointmentStartTime = localStartTime.format(timeFormatter);


                String appointmentEndTime_x = rs.getString("a.End");
                Timestamp endTimestamp = Timestamp.valueOf(appointmentEndTime_x);
                LocalDateTime endTime = endTimestamp.toLocalDateTime();
                LocalDateTime localEndDateTime = LocalDateTime.parse(endTime.toString());
                ZonedDateTime endTimeZonedTime = localEndDateTime.atZone(ZoneId.of("UTC"));
                ZonedDateTime localEndTime = endTimeZonedTime.withZoneSameInstant(zoneId);
                String appointmentEndTime = localEndTime.format(timeFormatter);

                Appointment appointment = new Appointment(appointmentId,userId, appointmentTitle,  userNames,  customerId,  customerName, appointmentType,  appointmentLocation,  appointmentDescription, appointmentContact,  appointmentStartDate, appointmentStartTime,  appointmentEndTime);
                allContactAppointments.add(appointment);

            }
        }
        return allContactAppointments;
    }

    /** this function gets all the appointment for a contact by appointment type
     * @param selectContact  contacts name
     * @param selectType appointment type
     * @throws  ParseException throws parse exception
     * @throws SQLException throws parse exception
     *   @return  allContactAppointmentsByType */
    public static ObservableList<Appointment> getAllContactAppointmentsByType(String selectType, String selectContact) throws ParseException, SQLException {
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zoneNow = now.atZone(zoneId);
        LocalDateTime localNow = zoneNow.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);

        ObservableList<Appointment> allContactAppointmentsByType = FXCollections.observableArrayList();
        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "SELECT a.Appointment_ID, a.User_ID, a.Last_Updated_By, a.Customer_ID, cu.Customer_Name, a.Title , a.Type , a.Location , a.Description, c.Contact_Name, a.Start,a.End " +
                    " From appointments as a   " +
                    " INNER JOIN customers as cu " +
                    " on cu.Customer_ID = a.Customer_ID " +
                    " INNER JOIN contacts as c " +
                    " On c.Contact_ID = a.Contact_ID   " +
                    " WHERE a.Type = '" + selectType + "' AND c.Contact_Name = '" + selectContact + "' ;";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {


                int appointmentId = rs.getInt("a.Appointment_ID");

                String customerName = rs.getString("cu.Customer_Name");
                String appointmentTitle = rs.getString("a.Title");

                int customerId = rs.getInt("a.Customer_ID");

                String appointmentDescription = rs.getString("a.Description");
                String appointmentLocation = rs.getString("a.Location");
                String appointmentType = rs.getString("a.Type");
                String appointmentContact = rs.getString("c.Contact_Name");
                String userNames = rs.getString("a.Last_Updated_By");
                int userId = rs.getInt("a.User_ID");

                String appointmentStartDate_x = rs.getString("a.Start");
                Timestamp startTimeStamp = Timestamp.valueOf(appointmentStartDate_x);
                LocalDateTime startDate = startTimeStamp.toLocalDateTime();
                LocalDateTime localDateTime = LocalDateTime.parse(startDate.toString());
                ZonedDateTime startZonedTime = localDateTime.atZone(ZoneId.of("UTC"));
                LocalDate localStart = startZonedTime.withZoneSameInstant(zoneId).toLocalDate();
                String appointmentStartDate = localStart.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));

                String appointmentStartTime_x = rs.getString("a.Start");

                Timestamp startimestamp = Timestamp.valueOf(appointmentStartTime_x);
                LocalDateTime startTime = startimestamp.toLocalDateTime();
                LocalDateTime localStartDateTime = LocalDateTime.parse(startTime.toString());
                ZonedDateTime startTimeZonedTime = localStartDateTime.atZone(ZoneId.of("UTC"));
                ZonedDateTime localStartTime = startTimeZonedTime.withZoneSameInstant(zoneId);
                String appointmentStartTime = localStartTime.format(timeFormatter);


                String appointmentEndTime_x = rs.getString("a.End");
                Timestamp endTimestamp = Timestamp.valueOf(appointmentEndTime_x);
                LocalDateTime endTime = endTimestamp.toLocalDateTime();
                LocalDateTime localEndDateTime = LocalDateTime.parse(endTime.toString());
                ZonedDateTime endTimeZonedTime = localEndDateTime.atZone(ZoneId.of("UTC"));
                ZonedDateTime localEndTime = endTimeZonedTime.withZoneSameInstant(zoneId);
                String appointmentEndTime = localEndTime.format(timeFormatter);

                Appointment appointment = new Appointment(appointmentId,userId, appointmentTitle,  userNames,  customerId,  customerName, appointmentType,  appointmentLocation,  appointmentDescription, appointmentContact,  appointmentStartDate, appointmentStartTime,  appointmentEndTime);
                allContactAppointmentsByType.add(appointment);

            }
        }
        return allContactAppointmentsByType;
    }


    public static ObservableList<String> AllStartTimeList() {
        ObservableList<String> allStartTimes = FXCollections.observableArrayList();


        try {
            PreparedStatement statement = ConnectorDb.connectDb().prepareStatement("SELECT  Start  FROM appointments ;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (!allStartTimes.contains(rs.getString("Start"))) {
                    allStartTimes.add(rs.getString("Start"));



                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }

        return allStartTimes;
    }

    /** this function gets all the appointment for a contact by appointment month
     * @param selectedContact contacts name
     * @throws ParseException throws  parse exception
     * @param selectedIndex  the index of the month array;
     *   @return  allContactAppointmentsByMonth */
    public static ObservableList<Appointment> getContactsMonthlyAppointments(int selectedIndex, String selectedContact) throws ParseException {
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zoneNow = now.atZone(zoneId);
        LocalDateTime localNow = zoneNow.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        ObservableList<Appointment> allMonthlyContactAppointments = FXCollections.observableArrayList();
        String selectedMonth = Integer.toString(selectedIndex + 1);
        try (Statement statement = ConnectorDb.connectDb().createStatement()) {
            String query = "SELECT a.Appointment_ID, a.Last_Updated_By, a.Customer_ID,a.User_ID, cu.Customer_Name, a.Title , a.Type , a.Location , a.Description, c.Contact_Name, a.Start,a.End " +
                    " From appointments as a   " +
                    " INNER JOIN customers as cu " +
                    " on cu.Customer_ID = a.Customer_ID " +
                    " INNER JOIN contacts as c " +
                    " On c.Contact_ID = a.Contact_ID   " +
                    " WHERE c.Contact_Name = '" + selectedContact + "' AND MONTH(Start) = '" + selectedMonth + "' ;";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int appointmentId = rs.getInt("a.Appointment_ID");
                String customerName = rs.getString("cu.Customer_Name");
                String appointmentTitle = rs.getString("a.Title");
                int customerId = rs.getInt("a.Customer_ID");
                String appointmentDescription = rs.getString("a.Description");
                String appointmentLocation = rs.getString("a.Location");
                String appointmentType = rs.getString("a.Type");
                String userNames = rs.getString("a.Last_Updated_By");

                String appointmentContact = rs.getString("c.Contact_Name");

                String appointmentStartDate_x = rs.getString("a.Start");
                Timestamp startTimeStamp = Timestamp.valueOf(appointmentStartDate_x);
                LocalDateTime startDate = startTimeStamp.toLocalDateTime();
                LocalDateTime localDateTime = LocalDateTime.parse(startDate.toString());
                ZonedDateTime startZonedTime = localDateTime.atZone(ZoneId.of("UTC"));
                LocalDate localStart = startZonedTime.withZoneSameInstant(zoneId).toLocalDate();
                String appointmentStartDate = localStart.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                int userId = rs.getInt("a.User_ID");
                String appointmentStartTime_x = rs.getString("a.Start");

                Timestamp startimestamp = Timestamp.valueOf(appointmentStartTime_x);
                LocalDateTime startTime = startimestamp.toLocalDateTime();
                LocalDateTime localStartDateTime = LocalDateTime.parse(startTime.toString());
                ZonedDateTime startTimeZonedTime = localStartDateTime.atZone(ZoneId.of("UTC"));
                ZonedDateTime localStartTime = startTimeZonedTime.withZoneSameInstant(zoneId);
                String appointmentStartTime = localStartTime.format(timeFormatter);


                String appointmentEndTime_x = rs.getString("a.End");
                Timestamp endTimestamp = Timestamp.valueOf(appointmentEndTime_x);
                LocalDateTime endTime = endTimestamp.toLocalDateTime();
                LocalDateTime localEndDateTime = LocalDateTime.parse(endTime.toString());
                ZonedDateTime endTimeZonedTime = localEndDateTime.atZone(ZoneId.of("UTC"));
                ZonedDateTime localEndTime = endTimeZonedTime.withZoneSameInstant(zoneId);
                String appointmentEndTime = localEndTime.format(timeFormatter);


                Appointment appointment = new Appointment(appointmentId,userId, appointmentTitle,  userNames,  customerId,  customerName, appointmentType,  appointmentLocation,  appointmentDescription, appointmentContact,  appointmentStartDate, appointmentStartTime,  appointmentEndTime);
                allMonthlyContactAppointments.add(appointment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allMonthlyContactAppointments;
    }




}