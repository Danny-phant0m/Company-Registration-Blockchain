package acsse.csc03a3;

import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FXInitializer {
    
    public FXInitializer(Stage primaryStage) {
        init(primaryStage);
    }

    private void init(Stage primaryStage) {
        // Create a GridPane layout for organizing the text fields and labels
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        
        // Create labels for each piece of information
        Label companyNameLabel = new Label("Company Name:");
        Label businessAddressLabel = new Label("Business Address:");
        Label businessTypeLabel = new Label("Business Type/Industry:");
        Label dateOfIncorporationLabel = new Label("Date of Incorporation:");
        Label legalStructureLabel = new Label("Legal Structure:");
        Label phoneNumberLabel = new Label("Phone Number:");
        Label emailLabel = new Label("Email Address:");
        Label directorsLabel = new Label("Names of Directors/Owners:");
        Label descriptionLabel = new Label("Description:");
        
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
        
        // Create a register button
        Button registerButton = new Button("Register");
        
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
        grid.add(registerButton, 0, 9, 2, 1); // Span the button across two columns
        
        // Set the grid layout as the root of the scene
        Scene scene = new Scene(grid, 600, 450);
        
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

            // Create a CompanyRegistration object
            CompanyRegistration registration = new CompanyRegistration(
                companyName, businessAddress, businessType, incorporationDate,
                legalStructure, phoneNumber, emailAddress, directors, description
            );
            
            HashMapClass myhashMap = new HashMapClass<>();
            
         // Add the company registration information to the HashMap
            myhashMap.put(companyName, registration);
            
            myhashMap.printHashMapContents();
        });
        
        primaryStage.setTitle("Company Registration Blockchain"); // Set window title
        primaryStage.setScene(scene); // Set the scene
        primaryStage.show(); // Display the window
    }
}
