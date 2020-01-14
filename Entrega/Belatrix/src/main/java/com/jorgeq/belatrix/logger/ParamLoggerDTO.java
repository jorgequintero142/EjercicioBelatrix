package com.jorgeq.belatrix.logger;

/**
 *
 * @author Luisa Quintero
 */
public class ParamLoggerDTO {

    //Use to define whether log to a file
    public boolean logToFileParam;
    //Use to define whether log to console
    public boolean logToConsoleParam;
    //Use to define whether log to database
    private boolean logToDatabaseParam;
    //Use to define type of messagge (messagge)
    public boolean logMessageParam;
    //Use to define type of messagge (warning)
    public boolean logWarningParam;
    //Use to define type of messagge (error)
    private boolean logErrorParam;

    public boolean isLogToFileParam() {
        return logToFileParam;
    }

    public void setLogToFileParam(boolean logToFileParam) {
        this.logToFileParam = logToFileParam;
    }

    public boolean isLogToConsoleParam() {
        return logToConsoleParam;
    }

    public void setLogToConsoleParam(boolean logToConsoleParam) {
        this.logToConsoleParam = logToConsoleParam;
    }

    public boolean isLogMessageParam() {
        return logMessageParam;
    }

    public void setLogMessageParam(boolean logMessageParam) {
        this.logMessageParam = logMessageParam;
    }

    public boolean isLogWarningParam() {
        return logWarningParam;
    }

    public void setLogWarningParam(boolean logWarningParam) {
        this.logWarningParam = logWarningParam;
    }

    public boolean isLogErrorParam() {
        return logErrorParam;
    }

    public void setLogErrorParam(boolean logErrorParam) {
        this.logErrorParam = logErrorParam;
    }

    public boolean isLogToDatabaseParam() {
        return logToDatabaseParam;
    }

    public void setLogToDatabaseParam(boolean logToDatabaseParam) {
        this.logToDatabaseParam = logToDatabaseParam;
    }

}
