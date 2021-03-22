package Model;


/**
 * this is the class for the customer model
 *
 */
public final class Customer {


    private Integer customerId;



    private int  customerDivisionId;
    private String customerDivision;
    private String customerCountry;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerZipCode;





    /**
     * this is the  constructor method for the customer class
     * @param customerAddress the customers address
     * @param customerDivisionId the customers division Id
     * @param customerName  the customers name
     * @param customerId the customers Id
     * @param customerCountry   the customers country
     * @param customerDivision the customers division
     * @param customerPhone  the customers phone
     * @param customerZipCode the customers zip code
     *
     */

    public Customer(int customerId, String customerDivision, String customerName, String customerCountry, String customerAddress, String customerPhone, String customerZipCode, int customerDivisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerZipCode = customerZipCode;
        this.customerDivisionId = customerDivisionId;
        this.customerDivision = customerDivision;
        this.customerCountry = customerCountry;
    }


    /**
     * this is the getter method for the customer Address
     * @return   returns customer Id
     */

    public int getCustomerId() {
        return customerId;
    }

    /**
     * this is the setter method for the contactId
     * @param customerId   the customers Id
     */

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    /**
     * this is the getter method for the customerDivision
     * @return   returns customerDivision
     */
    public String getCustomerDivision() { return customerDivision; }

    /**
     * this is the setter method for the contactId
     * @param customerDivision  the customers division
     */


    public void setCustomerDivision(String customerDivision) { this.customerDivision = customerDivision; }

    /**
     * this is the getter method for the customerCountry
     * @return   returns customerCountry
     */
    public String getCustomerCountry() { return customerCountry; }

    /**
     * this is the setter method for the customersCountry
     * @param customerCountry   the customers country
     */
    public void setCustomerCountry(String customerCountry) { this.customerCountry = customerCountry; }


    /**
     * this is the getter method for the customer DivisionId
     * @return   returns customerDivisionId
     */
    public int getCustomerDivisionId(){return customerDivisionId;}

    /**
     * this is the setter method for the customerDivision Id
     * @param customerDivisionId the customers division Id
     */
    public void setCustomerDivisionId(int customerDivisionId) { this.customerDivisionId = customerDivisionId; }



    /**
     * this is the getter method for the customerName
     * @return   returns customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * this is the setter method for the customer name
     * @param customerName  the customers name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    /**
     * this is the getter method for the customerAddress
     * @return   returns customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * this is the setter method for the customer Address
     * @param customerAddress  the customers address
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * this is the getter method for the customerPhone
     * @return   returns customerPhone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }


    /**
     * this is the setter method for the customerPhone
     * @param customerPhone the customers phone number
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * this is the getter method for the customerZip
     * @return   returns customerZip
     */
    public String getCustomerZipCode() {
        return customerZipCode;
    }


    /**
     * this is the setter method for the customer Address
     * @param customerZipCode  the customers zip code
     */
    public void setCustomerZipCode(String customerZipCode) {
        this.customerZipCode = customerZipCode;
    }





}