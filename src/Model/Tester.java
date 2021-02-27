package Model;



public final class Tester {

    private int contactId;
    private String contactName;
    private String contactEmail;


    public Tester(int contactId, String contactName, String contactEmail){
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }
    public int getContactId(){
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName(){
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail(){
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

}
