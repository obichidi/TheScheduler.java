package Model;


/**
 * this class  is the class for the contact
 *
 */

public final class Contact {
    private int contactId;
    private String contactName;
    private String contactEmail;


    /**
     * this is the constructor method for the contact
     * @param contactId the contacts Id
     * @param contactEmail the contacts email
     * @param contactName the contacts name
     *
     */
    public Contact(int contactId, String contactName, String contactEmail){
        this.contactId = contactId;
        this.contactName = contactName;
         this.contactEmail = contactEmail;
    }

    /**
     * this is the getter method for the contactId
     * @return  contactId
     */
    public int getContactId(){
        return contactId;
    }


    /**
     * this is the setter method for the contactId
     * @param contactId   the contacts Id
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }


    /**
     * this is the getter method for the contactName
     * @return contactName
     */
    public String getContactName(){
        return contactName;
    }


    /**
     * this is the setter method for the contactId
     * @param contactName   the contacts name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }



    /**
     * this is the getter method for the contactEmail
     * @return contactEmail
     */
    public String getContactEmail(){
        return contactEmail;
    }

    /**
     * this is the setter method for the contactId
     * @param contactEmail   the contacts Email
     */

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }




}
