/**
 * Thrown when the length of a string is outside of a predetermined range.
 *  
 * @author Shane Kumpf
 * @verion 0.1 8/29/12
 * @since 1.6
 *
 */
public class StringSizeException extends Exception {
	
	/**
	 * Provides exception handling for strings outside of a provided length.
	 */
	public StringSizeException() {}
	
	/**
	 * Provides exception handling for strings outside of a provided length allowing
	 * a user provided method.
	 * 
	 * @param msg	detailed exception message
	 */
	public StringSizeException(String msg) {
		super(msg);
	}
	
	/**
	 * Provides exception handling for strings outside of a provided length 
	 * allowing a user provided message and a @{link Throwable} for propagation.
	 * 
	 * @param msg 	detailed exception message
	 * @param t		exception instance for propagation.
	 */
	public StringSizeException(String msg, Throwable t) {
		super(msg, t);
	}
	
	/**
	 * Provides exception handling for strings outside of a provided length
	 * allowing a @{link Throwable} for propagation.
	 * 
	 * @param t		exception instance for propagation.
	 */
	public StringSizeException(Throwable t) {
		super(t);
	}

}
