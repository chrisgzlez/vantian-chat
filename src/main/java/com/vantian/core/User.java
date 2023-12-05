package com.vantian.core;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * User
 */
public class User extends UnicastRemoteObject implements IUser {
    private String userName;
    public User(String name) throws RemoteException {
        super();

        this.userName = name;
        return;
    }

    public String getUserName() {
        return this.userName;
    } 
}
