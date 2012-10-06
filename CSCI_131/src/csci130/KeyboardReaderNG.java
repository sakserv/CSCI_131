package csci130;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Title: CSCI 130 Basic data entry class.
 * 
 * Description: This class provides a set of methods which read from the
 * keyboard and return specific values. It is inteded for use by CSCI 130
 * students as part of the regular class, lab, and homework programming
 * assignments.
 * 
 * Copyright Heartland Community College(c) 2003
 * 
 * @author Shane Kumpf
 * @version 0.1 9/27/12
 * @since 1.6
 */

public class KeyboardReaderNG {

	static final DataInputStream dis;
	static final BufferedReader br;

	static {
		dis = new DataInputStream(System.in);
		br = new BufferedReader(new InputStreamReader(dis));
	}

	/**
	 * Returns a true if data is waiting to be read from the keyboard, or false
	 * if the user has not yet typed anything..
	 */
	public static final boolean isDataAvailable() {
		try {
			if (System.in.available() < 0) {
				return false;
			} else {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
			return false;
		}
	}

	/**
	 * Reads the words "true" and "false" from the keyboard. 
	 */
	public static final boolean readBoolean() {
		// true if user enters true, otherwise false.
		String input = null;
		input = readLine();
		if (input.equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Reads a byte value (between -128 and 127) from the keyboard. Re-prompts
	 * for data on any invalid entry.
	 */
	public static final byte readByte() {
		
		// Get input from the user
		Integer input = null;
		try {
			input = Integer.parseInt(readLine());
		} catch (NumberFormatException e) {
			System.err.println("Invalid entry, please retry");
			return readByte();
		}
		
		// byte is expected to be between -128 and 127
		if (input < -128 || input > 127) {
			System.err.println("Invalid entry, please retry");
			return readByte();
		} else {
			try {
				return input.byteValue();
			} catch (NullPointerException npe) {
				System.err.println("Invalid entry, please retry");
				return readByte();
			}
		}
	}

	/**
	 * Reads a single character from the keyboard. Re-prompts for data on any
	 * invalid entry.
	 */
	public static final char readChar() {
		char[] input = null;
		input = readLine().toCharArray();
		if (input.length > 1) {
			System.err.println("Invalid entry, please retry"); 
			return readChar();
		} else if(Character.isDigit(input[0])) {
			System.err.println("Invalid entry, please retry"); 
			return readChar();
		} else {
			return input[0];
		}
	}

	/**
	 * Reads a double value from the keyboard. Re-prompts for data on any
	 * invalid entry.
	 */
	public static final double readDouble() {
		double input = Double.NaN;
		try {
			input = Double.parseDouble(readLine());
		} catch (NumberFormatException e) {
			System.err.println("Invalid entry, please retry");
			return readDouble();
		}
		return input;
	}

	/**
	 * Reads a floating point value from the keyboard. Re-prompts for data on
	 * any invalid entry.
	 */
	public static final float readFloat() {
		float input = Float.NaN;
		try {
			input = Float.parseFloat(readLine());
		} catch (NumberFormatException e) {
			System.err.println("Invalid entry, please retry");
			return readFloat();
		}
		return input;
	}

	/**
	 * Reads an integer value from the keyboard. Re-prompts for data on any
	 * invalid entry.
	 */
	public static final int readInt() {
		int input = 0;
		try {
			input = Integer.parseInt(readLine());
		} catch (NumberFormatException e) {
			System.err.println("Invalid entry, please retry");
			return readInt();
		}
		return input;
	}

	/**
	 * Reads a string of text from the keyboard. Re-prompts for data on any
	 * invalid entry.
	 */
	public static final String readLine() {
		String input = "";
		try {
			input = br.readLine();
			if (input.trim().isEmpty() || input.equals(null)) {
				System.err.println("Invalid entry, please retry");
				return readLine();
			} else {
				return input;
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.err.println("Invalid entry, please retry");
			return readLine();
		}
	}

	/**
	 * Reads a long integer value from the keyboard. Re-prompts for data on any
	 * invalid entry.
	 */
	public static final long readLong() {
		long input = 0;
		try {
			input = Long.parseLong(readLine());
		} catch (NumberFormatException e) {
			System.err.println("Invalid entry, please retry");
			return readLong();
		}
		return input;
	}

	/**
	 * Reads a short integer value from the keyboard. Re-prompts for data on any
	 * invalid entry.
	 */
	public static final short readShort() {
		short input = 0;
		try {
			input = Short.parseShort(readLine());
		} catch (NumberFormatException e) {
			System.err.println("Invalid entry, please retry");
			return readShort();
		}
		return input;
	}

	/**
	 * This main is only provided to test the input methods of the class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.print("Enter a boolean: ");
		boolean testBoolean = readBoolean();
		System.out.println(testBoolean);

		System.out.print("Enter a byte: ");
		byte testByte = readByte();
		System.out.println(testByte);

		System.out.print("Enter a char: ");
		char testChar = readChar();
		System.out.println(testChar);
		
		System.out.print("Enter a double: ");
		double testDouble = readDouble();
		System.out.println(testDouble);
		
		System.out.print("Enter a float: ");
		float testFloat = readFloat();
		System.out.println(testFloat);
		
		System.out.print("Enter an int: ");
		int testInt = readInt();
		System.out.println(testInt);

		System.out.print("Enter a String: ");
		String testLine = readLine();
		System.out.println(testLine);
		
		System.out.print("Enter a long: ");
		long testLong = readLong();
		System.out.println(testLong);
		
		System.out.print("Enter a short: ");
		short testShort = readShort();
		System.out.println(testShort);
	}

}
