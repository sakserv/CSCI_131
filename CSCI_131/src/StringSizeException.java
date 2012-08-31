/**
 * Thrown when the length of a string is outside of a predetermined range.
 *  
 * @author Shane Kumpf
 * @verion 0.1
 * @
 *
 */
public class StringSizeException extends Exception {
	
	public StringSizeException() {}
	
	public StringSizeException(String msg) {
		super(msg);
	}
	
	public StringSizeException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public StringSizeException(Throwable t) {
		super(t);
	}

}
