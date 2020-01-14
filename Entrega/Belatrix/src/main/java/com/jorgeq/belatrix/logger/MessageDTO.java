package com.jorgeq.belatrix.logger;

/**
 *
 * @author jorge.quintero
 */
public class MessageDTO {
   private String message;
   private MessageTypeEnum tipoMessage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageTypeEnum getTipoMessage() {
        return tipoMessage;
    }

    public void setTipoMessage(MessageTypeEnum tipoMessage) {
        this.tipoMessage = tipoMessage;
    }  
}
