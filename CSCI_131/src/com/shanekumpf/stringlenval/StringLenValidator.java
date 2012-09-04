package com.shanekumpf.stringlenval;

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
	
	private int minLen = 1; // default minimum string length
	private int maxLen = 10; // default maximum string length

	/**
	 * Using conditionals determine if a string length is within a given range.
	 * 
	 * @param string	string to be validated
	 * @return boolean	false if string len is out of range.
	 * @throws StringSizeException
	 */
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
	
	/**
	 * Using assertions determine if a string length is within a given range.
	 * 
	 * @param string	string to be validated
	 * @return boolean	false if string len is out of range.
	 */
	public boolean validateStringAssert(String string) { 
		
		assert checkStringLen(string) : "The length of the string " + 
				string + " is outside of the allowed range. (MIN: " + 
				this.minLen + " MAX: " + this.maxLen + ") (Assert)";
		return true;
	}
	
	/**
	 * Validates the string length is within the min and max values.
	 * 
	 * @param string	string to be validated
	 * @return boolean	false if string length is outside of the provided range.
	 */
	private boolean checkStringLen(String string) {
		Integer stringLen = string.length();
		if (stringLen < this.minLen || stringLen > this.maxLen) {
			return false;
		}
		return true;
	}
	
	/**
	 * Max string length setter.
	 * 
	 * @param maxLen	max string length
	 */
	public void setMaxLen(Integer maxLen) {
		this.maxLen = maxLen;
	}
	
	/**
	 * Min string length setter.
	 * 
	 * @param minLen	min string length
	 */
	public void setMinLen(Integer minLen) {
		this.minLen = minLen;
	}
	
	/**
	 * Tests various string sizes against the conditional and assertion based
	 * checking.
	 * 
	 * @param args 	cmdline args
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
		slv.validateStringAssert(inputString);
		System.out.println(inputString + " is within range. (Assert)");

		
		
		// Assertion failure test
		slv.setMinLen(1);
		slv.setMaxLen(3);	
		inputString = "Shane";
		slv.validateStringAssert(inputString);
		System.out.println(inputString + " is within range. (Assert)");

	}

}

