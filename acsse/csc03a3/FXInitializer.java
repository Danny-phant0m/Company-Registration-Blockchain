package acsse.csc03a3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acsse.csc03a3.BlockchainClient.RegistrationFailedException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXInitializer {
	private HashMapClass myHashMap = new HashMapClass();
	private CompanyBlockchainManager blockchainManager = new CompanyBlockchainManager("localhost",8080);
	private Scene previousScene;
    
    public FXInitializer(Stage primaryStage) {
        init(primaryStage);
    }
    
    private void init(Stage primaryStage) {
        VBox layout = new VBox(20); // Vertical layout with 20 pixels spacing
        layout.setPadding(new Insets(20)); // Add padding around the layout
        
        // Create buttons for registering a new company and accessing existing ones
        Button registerButton = new Button("Register New Company");
        Button accessButton = new Button("Access Existing Company");
        
        // Add buttons to the layout
        layout.getChildren().addAll(registerButton, accessButton);
        
        // Set actions for the buttons
        registerButton.setOnAction(e -> {
        	previousScene = primaryStage.getScene();
            // Navigate to the registration form
            primaryStage.setScene(createRegistrationScene());
        });
        
        accessButton.setOnAction(e -> {
        	previousScene = primaryStage.getScene();
            // Navigate to the screen for accessing existing companies
            primaryStage.setScene(createAccessScene());
        });
        
        // Create the scene with the layout
        Scene scene = new Scene(layout, 400, 200);
        
        // Set the scene for the primary stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Company Registration App");
        primaryStage.show();
    }

    private Scene createRegistrationScene() {
        // Create and return the scene for the registration form
    	// Create a GridPane layout for organizing the text fields and labels
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        
        // Create labels for each piece of information
        Label companyNameLabel = new Label("Company Name:");
        Label businessAddressLabel = new Label("Business Address:");
        Label businessTypeLabel = new Label("Business Type:");
        Label dateOfIncorporationLabel = new Label("Date of Incorporation:");
        Label legalStructureLabel = new Label("Legal Structure:");
        Label phoneNumberLabel = new Label("Phone Number:");
        Label emailLabel = new Label("Email Address:");
        Label directorsLabel = new Label("Names of Owners:");
        Label descriptionLabel = new Label("Description:");
        Label idNumberLabel = new Label("Owners ID Number:");
        Label passwordLabel = new Label("Password:");
        Label confirmPasswordLabel = new Label("Confirm Password:");
        
        // Create text fields for each piece of information
        TextField companyNameField = new TextField();
        TextField businessAddressField = new TextField();
        TextField businessTypeField = new TextField();
        TextField dateOfIncorporationField = new TextField();
        TextField legalStructureField = new TextField();
        TextField phoneNumberField = new TextField();
        TextField emailField = new TextField();
        TextField directorsField = new TextField();
        TextField descriptionField = new TextField();
        TextField idNumberField = new TextField();
        PasswordField passwordField = new PasswordField();
        PasswordField confirmPasswordField = new PasswordField();
        
        // Create a register button
        Button registerButton = new Button("Register");
        
        // Create a print button
        Button printButton = new Button("Print HashMap");
        
        //Create a back button
        Button backButton = new Button("Back");
        
        
        // Add labels and text fields to the grid layout
        grid.add(companyNameLabel, 0, 0);
        grid.add(companyNameField, 1, 0);
        grid.add(businessAddressLabel, 0, 1);
        grid.add(businessAddressField, 1, 1);
        grid.add(businessTypeLabel, 0, 2);
        grid.add(businessTypeField, 1, 2);
        grid.add(dateOfIncorporationLabel, 0, 3);
        grid.add(dateOfIncorporationField, 1, 3);
        grid.add(legalStructureLabel, 0, 4);
        grid.add(legalStructureField, 1, 4);
        grid.add(phoneNumberLabel, 0, 5);
        grid.add(phoneNumberField, 1, 5);
        grid.add(emailLabel, 0, 6);
        grid.add(emailField, 1, 6);
        grid.add(directorsLabel, 0, 7);
        grid.add(directorsField, 1, 7);
        grid.add(descriptionLabel, 0, 8);
        grid.add(descriptionField, 1, 8);
        grid.add(idNumberLabel, 0, 9);
        grid.add(idNumberField, 1, 9);
        grid.add(passwordLabel, 0, 10);
        grid.add(passwordField, 1, 10);
        grid.add(confirmPasswordLabel, 0, 11);
        grid.add(confirmPasswordField, 1, 11);
        grid.add(registerButton, 0, 12, 2, 1); // Span the button across two columns
        //grid.add(printButton, 0, 13, 2, 1); // Span the button across two columns
        grid.add(backButton, 0, 13, 2, 1);
        
        // Set the grid layout as the root of the scene
        Scene scene = new Scene(grid, 600, 600);
        
        backButton.setOnAction(e -> {
            // Navigate back to the previous scene
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(previousScene);
        });
        
        // Event handling for Register button
        registerButton.setOnAction(e -> {
            // Retrieve text from text fields
            String companyName = companyNameField.getText();
            String businessAddress = businessAddressField.getText();
            String businessType = businessTypeField.getText();
            String incorporationDate = dateOfIncorporationField.getText();
            String legalStructure = legalStructureField.getText();
            String phoneNumber = phoneNumberField.getText();
            String emailAddress = emailField.getText();
            String directors = directorsField.getText();
            String description = descriptionField.getText();
            String idNumber = idNumberField.getText();
            int companyID = new Random().nextInt(10000);
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (!password.equals(confirmPassword)) {
                displayErrorMessage("Password Mismatch", "Password and confirm password do not match.");
                return;
            }

            // Create a CompanyRegistration object
            CompanyRegistration registration = new CompanyRegistration(
                companyName, businessAddress, businessType, incorporationDate,
                legalStructure, phoneNumber, emailAddress, directors, description,idNumber,companyID,hashPassword(password)
            );
            
            // Validate the company information
            if (!validateCompanyInformation(registration)) {
                return;
            }
                    
            //myHashMap.put(new Key(companyName), new Value(registration));
            
            try {
            	blockchainManager.addCompanyToBlockchain(registration);
            	displaySuccessMessage("Registration Successful", "Company registered and secured on the blockchain.");
            	
            } catch (RegistrationFailedException | ClassNotFoundException | IOException  ex) {
            	displayErrorMessage("Failed To register" , ex.getMessage());
            	ex.printStackTrace();
            }            
        });

        // Add event handling for the print button
        printButton.setOnAction(e -> {
            myHashMap.printHashMapContents();
        });
        
        return scene;
    }
    
    /**
     * acces the company 
     * @return the scene
     */
    private Scene createAccessScene() {
        // Create and return the scene for accessing existing companies
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Create labels for company name and password
        Label companyNameLabel = new Label("Company Name:");
        Label passwordLabel = new Label("Password:");

        // Create text fields for company name and password
        TextField companyNameField = new TextField();
        PasswordField passwordField = new PasswordField();

        // Create a button to submit the access request
        Button accessButton = new Button("Access Company");
        
        //Create back button 
        Button backButton = new Button("Back");

       
        grid.add(companyNameLabel, 0, 0);
        grid.add(companyNameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(accessButton, 0, 2, 2, 1); // Span the button across two columns
        grid.add(backButton, 0, 3, 2, 1); // Span the button across two columns

        // Set the grid layout as the root of the scene
        Scene scene = new Scene(grid, 400, 200);

        // Event handling for the access button
        accessButton.setOnAction(e -> {
            // Retrieve entered company name and password
            String companyName = companyNameField.getText();
            String password = passwordField.getText();

            // Query the blockchain to verify company existence and password	
            boolean accessGranted = verifyCompanyCredentials(companyName, hashPassword(password));

            
            if (accessGranted) {
            	 CompanyRegistration companyRegistration;
				try {
					companyRegistration = blockchainManager.getCompanyFromBlockchain(companyName);
					if (companyRegistration != null) {
	                     displayCompanyInformation(companyRegistration);
	                 } else {
	                     displayErrorMessage("Access Denied", "Company not found in the blockchain.");
	                 }
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
            } else {
                displayErrorMessage("Access Denied", "Incorrect company name or password.");
            }
        });
        
        // Event handling for the back button
        backButton.setOnAction(e -> {
            // Navigate back to the previous scene
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(previousScene);
        });

        return scene;
    }
    
    /**
     * display the compnay info
     * @param companyRegistration
     */
    private void displayCompanyInformation(CompanyRegistration companyRegistration) {
        // Create a new layout for displaying company information
        VBox layout = new VBox(20); // Vertical layout with 20 pixels spacing
        layout.setPadding(new Insets(20)); // Add padding around the layout

        // Create labels to display company information
        Label companyNameLabel = new Label("Company Name: " + companyRegistration.getCompanyName());
        Label businessAddressLabel = new Label("Business Address: " + companyRegistration.getBusinessAddress());
        Label businessTypeLabel = new Label("Business Type: " + companyRegistration.getBusinessType());
        Label incorporationDateLabel = new Label("Date of Incorporation: " + companyRegistration.getIncorporationDate());
        Label legalStructureLabel = new Label("Legal Structure: " + companyRegistration.getLegalStructure());
        Label phoneNumberLabel = new Label("Phone Number: " + companyRegistration.getPhoneNumber());
        Label emailLabel = new Label("Email Address: " + companyRegistration.getEmailAddress());
        Label directorsLabel = new Label("Names of Owners: " + companyRegistration.getDirectors());
        Label descriptionLabel = new Label("Description: " + companyRegistration.getDescription());
        Label idNumberLabel = new Label("Owners ID Number: " + companyRegistration.getIdNumber());

        // Add labels to the layout
        layout.getChildren().addAll(
            companyNameLabel, businessAddressLabel, businessTypeLabel,
            incorporationDateLabel, legalStructureLabel, phoneNumberLabel,
            emailLabel, directorsLabel, descriptionLabel, idNumberLabel
        );

        // Create a button to go back to the access scene
        Button backButton = new Button("Back");
        
     // Create an "Edit" button
        Button editButton = new Button("Edit");

        // Add action for the edit button
        editButton.setOnAction(e -> {
            // Navigate to the edit scene
            Stage stage = (Stage) editButton.getScene().getWindow();
            stage.setScene(createEditScene(companyRegistration));
        });


        backButton.setOnAction(e -> {
            // Navigate back to the access scene
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(createAccessScene());
        });

        layout.getChildren().addAll(backButton,editButton);

        // Create a new scene with the layout
        Scene scene = new Scene(layout, 600, 500);

        // Get the primary stage and set the new scene
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Company Information");
        primaryStage.show();
    }
    
    private Scene createEditScene(CompanyRegistration companyRegistration) {
        // Create a GridPane layout for the edit form
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Create labels for each piece of information
        Label companyNameLabel = new Label("Company Name:");
        Label businessAddressLabel = new Label("Business Address:");
        Label businessTypeLabel = new Label("Business Type:");
        Label dateOfIncorporationLabel = new Label("Date of Incorporation:");
        Label legalStructureLabel = new Label("Legal Structure:");
        Label phoneNumberLabel = new Label("Phone Number:");
        Label emailLabel = new Label("Email Address:");
        Label directorsLabel = new Label("Names of Owners:");
        Label descriptionLabel = new Label("Description:");
        Label idNumberLabel = new Label("Owners ID Number:");
        Label passwordLabel = new Label("Password:");
        Label confirmPasswordLabel = new Label("Confirm Password:");
        

        // Create text fields 
        TextField companyNameField = new TextField(companyRegistration.getCompanyName());
        TextField businessAddressField = new TextField(companyRegistration.getBusinessAddress());
        TextField businessTypeField = new TextField(companyRegistration.getBusinessType());
        TextField dateOfIncorporationField = new TextField(companyRegistration.getIncorporationDate());
        TextField legalStructureField = new TextField(companyRegistration.getLegalStructure());
        TextField phoneNumberField = new TextField(companyRegistration.getPhoneNumber());
        TextField emailField = new TextField(companyRegistration.getEmailAddress());
        TextField directorsField = new TextField(companyRegistration.getDirectors());
        TextField descriptionField = new TextField(companyRegistration.getDescription());
        TextField idNumberField = new TextField(companyRegistration.getIdNumber());
        

        // Create an "Update" button
        Button updateButton = new Button("Update");
        
        Button backButton = new Button("Back");

        // Add labels, text fields, and button to the grid layout
        grid.add(companyNameLabel, 0, 0);
        grid.add(companyNameField, 1, 0);
        grid.add(businessAddressLabel, 0, 1);
        grid.add(businessAddressField, 1, 1);
        grid.add(businessTypeLabel, 0, 2);
        grid.add(businessTypeField, 1, 2);
        grid.add(dateOfIncorporationLabel, 0, 3);
        grid.add(dateOfIncorporationField, 1, 3);
        grid.add(legalStructureLabel, 0, 4);
        grid.add(legalStructureField, 1, 4);
        grid.add(phoneNumberLabel, 0, 5);
        grid.add(phoneNumberField, 1, 5);
        grid.add(emailLabel, 0, 6);
        grid.add(emailField, 1, 6);
        grid.add(directorsLabel, 0, 7);
        grid.add(directorsField, 1, 7);
        grid.add(descriptionLabel, 0, 8);
        grid.add(descriptionField, 1, 8);
        grid.add(idNumberLabel, 0, 9);
        grid.add(idNumberField, 1, 9);
        grid.add(updateButton, 0, 10, 2, 1); // Span the button across two columns
        grid.add(backButton, 0, 11, 2, 1);
        
        // Set the grid layout as the root of the scene
        Scene scene = new Scene(grid, 600, 500);

        // Event handling for the update button
        updateButton.setOnAction(e -> {
            // Retrieve the updated company information from the text fields
            String updatedCompanyName = companyNameField.getText();
            String updatedBusinessAddress = businessAddressField.getText();
            String updatedBusinessType = businessTypeField.getText();
            String updatedDateOfIncorporation = dateOfIncorporationField.getText();
            String updatedLegalStructure = legalStructureField.getText();
            String updatedPhoneNumber = phoneNumberField.getText();
            String updatedEmail = emailField.getText();
            String updatedDirectors = directorsField.getText();
            String updatedDescription = descriptionField.getText();
            String updatedIdNumber = idNumberField.getText();

            // Update the company registration object
            companyRegistration.setCompanyName(updatedCompanyName);
            companyRegistration.setBusinessAddress(updatedBusinessAddress);
            companyRegistration.setBusinessType(updatedBusinessType);
            companyRegistration.setIncorporationDate(updatedDateOfIncorporation);
            companyRegistration.setLegalStructure(updatedLegalStructure);
            companyRegistration.setPhoneNumber(updatedPhoneNumber);
            companyRegistration.setEmailAddress(updatedEmail);
            companyRegistration.setDirectors(updatedDirectors);
            companyRegistration.setDescription(updatedDescription);
            companyRegistration.setIdNumber(updatedIdNumber);

            // Add the updated company registration to the blockchain
            try {
                blockchainManager.addCompanyToBlockchain(companyRegistration);
                displaySuccessMessage("Update Successful", "Company information updated.");
            } catch (RegistrationFailedException | ClassNotFoundException | IOException ex) {
                displayErrorMessage("Failed to Update", ex.getMessage());
                ex.printStackTrace();
            }
        });
        
        // Event handling for the back button
        backButton.setOnAction(e -> {
            // Navigate back to the previous scene.
        	Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(createAccessScene());
        });

        return scene;
    }

    private boolean verifyCompanyCredentials(String companyName, String password) {
    	try {
    		// Retrieve company information from the blockchain
            CompanyRegistration companyRegistration = blockchainManager.getCompanyFromBlockchain(companyName);

            // Check if company information was found
            if (companyRegistration != null) {
                // Retrieve the stored password from the company registration
                String storedPassword = companyRegistration.gethashedPassword();

                // Check if the provided password matches the stored password
                if (storedPassword.equals(password)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                // Company not found in the blockchain, access denied
                return false;
            }
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    // Method to display an error message
    private void displayErrorMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to display a success message
    private void displaySuccessMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private boolean validateCompanyInformation(CompanyRegistration registration) {
    	// Retrieve company information
        String companyName = registration.getCompanyName();
        String businessAddress = registration.getBusinessAddress();
        String businessType = registration.getBusinessType();
        String incorporationDate = registration.getIncorporationDate();
        String legalStructure = registration.getLegalStructure();
        String phoneNumber = registration.getPhoneNumber();
        String emailAddress = registration.getEmailAddress();
        String directors = registration.getDirectors();
        String description = registration.getDescription();
        
        // Check if any required field is empty
        if (companyName.isEmpty() || businessAddress.isEmpty() || businessType.isEmpty() ||
            incorporationDate.isEmpty() || legalStructure.isEmpty() || phoneNumber.isEmpty() ||
            emailAddress.isEmpty() || directors.isEmpty() || description.isEmpty()) {
        	displayErrorMessage("Null values","Please Fill in all the fields.");
            return false; // If any required field is empty, return false
        }
        
        // Regular expression to check for special characters
        String regex = "^[a-zA-Z0-9]+$";
        
        // Check if the company name contains any special characters
        if (!companyName.matches(regex)) {
        	displayErrorMessage("Invalid company name","Company name contains special characters that are not allowed.");
            return false;
        }
        
        
        try {
            LocalDate.parse(incorporationDate, DateTimeFormatter.ofPattern("yyyy MM dd"));
        } catch (DateTimeParseException e) {
        	displayErrorMessage("Invalid company date","Invalid incorporation date format. Use yyyy MM dd.");
            return false;
        }
        
        // Check if the email address is valid
        if (!isValidEmail(emailAddress)) {
        	displayErrorMessage("Ivalid company email","Invalid email address format.");
            return false;
        }
        
        return true; // All validations passed
    }
    
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private String hashPassword(String password) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Get the hash bytes by digesting the password bytes
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            
            // Convert the hash bytes to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
