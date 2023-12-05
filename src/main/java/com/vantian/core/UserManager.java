package com.vantian.core;
import java.rmi.*;
import java.rmi.server.*;

/**
 * UserManager
 */
public class UserManager extends UnicastRemoteObject implements IUserManager {
    public UserManager() throws RemoteException {
        super();
        return;
    }

    public boolean signIn(IUser user) {
        try {
            //TODO: Implemente
            System.out.println("Sign in called by: " + user.getUserName());
            System.out.println("Reference: " + user.toString());
            return true;
            
        } catch (Exception e) {
            System.out.println(" [x] Could not reach user...");
            return false;
        }
    }

    public boolean logIn(IUser user) {
        return true;
    }

    public boolean isRegistered(IUser user) {
        return true;
    }

}



