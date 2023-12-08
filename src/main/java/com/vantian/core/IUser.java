package com.vantian.core;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * IUser
 */
public interface IUser extends Remote {
    
    public String getUserName() throws RemoteException; 
    public void notifyLogin(IUser user) throws RemoteException;
    public void updateFriends(HashMap<String, IUser> friends) throws RemoteException;
    
}
