import java.util.logging.Logger;


public class LogLevelsDriver {

   /**
	* Demonstrates each of the log levels.
	* 
	* @param args 	command line arguments.
	*/
	public static void main(String[] args) {
		LogLevels logLevels = new LogLevels();
		logLevels.logSevere();
		logLevels.logWarning();
		logLevels.logInfo();
		logLevels.logConfig();
		logLevels.logFine();
		logLevels.logFiner();
		logLevels.logFinest();
		
		Logger logger = Logger.getLogger("LogLevelsFromConfig");
		LogLevels logLevelsFromConfig = new LogLevelsFromConfig(logger);
		logLevelsFromConfig.logSevere();
			
	}

}

