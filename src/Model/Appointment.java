package Model;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * This is the appointment class that handles all of the appointment declaration and getters and setter methods
 */
public final class Appointment {

    private Integer appointmentId;
    private Integer customerId;
    private String customerName;
    private String appointmentTitle;
    private String appointmentType;
    private String appointmentLocation;
    private String appointmentDescription;
    private String appointmentContact;



    private int userId;
    private String appointmentStartDate;
    private String appointmentStartTime;
    private String appointmentEndTime;
    private String userNames;


    /**
     * This is the getter method that gets the user Id
     * @return returns userId
     */
    public int getUserId() { return userId;
    }

    /**
     * This is the setter method that sets the userId
     * @param userId the appointment Id
     */
    public void setUserId(int userId) { this.userId = userId;
    }

    /**
     * This is the getter method that gets the appointment id
     * @return returns userName
     */
   public String getUserName() { return userNames;
   }
    /**
     * This is the setter method that sets the appointmentId
     * @param userNames the appointment Id
     */
    public void setUserName(String userNames) { this.userNames = userNames;
    }

    /**
     * This is the getter method that gets the appointment id
     * @return returns appointmentId
     */

    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * This is the setter method that sets the appointmentId
     * @param appointmentId  the appointment Id
     */

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }


    /**
     * This is the getter method that gets the customerName
     *
     * @return customerName
     */
    public String getCustomerName() { return customerName; }


    /**
     * This is the setter method that sets the customerName
     * @param customerName  the customer name
     */
    public void setCustomerName(String customerName) { this.customerName = customerName; }


    /**
     * This is the getter method that gets the customerId
     * @return returns customerId
     */
    public int getCustomerId() { return customerId; }

    /**
     * This is the setter method that sets the customerId
     * @param customerId  the customer Id
     */
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    /**
     * This is the getter method that gets the appointmentTitle
     * @return returns appointmentTitle
     */
    public String getAppointmentTitle() { return appointmentTitle; }

    /**
     * This is the setter method that sets the appointmentTitle
     * @param appointmentTitle  the appointment title
     */
    public void setAppointmentTitle(String appointmentTitle) { this.appointmentTitle = appointmentTitle; }

    /**
     * This is the getter method that gets the appointmentType
     * @return returns appointmentType
     */
    public String getAppointmentType() { return appointmentType; }


    /**
     * This is the setter method that sets the appointmentType
     * @param appointmentType the appointment type
     */
    public void setAppointmentType(String appointmentType) { this.appointmentType = appointmentType; }


    /**
     * This is the getter method that gets the appointmentLocation
     * @return returns appointmentLocation
     */
    public String getAppointmentLocation() { return appointmentLocation; }


    /**
     * This is the setter method that sets the appointmentLocation
     * @param appointmentLocation  the appointment location
     */
    public void setAppointmentLocation(String appointmentLocation) { this.appointmentLocation = appointmentLocation; }

    /**
     * This is the getter method that gets the appointmentDescription
     * @return returns appointmentDescription
     */

    public String getAppointmentDescription() { return appointmentDescription; }


    /**
     * This is the setter method that sets the appointmentDescription
     * @param appointmentDescription the appointment description
     */
    public void setAppointmentDescription(String appointmentDescription) { this.appointmentDescription = appointmentDescription; }

    /**
     * This is the getter method that gets the appointmentContact
     * @return returns appointmentContact
     */
    public String getAppointmentContact() { return appointmentContact; }


    /**
     * This is the setter method that sets the appointmentContact
     * @param appointmentContact  the contact's name  for the appointment
     */
    public void setAppointmentContact(String appointmentContact) { this.appointmentContact = appointmentContact; }


    /**
     * This is the getter method that gets the appointmentStartDate
     * @return returns appointmentStartDate
     */
    public String getAppointmentStartDate() { return appointmentStartDate; }


    /**
     * This is the setter method that sets the appointmentStartDate
     * @param appointmentStartDate  the start date of the appointment
     */
    public void setAppointmentStartDate(String appointmentStartDate) { this.appointmentStartDate = appointmentStartDate; }


    /**
     * This is the getter method that gets the appointmentStartTime
     * @return returns appointmentStartTime
     */
    public String getAppointmentStartTime() { return appointmentStartTime; }

    /**
     * This is the setter method that sets the appointmentStartTime
     * @param appointmentStartTime  the start time of the appointment
     */
    public void setAppointmentStartTime(String appointmentStartTime) { this.appointmentStartTime = appointmentStartTime; }


    /**
     * This is the getter method that gets the appointmentEndTime
     * @return returns appointmentEndTime
     */
    public String getAppointmentEndTime() { return appointmentEndTime; }


    /**
     * This is the setter method that sets the appointmentEndTime
     * @param appointmentEndTime  the end time of the appointment
     */
    public void setAppointmentEndTime(String appointmentEndTime) { this.appointmentEndTime = appointmentEndTime; }



    /**
     * This is the constructor method for Appointments
     * @param appointmentId  the appointments Id
     * @param appointmentContact  the appointments contact name
     * @param appointmentDescription  the description of the appointment
     * @param appointmentEndTime  the end time of the appointment
     * @param appointmentLocation  the location of the appointment
     * @param appointmentStartDate  the appointments start date
     * @param appointmentStartTime the start time of the appointment
     * @param appointmentTitle  the title of the appointment
     * @param appointmentType  the appointment type
     * @param customerId  the customer Id
     * @param customerName  the customers name
     * @param userNames  the username
     * @param userId  the user Id
     */
    public Appointment(int appointmentId,int userId, String appointmentTitle, String userNames, int customerId, String customerName, String appointmentType, String appointmentLocation, String appointmentDescription, String appointmentContact, String appointmentStartDate, String appointmentStartTime, String appointmentEndTime) {



        this.appointmentId = appointmentId;

        this.appointmentTitle = appointmentTitle;
        this.appointmentType = appointmentType;
        this.appointmentLocation = appointmentLocation;
        this.appointmentDescription = appointmentDescription;
        this.appointmentContact = appointmentContact;
        this.appointmentStartDate = appointmentStartDate;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
        this.customerName = customerName;
        this.userNames = userNames;
        this.userId = userId;
        this.customerId = customerId;
    }






}












