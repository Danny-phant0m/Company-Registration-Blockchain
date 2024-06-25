package acsse.csc03a3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class BlockchainServer {
    private Blockchain<CompanyRegistration> blockchain;
    private Socket clientSocket;
    private HashMapClass myHashMap = new HashMapClass();

    public BlockchainServer() {
        blockchain = new Blockchain<>();
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Listening on port " + port);
            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
			if(clientSocket != null) {
				try {
					clientSocket.close();
			} catch (IOException e) {
					e.printStackTrace();
			}
		}
	}
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
            ) {
            	// Read the operation type from the client
                String operation = (String) in.readObject();

                // Handle registration operation
                if (operation.equals("register")) {
                    // Read company registration information from client
                    CompanyRegistration registration = (CompanyRegistration) in.readObject();
                    
                    // Get the company name and ID for the stake
                    String companyName = registration.getCompanyName(); 
                    int companyId = registration.getcompanyID();
                    
                    // Register stake in blockchain
                    blockchain.registerStake(companyName, companyId);
                    
                    // Add registration to blockchain
                    List<Transaction<CompanyRegistration>> transactions = List.of(new Transaction<>("sender", "receiver", registration));
                    blockchain.addBlock(transactions);
                    
                    // Print blockchain state after registration
                    System.out.println("Blockchain after Registration:");
                    System.out.println(blockchain.toString());
                    
                    myHashMap.put(new Key(companyName), new Value(registration));
                    
                    // Respond to client
                    out.writeObject("Registration successful.");
                } 
                // Handle retrieval operation
                else if (operation.equals("retrieve")) {
                    // Read company name from client
                    String companyName = (String) in.readObject();

                    // Retrieve company information 
                    CompanyRegistration registration = getCompanyInformation(companyName);

                    // Respond to client with company information
                    if (registration != null) {
                        out.writeObject(registration);
                    } else {
                        out.writeObject("Company not found in blockchain.");
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        private CompanyRegistration getCompanyInformation(String companyName) {
            // Check if the HashMap contains the company information
        	Value value = myHashMap.get(new Key(companyName));
            if (value != null) {
                return (CompanyRegistration) value.getValue();
            } else {
                return null; 
            }
        }
    }
}
