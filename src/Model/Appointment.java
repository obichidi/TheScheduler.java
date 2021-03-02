package Model;


import java.time.LocalDate;
import java.time.LocalTime;

public final class Appointment {

    private Integer appointmentId;


    private Integer customerId;




//    private String customerName;
    private String appointmentTitle;
    private String appointmentType;
    private String appointmentLocation;
    private String appointmentDescription;
    private String appointmentContact;


    private LocalDate appointmentStartDate;
    private LocalTime appointmentStartTime;
    private LocalTime appointmentEndTime;





    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }
//    public String getCustomerName() { return customerName;
//    }
//
//    public void setCustomerName(String customerName) { this.customerName = customerName;
//    }
    public int getCustomerId() { return customerId;
    }


    public void setCustomerId(int customerId) { this.customerId = customerId;
    }


    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public String getAppointmentContact() {
        return appointmentContact;
    }

    public void setAppointmentContact(String appointmentContact) {
        this.appointmentContact = appointmentContact;
    }

    public LocalDate getAppointmentStartDate() {
        return appointmentStartDate;
    }

    public void setAppointmentStartDate(LocalDate appointmentStartDate) {
        this.appointmentStartDate = appointmentStartDate;
    }

    public LocalTime getAppointmentStartTime() {
        return appointmentStartTime;
    }

    public void setAppointmentStartTime(LocalTime appointmentStartTime) {
        this.appointmentStartTime = appointmentStartTime;
    }

    public LocalTime getAppointmentEndTime() {
        return appointmentEndTime;
    }

    public void setAppointmentEndTime(LocalTime appointmentEndTime) {
        this.appointmentEndTime = appointmentEndTime;
    }

    public Appointment(int appointmentId, String appointmentTitle, int customerId,  String appointmentType, String appointmentLocation, String appointmentDescription, String appointmentContact, LocalDate appointmentStartDate, LocalTime appointmentStartTime, LocalTime appointmentEndTime) {
        this.appointmentId = appointmentId;

        this.appointmentTitle = appointmentTitle;
        this.appointmentType = appointmentType;
        this.appointmentLocation = appointmentLocation;
        this.appointmentDescription = appointmentDescription;
        this.appointmentContact = appointmentContact;
        this.appointmentStartDate = appointmentStartDate;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
//      this.customerName = customerName;
        this.customerId = customerId;
    }






}










