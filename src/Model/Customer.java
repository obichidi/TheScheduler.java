package Model;



public final class Customer {


    private Integer customerId;



    private int  customerDivisionId;



    private String customerCountry;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerZipCode;



    private String customerDivision;



    public Customer(Integer customerId, String customerName, String customerCountry, String customerAddress, String customerPhone, String customerZipCode, int customerDivisionId, String customerDivision) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerZipCode = customerZipCode;
        this.customerDivisionId = customerDivisionId;
        this.customerDivision = customerDivision;
        this.customerCountry = customerCountry;
    }


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public String getCustomerDivision() { return customerDivision;
    }
    public void setCustomerDivision(String customerDivision) { this.customerDivision = customerDivision; }


    public String getCustomerCountry() { return customerCountry; }
    public void setCustomerCountry(String customerCountry) { this.customerCountry = customerCountry; }

    public int getCustomerDivisionId(){return customerDivisionId;}
    public void setCustomerDivisionId(int customerDivisionId) { this.customerDivisionId = customerDivisionId; }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerZipCode() {
        return customerZipCode;
    }

    public void setCustomerZipCode(String customerZipCode) {
        this.customerZipCode = customerZipCode;
    }





}