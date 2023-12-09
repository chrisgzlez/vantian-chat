package com.vantian.core.communication;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.InvalidParameterException;

/**
 * Message
 */
public class TextMessage extends UnicastRemoteObject implements IMessage {
    String mssg;

    public TextMessage(String text) throws RemoteException {
        this.mssg = text;
    }

    public String get() throws RemoteException {
        return this.mssg;
    }

    
}
