package com.jorgeq.belatrix.logger;

/**
 *
 * @author Jorge Quintero
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.util.logging.Handler;

public class JobLogger {

    private boolean logToFile;
    private boolean logToConsole;
    private boolean logToDatabase;

    private boolean logMessage;
    private boolean logWarning;
    private boolean logError;

    private static Logger logger;
    private static Properties properties;

    public JobLogger(ParamLoggerDTO paramsLoggerDTO) {

        logger = Logger.getLogger("JobLogger");
        logError = paramsLoggerDTO.isLogErrorParam();
        logMessage = paramsLoggerDTO.isLogMessageParam();
        logWarning = paramsLoggerDTO.isLogWarningParam();

        logToDatabase = paramsLoggerDTO.isLogToDatabaseParam();
        logToFile = paramsLoggerDTO.isLogToFileParam();
        logToConsole = paramsLoggerDTO.isLogToConsoleParam();

        properties = loadConfig();

    }

    /**
     * This method allows register a message acordign to sent params.
     *
     * @param messageDTO
     * @throws ErrorLoggerException
     */
    public void logMessage(MessageDTO messageDTO)
            throws ErrorLoggerException {

        Handler[] handlers = logger.getHandlers();
        handlers.clone();
        String messageText = messageDTO.getMessage().trim();
        Level level = homologateLevel(messageDTO.getTipoMessage());

        if (messageText == null || messageText.length() == 0) {
            return;
        }
        if (!logToConsole && !logToFile && !logToDatabase) {
            throw new ErrorLoggerException("Invalid configuration");
        }

        if (!validateTypeMessage(level)) {
            throw new ErrorLoggerException("Invalid option for log");
        }

        if (!logError && !logMessage && !logWarning) {
            throw new ErrorLoggerException("Error , Warning or Message must be specified");
        }

        ConsoleHandler ch = new ConsoleHandler();
        if (logToConsole) {
            logger.addHandler(ch);
        }

        FileHandler fileHandler = generateFileHandler();

        if (logToFile && fileHandler != null) {
            logger.addHandler(fileHandler);
        }

        if (logToConsole) {
            logger.addHandler(ch);
        }

        logger.log(level, messageText);

        if (logToDatabase) {
            registerToDataBase(messageText, messageDTO.getTipoMessage());
        }

    }

    /**
     * Homologate the message type with a Level equivalent from Logger
     *
     * @param messageType
     * @return
     */
    private Level homologateLevel(MessageTypeEnum messageType) {
        Level level = Level.INFO;
        switch (messageType) {
            case MESSAGE:
                level = Level.INFO;
                break;
            case ERROR:
                level = Level.SEVERE;
                break;
            case WARNING:
                level = Level.WARNING;
                break;
        }
        return level;
    }

    /**
     * Create a FileHandler to be add in ConsoleManager
     *
     * @return
     * @throws ErrorLoggerException
     */
    private FileHandler generateFileHandler() throws ErrorLoggerException {
        FileHandler fh = null;
        String filePathName = properties.getProperty("bel.logFileFolder") + properties.getProperty("bel.logFile");
        File logFile = new File(filePathName);
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException ex) {
                throw new ErrorLoggerException("An error has ocurred with file management: " + ex.getMessage());
            }
        }

        try {
            fh = new FileHandler(filePathName);
        } catch (IOException | SecurityException ex) {
            throw new ErrorLoggerException("An error has ocurred with file handler: " + ex.getMessage());
        }
        return fh;
    }

    /**
     * Validate if the message type of sented message, has been selected to log
     *
     * @param level
     * @return
     */
    public boolean validateTypeMessage(Level level) {

        if (logMessage && level.getName().equals("INFO")) {
            return true;
        }
        if (logWarning && level.getName().equals("WARNING")) {
            return true;
        }
        if (logError && level.getName().equals("SEVERE")) {
            return true;
        }
        return false;
    }

    /**
     * Load information from a properties file
     *
     * @return
     */
    private Properties loadConfig() {
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream("config.properties");
            prop.load(input);
        } catch (IOException ex) {
            Logger.getLogger(JobLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prop;
    }

    private void registerToDataBase(String messageText,
            MessageTypeEnum tipoMessage) throws ErrorLoggerException {
        Connection connection = null;
        try {
            String urlConnection = "jdbc:" + properties.getProperty("bel.dbms") + "://"
                    + properties.getProperty("bel.serverName")
                    + ":" + properties.getProperty("bel.portNumber") + "/" + properties.getProperty("bel.database");
            connection = DriverManager.getConnection(urlConnection,
                    properties.getProperty("bel.user"),
                    properties.getProperty("bel.password"));
        } catch (SQLException ex) {
            throw new ErrorLoggerException("An error has ocurred in database connection: " + ex.getMessage());
        }
        Statement stmt;
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            throw new ErrorLoggerException("An error has ocurred creating SQL statement: " + ex.getMessage());
        }

        try {
            stmt.executeUpdate("insert into Log_Values(message,typemessage) VALUES ('" + messageText + "', '" + tipoMessage.name() + "')");
        } catch (SQLException ex) {
            throw new ErrorLoggerException("An error has ocurred wile inserting data: " + ex.getMessage());
        }
    }
}
