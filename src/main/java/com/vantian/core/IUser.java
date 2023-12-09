package com.vantian.core;

import java.rmi.RemoteException;
import java.util.HashMap;

import com.vantian.core.communication.ICommunicate;

/**
 * IUser
 */
public interface IUser extends ICommunicate {
    
    public String getUserName() throws RemoteException; 
    public void notifyLogin(IUser user) throws RemoteException;
    public void notifyLogout(IUser user) throws RemoteException;
    public void updateFriends(HashMap<String, IUser> friends) throws RemoteException;
    public HashMap<String, IUser> getFriends() throws RemoteException;
    
}
