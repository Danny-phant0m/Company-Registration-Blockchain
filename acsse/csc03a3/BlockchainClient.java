package acsse.csc03a3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class BlockchainClient {
    private String serverAddress;
    private int serverPort;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;

    public BlockchainClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void registerCompany(CompanyRegistration registration) throws RegistrationFailedException {
        try {
            socket = new Socket(serverAddress, serverPort);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Send operation type (register) to server
            out.writeObject("register");

            // Send company registration information to server
            out.writeObject(registration);

            // Receive response from server
            String response = (String) in.readObject();
            System.out.println("Server response: " + response);

            // Close resources
            out.close();
            in.close();
            socket.close();

            if (!response.equals("Registration successful.")) {
                throw new RegistrationFailedException("Failed to register company. Server response: " + response);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RegistrationFailedException("Failed to register company due to an exception: " + e.getMessage());
        } finally {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    
    public CompanyRegistration retrieveCompany(String companyName) throws IOException, ClassNotFoundException {
    	socket = new Socket(serverAddress, serverPort);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        // Send operation type (retrieve) to server
        out.writeObject("retrieve");

        // Send company name to server
        out.writeObject(companyName);

        // Receive company information from server
        CompanyRegistration registration = (CompanyRegistration) in.readObject();
        System.out.println("Company information retrieved: " + registration);

        // Close resources
        out.close();
        in.close();
        socket.close();

        return registration;
    }
    
    public class RegistrationFailedException extends Exception {
        public RegistrationFailedException(String message) {
            super(message);
        }
    }

}