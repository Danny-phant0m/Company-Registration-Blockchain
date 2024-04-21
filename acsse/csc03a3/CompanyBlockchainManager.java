/**
 * 
 */
package acsse.csc03a3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Daniel
 *
 */
public class CompanyBlockchainManager {


    public void addCompanyToBlockchain(CompanyRegistration registration) {
        // Create a new transaction with the company registration information
    	Transaction<CompanyRegistration> transaction = new Transaction<>("senderAddress", "receiverAddress", registration);
    	
    	List<Transaction<CompanyRegistration>> transactions = new ArrayList<>();
        transactions.add(transaction);
                
        Blockchain blockChain = new Blockchain();
        
        blockChain.addBlock(transactions);

    }



}
