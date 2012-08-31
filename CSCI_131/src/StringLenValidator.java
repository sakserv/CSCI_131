import java.util.logging.Logger;

/**
 * Provides string length validation methods using assertion or or conditional
 * comparison.
 * 
 * @author Shane Kumpf
 * @version 0.1 8/28/12
 * @since 1.6
 *
 */
public class StringLenValidator {
	
	Logger logger = Logger.getLogger("StringLenValidator");
	
	private int minLen = 1; // default minimum string length
	private int maxLen = 10; // default maximum string length

	public boolean validateStringIf(String string) throws StringSizeException {
		if (!checkStringLen(string)) {
			throw new StringSizeException("The length of the string " + string + 
					" is outside of the allowed range. (MIN: " + this.minLen +
					" MAX: " + this.maxLen + ") (If)");
		} 
		else {
			return true;
		}
	}
	
	public boolean validateStringAssert(String string) 
			throws StringSizeException {
		
		try {
			assert checkStringLen(string);
		}
		catch (AssertionError e) {
			throw new StringSizeException("The length of the string " + string + 
					" is outside of the allowed range. (MIN: " + this.minLen +
					" MAX: " + this.maxLen + ") (Assert)");
		}
		return true;
	}
	
	private boolean checkStringLen(String string) {
		Integer stringLen = string.length();
		if (stringLen < this.minLen || stringLen > this.maxLen) {
			return false;
		}
		return true;
	}
	
	public void setMaxLen(Integer maxLen) {
		this.maxLen = maxLen;
	}
	
	public void setMinLen(Integer minLen) {
		this.minLen = minLen;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		StringLenValidator slv = new StringLenValidator();
		
		String inputString;
	
		// If successful test
		slv.setMinLen(1);
		slv.setMaxLen(5);	
		inputString = "Shane";
		try {
			slv.validateStringIf(inputString);
			System.out.println(inputString + " is within range. (If)");
		} 
		catch(StringSizeException e) {
			System.out.println(e.toString());
		}
		
		//If failure test
		slv.setMinLen(3);
		slv.setMaxLen(5);
		inputString = "Sh";
		try {
			slv.validateStringIf(inputString);
			System.out.println(inputString + " is within range. (If)");
		} 
		catch(StringSizeException e) {
			System.out.println(e.toString());
		}
		
		// Assertion successful test
		slv.setMinLen(1);
		slv.setMaxLen(5);	
		inputString = "Shane";
		try {
			slv.validateStringAssert(inputString);
			System.out.println(inputString + " is within range. (Assert)");
		}
		catch(StringSizeException e) {
			System.out.println(e.toString());
		}
		
		
		// Assertion failure test
		slv.setMinLen(1);
		slv.setMaxLen(4);	
		inputString = "Shane";
		try {
			slv.validateStringAssert(inputString);
			System.out.println(inputString + " is within range. (Assert)");
		}
		catch(StringSizeException e) {
			System.out.println(e.toString());
		}

	}

}

