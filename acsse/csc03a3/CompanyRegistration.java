package acsse.csc03a3;

import java.io.Serializable;

public class CompanyRegistration implements Serializable{
    private String companyName;
    private String businessAddress;
    private String businessType;
    private String incorporationDate;
    private String legalStructure;
    private String phoneNumber;
    private String emailAddress;
    private String directors;
    private String description;
    private String IdNumber;
    private int companyID;
    private String hashedPassword;
    
    // Constructor
    public CompanyRegistration(String companyName, String businessAddress, String businessType, String incorporationDate,
                               String legalStructure, String phoneNumber, String emailAddress, String directors, String description, String idNumber, int companyID,String hashPassword) {
        this.companyName = companyName;
        this.businessAddress = businessAddress;
        this.businessType = businessType;
        this.incorporationDate = incorporationDate;
        this.legalStructure = legalStructure;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.directors = directors;
        this.description = description;
        this.IdNumber = idNumber;
        this.companyID = companyID;
        this.hashedPassword = hashPassword;
    }
    
    public String gethashedPassword() {
    	return hashedPassword;
    }
    
    public int getcompanyID() {
    	return companyID;
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
    public String getIdNumber() {
    	return IdNumber;
    }
    
    public int getCompanyID() {
    	return companyID;
    }
    
    @Override
    public String toString() {
        return "CompanyRegistration{" +
                "companyName='" + companyName + '\'' +
                ", businessAddress='" + businessAddress + '\'' +
                ", businessType='" + businessType + '\'' +
                ", incorporationDate='" + incorporationDate + '\'' +
                ", legalStructure='" + legalStructure + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", directors='" + directors + '\'' +
                ", description='" + description + '\'' +
                ", IdNumber='" + IdNumber + '\'' +
                ", companyID=" + companyID +
                ", hashedPassword='" + hashedPassword + '\'' +
                '}';
    }

	public void setCompanyName(String updatedCompanyName) {
		companyName = updatedCompanyName;
	}

	public void setBusinessAddress(String updatedBusinessAddress) {
		businessAddress = updatedBusinessAddress;
	}
	
	public void setBusinessType(String updatedBusinessType) {
        businessType = updatedBusinessType;
    }

    public void setIncorporationDate(String updatedIncorporationDate) {
        incorporationDate = updatedIncorporationDate;
    }

    public void setLegalStructure(String updatedLegalStructure) {
        legalStructure = updatedLegalStructure;
    }

    public void setPhoneNumber(String updatedPhoneNumber) {
        phoneNumber = updatedPhoneNumber;
    }

    public void setEmailAddress(String updatedEmailAddress) {
        emailAddress = updatedEmailAddress;
    }

    public void setDirectors(String updatedDirectors) {
        directors = updatedDirectors;
    }

    public void setDescription(String updatedDescription) {
        description = updatedDescription;
    }

    public void setIdNumber(String updatedIdNumber) {
        IdNumber = updatedIdNumber;
    }

    public void setCompanyID(int updatedCompanyID) {
        companyID = updatedCompanyID;
    }

    public void setHashedPassword(String updatedHashedPassword) {
        hashedPassword = updatedHashedPassword;
    }
    
}
