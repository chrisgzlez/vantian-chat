package com.vantian.core;
import java.rmi.*;

/**
 * IUserManager
 * Manejo de Usuarios
 */
public interface IUserManager extends Remote {
    public boolean signIn(IUser user, IPassword passwd) throws RemoteException;
    public boolean logIn(IUser user, IPassword passwd) throws RemoteException;
    public boolean isRegistered(IUser user) throws RemoteException;
}

