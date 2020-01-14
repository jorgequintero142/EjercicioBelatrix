/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jorgeq.belatrix.logger;

/**
 *
 * @author Luisa Quintero
 */
public class ParamLogger {

    public boolean logToFileParam;
    public boolean logToConsoleParam;
    public boolean logMessageParam;
    public boolean logWarningParam;
    private boolean logErrorParam;
    private boolean logToDatabaseParam;

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
