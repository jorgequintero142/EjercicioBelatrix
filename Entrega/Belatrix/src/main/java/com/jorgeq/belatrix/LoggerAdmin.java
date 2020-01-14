package com.jorgeq.belatrix;

import com.jorgeq.belatrix.logger.ErrorLoggerException;
import com.jorgeq.belatrix.logger.JobLogger;
import com.jorgeq.belatrix.logger.MessageDTO;
import com.jorgeq.belatrix.logger.MessageTypeEnum;
import com.jorgeq.belatrix.logger.ParamLoggerDTO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Quintero
 */
public class LoggerAdmin {

    public static void main(String args[]) {

        ParamLoggerDTO parametros = new ParamLoggerDTO();
        parametros.setLogToFileParam(true);
       // parametros.setLogToConsoleParam(true);
       // parametros.setLogWarningParam(true);
        parametros.setLogMessageParam(true);
        
        parametros.setLogToDatabaseParam(true);
        JobLogger jl = new JobLogger(parametros);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage("Mensaje Prueba PARA IRME");
        messageDTO.setTipoMessage(MessageTypeEnum.MESSAGE);

        try {
            jl.logMessage(messageDTO);

        } catch (ErrorLoggerException ex) {
            Logger.getLogger(LoggerAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
