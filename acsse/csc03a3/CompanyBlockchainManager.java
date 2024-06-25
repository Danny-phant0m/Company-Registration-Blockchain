/**
 * 
 */
package acsse.csc03a3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import acsse.csc03a3.BlockchainClient.RegistrationFailedException;

/**
 * @author Daniel
 *
 */
public class CompanyBlockchainManager {
	private BlockchainClient blockchainClient;

    public CompanyBlockchainManager(String serverAddress, int serverPort) {
        blockchainClient = new BlockchainClient(serverAddress, serverPort);
    }

    // Add company information to blockchain via client
    public void addCompanyToBlockchain(CompanyRegistration registration) throws ClassNotFoundException, IOException, RegistrationFailedException {        
        blockchainClient.registerCompany(registration);
    }
    public CompanyRegistration getCompanyFromBlockchain(String companyName) throws ClassNotFoundException, IOException {
    	return blockchainClient.retrieveCompany(companyName);
    }
 }

