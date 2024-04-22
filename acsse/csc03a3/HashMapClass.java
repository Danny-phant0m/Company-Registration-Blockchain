/**
 * 
 */
package acsse.csc03a3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Daniel
 *
 */
public class HashMapClass{
		LinkedList<Entry>[] hashMap = new LinkedList[2];
		int size = 0;
	    public HashMapClass() {
	    	
	    	
	    }


	    public void put(Key k, Value v) {
//	        if (!validateCompanyInformation((CompanyRegistration) v)) {
//	            System.out.println("Invalid company information. Registration failed.");
//	            return;
//	        }
	    	
	    	if(size >= hashMap.length) {
	    		resize();
	    	}
	    	
	    	int index = getIndex(k);
	    	
	    	if(hashMap[index] == null) {
	    		hashMap[index] = new LinkedList<>();
	    		hashMap[index].add(new Entry(k,v));
	    		size++;
	    		return;
	    	}else {
	    		for(Entry entry : hashMap[index]) {
	    			if(entry.key.equals(k)) {
	    				System.out.println("Company with that name already exists.");
//	    				entry.value = v;
//	    				size++;
//	    				return;
	    			}
	    		}
	    		hashMap[index].add(new Entry(k,v));
	    		size++;
	    		return;
	    	}
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
	            return false; // If any required field is empty, return false
	        }
	        
	        // Regular expression to check for special characters
	        String regex = "[^A-Za-z0-9\\s]";
	        
	        // Check if the company name contains any special characters
	        if (companyName.matches(regex)) {
	            System.out.println("Company name contains special characters that are not allowed.");
	            return false;
	        }
	        
	        // Check if the incorporation date is in the past
	        try {
	            LocalDate.parse(incorporationDate, DateTimeFormatter.ofPattern("yyyy MM dd"));
	        } catch (DateTimeParseException e) {
	            System.out.println("Invalid incorporation date format. Use yyyy MM dd.");
	            return false;
	        }
	        
	        // Check if the email address is valid
	        if (!isValidEmail(emailAddress)) {
	            System.out.println("Invalid email address format.");
	            return false;
	        }
	        
	        return true; // All validations passed
	    }
	    public Value get(Key key) {
	        int index = getIndex(key);
	        
	       if(hashMap[index] == null) return null;
	       
	       for(Entry entry : hashMap[index]) {
	    	   if(entry.key.equals(key)) {
	    		   return entry.value;
	    	   }
	       }
	       return null;	
	    }
	    
	    public void remove(Key k) {
	    	if(k == null ) return;
	    	
	    	int index = getIndex(k);
	        
		    if(hashMap[index] == null) return;
		       
		    Entry entryToRemove = null;
		    
		    for(Entry entry : hashMap[index]) {
		    	   if(entry.key.equals(k)) {
		    		   entryToRemove = entry;
		    		   break;
		    	   }
		     }
		    
		    if(entryToRemove == null) return;
		    
		    hashMap[index].remove(entryToRemove);
		    size--;
	    }
	    
	    public boolean containsKey(Key key) {
	    	if(key == null) return false;
	    	
	    	int index = getIndex(key);
	    	
	    	if(hashMap[index] == null) {
	    		return false;
	    	}
	    	
	    	for(Entry entry : hashMap[index]) {
		    	   if(entry.key.equals(key)) {
		    		   return true;
		    	   }
		     }
	    	return false;
	    }
	    
	    public void resize() {
	    	LinkedList<Entry>[] oldHashMap = hashMap;
	    	hashMap = new LinkedList[size*2];
	    	size = 0;
	    	
	    	for(int i = 0; i < oldHashMap.length; i++) {
	    		if(oldHashMap[i] == null) continue;
	    		for(Entry entry : oldHashMap[i]) {
	    			put(entry.key,entry.value);
	    		}
	    	}
	    }

	    private int getIndex(Key key) {
	        return key.hashCode() % hashMap.length;
	    }

	    private boolean isValidEmail(String email) {
	        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	        Pattern pattern = Pattern.compile(emailRegex);
	        Matcher matcher = pattern.matcher(email);
	        return matcher.matches();
	    }
	    
	    public int getSize() {
	    	return size;
	    }
	    
	    public void printHashMapContents(Key k) {
	        int index = getIndex(k);
	        if (hashMap[index] == null) {
	            System.out.println("No entries found for the given key.");
	            return;
	        }

	        for (Entry entry : hashMap[index]) {
	            System.out.println(entry.value.toString());
	        }
	    }


}
