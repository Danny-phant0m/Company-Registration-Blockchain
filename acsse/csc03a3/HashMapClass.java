/**
 * 
 */
package acsse.csc03a3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Daniel
 *
 */
public class HashMapClass<K,V> {
	 private static final int DEFAULT_CAPACITY = 16;
	    private Entry<K, V>[] buckets;
	    private int size;

	    public HashMapClass() {
	        this(DEFAULT_CAPACITY);
	    }

	    public HashMapClass(int capacity) {
	        buckets = new Entry[capacity];
	        size = 0;
	    }

	    public void put(K key, V value) {
	        // Your validation logic here
	        if (!validateCompanyInformation((CompanyRegistration) value)) {
	            System.out.println("Invalid company information. Registration failed.");
	            return;
	        }

	        int index = getIndex(key);
	        Entry<K, V> entry = new Entry<>(key, value);

	        if (buckets[index] == null) {
	            buckets[index] = entry;
	            size++;
	        } else {
	            Entry<K, V> current = buckets[index];
	            while (current.next != null) {
	                if (current.key.equals(key)) {
	                    current.value = value; // Update value if key already exists
	                    return;
	                }
	                current = current.next;
	            }
	            current.next = entry; // Add new entry to the end of the linked list
	            size++;
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
	    public V get(K key) {
	        int index = getIndex(key);
	        Entry<K, V> current = buckets[index];
	        while (current != null) {
	            if (current.key.equals(key)) {
	                return current.value;
	            }
	            current = current.next;
	        }
	        return null; // Key not found
	    }

	    private int getIndex(K key) {
	        return Math.abs(key.hashCode()) % buckets.length;
	    }

	    private boolean isValidEmail(String email) {
	        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	        Pattern pattern = Pattern.compile(emailRegex);
	        Matcher matcher = pattern.matcher(email);
	        return matcher.matches();
	    }
	    
	    public void printHashMapContents() {
	        for (Entry<K, V> bucket : buckets) {
	            Entry<K, V> current = bucket;
	            while (current != null) {
	                System.out.println(current.value.toString());
	                current = current.next;
	            }
	        }
	    }


}
