package com.jorgeq.belatrix;

import com.jorgeq.belatrix.logger.ErrorLoggerException;
import com.jorgeq.belatrix.logger.JobLogger;
import com.jorgeq.belatrix.logger.MessageDTO;
import com.jorgeq.belatrix.logger.MessageTypeEnum;
import com.jorgeq.belatrix.logger.ParamLoggerDTO;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Quintero
 */
public class LoggerAdmin {

    public static void main(String args[]) {
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Enter Message to log");
    String userName = myObj.nextLine();  // Read user input
    System.out.println("Username is: " + userName); 
    
    System.out.println("Selected the message type"); 
    System.out.println("1. Warning ");     
    System.out.println("2. Message ");     
    System.out.println("3. Error ");
    String typeMessage = myObj.nextLine();  // Read user input
    System.out.println("You selected: " + typeMessage); 
        /*
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
        }*/

    }
}
