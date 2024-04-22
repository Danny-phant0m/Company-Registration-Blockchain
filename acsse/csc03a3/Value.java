/**
 * 
 */
package acsse.csc03a3;

/**
 * @author Daniel
 *
 */
public class Value {
	
	private CompanyRegistration value;
	
	public Value(CompanyRegistration v) {
		value = v;
	}
	
	public CompanyRegistration getValue() {
		return value;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		Value value1 = (Value)o;
		return value ==value1.value;
	}
	
	@Override
    public String toString() {
        return "Value: " + value;
    }

}
