package Model;



public final class Customer {


    private Integer customerId;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerZipCode;



    private int customerDivision;



    public Customer(Integer customerId, String customerName, String customerAddress, String customerPhone, String customerZipCode, int customerDivision) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerZipCode = customerZipCode;
        this.customerDivision = customerDivision;
    }


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public int getCustomerDivision() { return customerDivision;
    }

    public void setCustomerDivision(int customerDivision) { this.customerDivision = customerDivision;
    }

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