package com.jorgeq.belatrix;

import com.jorgeq.belatrix.logger.ErrorLoggerException;
import com.jorgeq.belatrix.logger.JobLogger;
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
        parametros.setLogWarningParam(true);
        JobLogger jl = new JobLogger(parametros);
        try {
            jl.logMessage("Hola Mundo");
            
            /*System.out.println("==>"+DateFormat.getDateInstance(DateFormat.LONG).format(new Date()));*/
        } catch (ErrorLoggerException ex) {
            Logger.getLogger(LoggerAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
