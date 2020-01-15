package com.jorgeq.belatrix;

import com.jorgeq.belatrix.gui.LoggerGUI;
import com.jorgeq.belatrix.logger.ErrorLoggerException;
import com.jorgeq.belatrix.logger.JobLogger;
import com.jorgeq.belatrix.logger.MessageDTO;
import com.jorgeq.belatrix.logger.ParamLoggerDTO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Quintero
 */
public class LoggerAdmin {

    public static void main(String args[]) {

        LoggerGUI logGUI = new LoggerGUI();
        logGUI.showConsole();

        ParamLoggerDTO parametros = logGUI.getParams();
        parametros.setLogToFileParam(true);
        parametros.setLogToConsoleParam(true);
        parametros.setLogToDatabaseParam(true);
        JobLogger jl = new JobLogger(parametros);

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(logGUI.getMessage());
        messageDTO.setTipoMessage(logGUI.getMessageType());

        try {
            jl.logMessage(messageDTO);

        } catch (ErrorLoggerException ex) {
            Logger.getLogger(LoggerAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
