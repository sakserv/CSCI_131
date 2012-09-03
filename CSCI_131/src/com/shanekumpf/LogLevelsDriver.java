package com.shanekumpf;
/**
 * Drives the logging demonstration using a default and externally provided
 * logger configuration.
 * 
 * @author Shane Kumpf
 * @version 0.1
 * @since 1.6
 *
 */

public class LogLevelsDriver {

   /**
	* Demonstrates each of the log levels.
	* 
	* @param args 	command line arguments.
	*/
	public static void main(String[] args) {
		
		// Default logging instance
		LogLevels logLevels = new LogLevels();
		logLevels.logSevere();
		logLevels.logWarning();
		logLevels.logInfo();
		logLevels.logConfig();
		logLevels.logFine();
		logLevels.logFiner();
		logLevels.logFinest();
		
		// From external configuration
		LogLevelsFromConfig logLevelsFromConfig = new LogLevelsFromConfig();
		logLevelsFromConfig.logSevere();
		logLevelsFromConfig.logWarning();
		logLevelsFromConfig.logConfig();
		logLevelsFromConfig.logInfo();
		logLevelsFromConfig.logFine();
		logLevelsFromConfig.logFiner();
		logLevelsFromConfig.logFinest();
			
	}

}

