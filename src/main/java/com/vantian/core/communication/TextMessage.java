package com.vantian.core.communication;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Message
 */
public class TextMessage extends UnicastRemoteObject implements IMessage {
    private String mssg;
    private String time; 

    public TextMessage(String text) throws RemoteException {
        this.mssg = text;

        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.time = currentTime.format(formatter);

    }

    public String get() throws RemoteException {
        return this.mssg;
    }

    public String getTime() throws RemoteException {
        return this.time;
    }

    
}
