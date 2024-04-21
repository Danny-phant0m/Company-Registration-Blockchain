package acsse.csc03a3;

public class CompanyRegistration {
    private String companyName;
    private String businessAddress;
    private String businessType;
    private String incorporationDate;
    private String legalStructure;
    private String phoneNumber;
    private String emailAddress;
    private String directors;
    private String description;
    
    // Constructor
    public CompanyRegistration(String companyName, String businessAddress, String businessType, String incorporationDate,
                               String legalStructure, String phoneNumber, String emailAddress, String directors, String description) {
        this.companyName = companyName;
        this.businessAddress = businessAddress;
        this.businessType = businessType;
        this.incorporationDate = incorporationDate;
        this.legalStructure = legalStructure;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.directors = directors;
        this.description = description;
    }
    
    public String getCompanyName() {
        return companyName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public String getBusinessType() {
        return businessType;
    }

    public String getIncorporationDate() {
        return incorporationDate;
    }

    public String getLegalStructure() {
        return legalStructure;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getDirectors() {
        return directors;
    }

    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return "Company Name: " + companyName + "\n" +
               "Business Address: " + businessAddress + "\n" +
               "Business Type: " + businessType + "\n" +
               "Incorporation Date: " + incorporationDate + "\n" +
               "Legal Structure: " + legalStructure + "\n" +
               "Phone Number: " + phoneNumber + "\n" +
               "Email Address: " + emailAddress + "\n" +
               "Directors: " + directors + "\n" +
               "Description: " + description;
    }



}
