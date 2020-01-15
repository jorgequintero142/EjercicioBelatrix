package com.jorgeq.belatrix.gui;

import com.jorgeq.belatrix.logger.MessageTypeEnum;
import com.jorgeq.belatrix.logger.ParamLoggerDTO;
import java.util.Scanner;

/**
 * Class to manage GUI
 * @author Jorge Quintero
 */
public final class LoggerGUI {

    private String message;
    private MessageTypeEnum typeMessage;
    private final Scanner scanner;
    private final ParamLoggerDTO params;

    
        public LoggerGUI() {
        scanner = new Scanner(System.in);
        params = new ParamLoggerDTO();
    }
    /**
     * Show GUI to the user. It's a  classic console, to capture
     * information that user will insert
     */
    public void showConsole() {
        defineMessage();
        defineMessageType();
        defineTypesToLog();
    }


   /**
     * Catch the message typed to user, it will be stored in Console,
     * File or database
     */
    private void defineMessage() {
        System.out.println("Enter Message to log: ");
        message = scanner.nextLine();
        message = message.trim();
        if (message.length() == 0) {
            System.out.println("You must type a message");
            System.out.println("Type 9! to exit ");
            defineMessage();
        } else if (message.equals("9!")) {
            System.exit(0);
        }
        System.out.println("Message is: " + message);

    }

  /**
 * Show avaible options of allowed message types, and catch user selection
 */
    private void defineMessageType() {
        System.out.println("Selected the message type: ");
        System.out.println("1. Warning ");
        System.out.println("2. Message ");
        System.out.println("3. Error ");
        int typeMessageTMP = scanner.nextInt();
        System.out.println("You selected: " + typeMessageTMP);

        if (typeMessageTMP == 9) {
            System.out.println("You did not selected message type");
            System.exit(0);
        } else if (!(1 <= typeMessageTMP && typeMessageTMP <= 3)) {
            System.out.println("Selected the correct message type(1, 2 o 3) ");
            System.out.println("Type 9 to exit ");
            defineMessageType();
        } else {

            switch (typeMessageTMP) {
                case 1:
                    typeMessage = MessageTypeEnum.WARNING;
                    break;

                case 2:
                    typeMessage = MessageTypeEnum.MESSAGE;
                    break;

                case 3:
                    typeMessage = MessageTypeEnum.ERROR;
                    break;
            }
        }
    }
/**
 * Show avaible options about type messages, and catch user selection
 */
    private void defineTypesToLog() {
        System.out.println("Selected one or more types messages to Log ");
        System.out.println("1. Message  ");
        System.out.println("2. Error  ");
        System.out.println("3. Warning  ");
        System.out.println("Type any character to stop ");

        boolean isSomefalse = false;
        while (scanner.hasNextInt()) {
            int opc = scanner.nextInt();
            switch (opc) {
                case 1:
                    params.setLogMessageParam(true);
                    isSomefalse = true;
                    break;
                case 2:
                    params.setLogErrorParam(true);
                    isSomefalse = true;
                    break;
                case 3:
                    params.setLogWarningParam(true);
                    isSomefalse = true;
                    break;
                default:
                    break;
            }
        }
        if (!isSomefalse) {
            System.out.println("You must to select at least one option! Default options is selected");
            params.setLogMessageParam(true);
        }

    }

    /**
     * return the type messaged typed by user
     * @return 
     */
    public MessageTypeEnum getMessageType() {
        return typeMessage;
    }

    /**
     * Return message typed by user
     * @return 
     */
    public String getMessage() {
        return message;
    }

    /**
     * return a Object od class ParamLoggerDTO,
     * with params about type message allowed.
     * @return 
     */
    public ParamLoggerDTO getParams() {
        return params;
    }

}
