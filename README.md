# Company-Registration-Blockchain

This project is a Java-based application for managing company registrations on a blockchain. It allows users to register new companies, access existing company information, and edit company details through a graphical user interface (GUI) built using JavaFX.

## Features

- Register new companies with relevant details.
- Access existing company information securely.
- Edit company details as needed.
- Integration with blockchain for secure data storage.

## Technologies Used

- Java
- JavaFX
- Blockchain technology (specifically tailored for company registrations)
- blockchain.jar file

## Getting Started

To get a local copy of this project up and running, follow these steps:

1. **Clone the repository**
   git clone https://github.com/Danny-phant0m/Company-Registration-Blockchain.git
Navigate to the project directory
cd company-registration-app
Compile the Java files
javac *.java
Run the application
java BlockchainServerMain
Usage

Launch the application.
Choose to register a new company or access existing company details.
Follow the prompts to enter company information or to retrieve data.

File Descriptions
BlockchainClient.java: Handles client-side blockchain interactions.
BlockchainServer.java: Implements the server that communicates with the blockchain.
BlockchainServerMain.java: Entry point for starting the blockchain server.
CompanyBlockchainManager.java: Manages blockchain operations for company registrations.
CompanyRegistration.java: Represents the company registration data model.
Entry.java: Defines the entry structure for the blockchain.
FXInitializer.java: Initializes the JavaFX application and sets up the GUI.
