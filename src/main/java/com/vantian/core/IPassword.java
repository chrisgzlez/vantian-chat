package com.vantian.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * IPassword
 */
public interface IPassword extends Remote {

    public String get() throws RemoteException;
}
