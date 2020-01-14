package com.jorgeq.belatrix.logger;

/**
 *
 * @author Jorge Quintero
 */
import com.jorgeq.belatrix.LoggerAdmin;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;

public class JobLogger {

    private boolean logToFile;
    private boolean logToConsole;
    private boolean logMessage;
    private boolean logWarning;
    private boolean logError;
    private boolean logToDatabase;

    //private static Map dbParams;
    private static Logger logger;
    private static Properties properties;

    public JobLogger(ParamLoggerDTO paramsLoggerDTO) {

        logger = Logger.getLogger("MyLog");
        logError = paramsLoggerDTO.isLogErrorParam();
        logMessage = paramsLoggerDTO.isLogMessageParam();
        logWarning = paramsLoggerDTO.isLogWarningParam();
        logToDatabase = paramsLoggerDTO.isLogToDatabaseParam();
        logToFile = paramsLoggerDTO.isLogToFileParam();
        logToConsole = paramsLoggerDTO.isLogToConsoleParam();
        properties = loadConfig();

    }

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

    public void logMessage(String messageText)
            throws ErrorLoggerException {
        messageText.trim();
        if (messageText == null || messageText.length() == 0) {
            return;
        }
        if (!logToConsole && !logToFile && !logToDatabase) {
            throw new ErrorLoggerException("Invalid configuration");
        }
        if (!logError && !logMessage && !logWarning) {
            throw new ErrorLoggerException("Error or Warning or Message must be specified");
        }
        Connection connection = null;

        /*  Properties connectionProps = new Properties();
        connectionProps.put("user", dbParams.get("userName"));
        connectionProps.put("password", dbParams.get("password"));
        try {
            connection = DriverManager.getConnection("jdbc:" + dbParams.get("dbms") + "://"
                    + dbParams.get("serverName")
                    + ":" + dbParams.get("portNumber") + "/", connectionProps);
        } catch (SQLException ex) {
            throw new ErrorLoggerException("An error has ocurred in database connection: " + ex.getMessage());
        }
         */
        int t = 0;
        /* if (message && logMessage) {
            t = 1;
        }
        if (error && logError) {
            t = 2;
        }
        if (warning && logWarning) {
            t = 3;
        }
        Statement stmt;
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            throw new ErrorLoggerException("An error has ocurred creating SQL statement: " + ex.getMessage());
        }*/
        String l = null;
        String filePathName = properties.getProperty("bel.logFileFolder") +properties.getProperty("bel.logFile");
        File logFile = new File(filePathName);
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException ex) {  
                
                
                throw new ErrorLoggerException("An error has ocurred with file management: " + ex.getMessage());
            }
        }
        FileHandler fh;
     
        try {
            fh = new FileHandler(filePathName);
        } catch (IOException | SecurityException ex) {
            throw new ErrorLoggerException("An error has ocurred with file handler: " + ex.getMessage());
        }
        ConsoleHandler ch = new ConsoleHandler();
        /* if (error && logError) {
            l = l + "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date())
                    + messageText;
        }
        if (warning && logWarning) {
            l = l + "warning " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date())
                    + messageText;
        }
        if (message && logMessage) {
            l = l + "message " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date())
                    + messageText;
        }*/
        if (logToFile) {
            logger.addHandler(fh);
            logger.log(Level.INFO, messageText);
        }

        if (logToConsole) {
            logger.addHandler(ch);
            logger.log(Level.INFO, messageText);
        }
        /*if (logToDatabase) {
            try {
                stmt.executeUpdate("insert into Log_Values('" + message + "', " + String.valueOf(t) + ")");
            } catch (SQLException ex) {
                throw new ErrorLoggerException("An error has ocurred wile inserting data: " + ex.getMessage());
            }
        }*/
    }
}
