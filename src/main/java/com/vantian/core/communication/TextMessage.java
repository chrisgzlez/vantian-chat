package com.vantian.core.communication;

import java.security.InvalidParameterException;

/**
 * Message
 */
public class TextMessage implements IMessage {
    String mssg;

    public TextMessage(String text) throws InvalidParameterException {
        if (text == null) {
           throw new InvalidParameterException(" [x] Contents of message cant be null"); 
        }
        this.mssg = text;
    }

    public String get() {
        return this.mssg;
    }

    
}
