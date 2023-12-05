package com.vantian.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * IUser
 */
public interface IUser extends Remote {
    
    public String getUserName() throws RemoteException; 
    
}
