import java.io.*;

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
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
			return false;
		}
		return true;
	}

	/**
	 * Reads the words "true" and "false" from the keyboard
	 */
	public static final boolean readBoolean() {
		String input = null;
		input = readLine();
		if (input.equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}

	/**
	 * Reads a byte value (between -128 and 127) from the keyboard. Re-prompts
	 * for data on any invalid entry.
	 */
	public static final byte readByte() {
		Integer input = 1;
		try {
			input = Integer.parseInt(readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace(System.err);
			System.err.println("Invalid entry, please retry");
			readByte();
		}
		if (input < -128 || input > 127) {
			System.err.println("Invalid entry, please retry");
			readByte();
		}
		return input.byteValue();
	}

	/**
	 * Reads a single character from the keyboard. Re-prompts for data on any
	 * invalid entry.
	 */
	public static final char readChar() {
		char[] input;
		input = readLine().toCharArray();
		if (input.length > 1 || Character.isDigit(input[0])) {
			System.err.println("Invalid entry, please retry");
			readChar();
		}
		return input[0];
	}

	/**
	 * Reads a double value from the keyboard. Re-prompts for data on any
	 * invalid entry.
	 */
	public static final double readDouble() {
		return 1;
	}

	/**
	 * Reads a floating point value from the keyboard. Re-prompts for data on
	 * any invalid entry.
	 */
	public static final float readFloat() {
		return 1.0f;
	}

	/**
	 * Reads an integer value from the keyboard. Re-prompts for data on any
	 * invalid entry.
	 */
	public static final int readInt() {
		return 1;
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
				readLine();
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.err.println("Invalid entry, please retry");
			readLine();
		}
		return input;
	}

	/**
	 * Reads a long integer value from the keyboard. Re-prompts for data on any
	 * invalid entry.
	 */
	public static final long readLong() {
		return 1;
	}

	/**
	 * Reads a short integer value from the keyboard. Re-prompts for data on any
	 * invalid entry.
	 */
	public static final short readShort() {
		return 1;
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

		System.out.print("Enter a String: ");
		String testLine = readLine();
		System.out.println(testLine);
	}

}
