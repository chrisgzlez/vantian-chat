package com.vantian.core.communication;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ICommunicate
 */
public interface ICommunicate extends Remote {
    
    public void send(ICommunicate sender, IMessage mssg) throws RemoteException;

    public IMessage receive(ICommunicate sender) throws RemoteException;

    public String getId() throws RemoteException;
    
}
