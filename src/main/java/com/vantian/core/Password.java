package com.vantian.core;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Password
 */
public class Password extends UnicastRemoteObject implements IPassword {
    private String passwd;
    public Password(String plainPassword) throws RemoteException {
        this.passwd = plainPassword;
    }

    public String get() {
        return this.passwd;
    }

    
}
