package com.jorgeq.belatrix.gui;

import com.jorgeq.belatrix.logger.MessageTypeEnum;
import com.jorgeq.belatrix.logger.ParamLoggerDTO;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jorge Quintero
 */
public final class LoggerGUI {

    private String message;
    private MessageTypeEnum typeMessage;
    private final Scanner scanner;
    private final ParamLoggerDTO params;

    public void showConsole() {
        defineMessage();
        defineMessageType();
        defineTypesToLog();
    }

    public LoggerGUI() {
        scanner = new Scanner(System.in);
        params = new ParamLoggerDTO();
    }

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

    private void defineMessageType() {
        System.out.println("Selected the message type: ");
        System.out.println("1. Warning ");
        System.out.println("2. Message ");
        System.out.println("3. Error ");
        int typeMessageTMP = scanner.nextInt();
        System.out.println("You selected: " + typeMessage);

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

    public void defineTypesToLog() {
        System.out.println("Selected one or more types messages to Log ");
        System.out.println("1. Message  ");
        System.out.println("2. Error  ");
        System.out.println("3. Warning  ");
        System.out.println("Type another number or text to stop ");

        boolean isSometrue = true;
        while (scanner.hasNextInt()) {
            int opc = scanner.nextInt();
            switch (opc) {
                case 1:
                    params.setLogMessageParam(true);
                    break;
                case 2:
                    params.setLogErrorParam(true);
                    break;
                case 3:
                    params.setLogWarningParam(true);
                    break;
                default:
                    isSometrue = false;
                    break;
            }
        }
        if (!isSometrue) {
            System.out.println("You must to select at least one option! Default options is selected");
            params.setLogMessageParam(true);
        }

    }

    public MessageTypeEnum getMessageType() {
        return typeMessage;
    }

    public String getMessage() {
        return message;
    }

    public ParamLoggerDTO getParams() {
        return params;
    }

}
