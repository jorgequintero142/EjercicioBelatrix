package com.jorgeq.belatrix.logger;

/**
 * Class to manage errors that could happen
 *
 * @author jorge.quintero
 */
public class ErrorLoggerException extends Exception {

    public ErrorLoggerException(String msg) {
        super(msg);
    }
}
