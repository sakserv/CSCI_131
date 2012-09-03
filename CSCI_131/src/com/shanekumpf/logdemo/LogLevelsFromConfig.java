package com.shanekumpf.logdemo;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.InputStream;
/**
 * Provides logging facilities using an external configuration from the default
 * package.
 * 
 * @author Shane Kumpf
 * @version 0.1
 * @since 1.6
 *
 */
public class LogLevelsFromConfig extends LogLevels {

	Logger logger;
	final InputStream inputStream = getClass().getResourceAsStream("/logging.properties");
	
	/**
	 * Reads in the logginig properties from the default package and sets up
	 * a default logger.
	 */
	public LogLevelsFromConfig() {
		try {
			LogManager.getLogManager().readConfiguration(inputStream);
		} catch(IOException e) {
			Logger.getAnonymousLogger().severe("Could not load logging.properties" + e.getMessage());
		}
		logger = Logger.getLogger("com.shanekumpf");
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
