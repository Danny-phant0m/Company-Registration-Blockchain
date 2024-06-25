/**
 * 
 */
package acsse.csc03a3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Daniel
 *
 */
public class HashMapClass{
		LinkedList<Entry>[] hashMap;
		int size = 0;
	    public HashMapClass() {
	    	hashMap = new LinkedList[2];
	    }


	    public void put(Key k, Value v) {	    	
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
	    
	 // Retrieve all entries in the HashMap
	    public List<Entry> getAllEntries() {
	        List<Entry> allEntries = new ArrayList<>();
	        for (LinkedList<Entry> list : hashMap) {
	            if (list != null) {
	                allEntries.addAll(list);
	            }
	        }
	        return allEntries;
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
	        return Math.abs(key.hashCode() % hashMap.length);
	    }
	    public int getSize() {
	    	return size;
	    }
	    
	    public void printHashMapContents() {
	    	boolean isEmpty = true;
	        for (LinkedList<Entry> list : hashMap) {
	            if (list != null) {
	                for (Entry entry : list) {
	                    System.out.println(entry.value.toString());
	                    isEmpty = false;
	                }
	            }
	        }
	        if (isEmpty) {
	            System.out.println("HashMap is empty.");
	        }
	    }
}
