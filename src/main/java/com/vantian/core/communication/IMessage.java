package com.vantian.core.communication;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * IMessage
 */
public interface IMessage extends Remote {
    public String get() throws RemoteException;
}
