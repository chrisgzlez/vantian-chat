package com.vantian.core;
import java.rmi.*;
import java.rmi.server.*;

/**
 * UserManager
 */
public class UserManager extends UnicastRemoteObject implements IUserManager {
    public UserManager() throws RemoteException {
        return;
    }

    public boolean signIn(IUser user) throws RemoteException{
        return true;
    }

    public boolean logIn(IUser user) throws RemoteException{
        return true;
    }

    public boolean isRegistered(IUser user) throws RemoteException{
        return true;
    }

}



