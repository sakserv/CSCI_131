package com.shanekumpf;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 * Demonstrate the various log levels available in 
 * {@link java.util.logging.Logger}.
 * 
 * @author Shane Kumpf
 * @version 0.1
 * @since 1.6
 */
public class LogLevels {

	Logger logger;
	
	/**
	 * Provides a default logger instance
	 * 
	 */
	public LogLevels() {
		logger = Logger.getLogger("LogLevels");
	}
	
	/**
	 * Sets the log level threshold, messages under this threshold are not
	 * logged.
	 * 
	 * @param Level		log level
	 * @see Level
	 */
	public void setLogLevel(Level logLevel) {
		logger.setLevel(logLevel);
	}
	
	/**
	 * Logs a {@link Level.SEVERE} level message.
	 * @see Level
	 */
	public void logSevere() {
		logger.severe("SEVERE entry");
	}
	
	/**
	 * Logs a {@link Level.WARNING} level message.
	 * @see Level
	 */
	public void logWarning() {
		logger.warning("WARNING entry");
	}
	
	/**
	 * Logs an {@link Level.INFO} level message.
	 * @see Level
	 */
	public void logInfo() {
		logger.info("INFO entry");
	}
	
	/**
	 * Logs a {@link Level.CONFIG} level message.
	 * @see Level
	 */
	public void logConfig() {
		logger.config("CONFIG entry");
	}
	
	/**
	 * Logs an {@link Level.FINE} level message.
	 * @see Level
	 */
	public void logFine() {
		logger.fine("FINE entry");
	}
	
	/**
	 * Logs a {@link Level.FINER} level message.
	 * @see Level
	 */
	public void logFiner() {
		logger.finer("FINER entry");
	}
	
	/**
	 * Logs a {@link Level.FINEST} level message.
	 * @see Level
	 */
	public void logFinest() {
		logger.finest("FINEST entry");
	}
}
