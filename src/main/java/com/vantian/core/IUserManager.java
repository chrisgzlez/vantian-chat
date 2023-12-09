package com.vantian.core;
import java.rmi.*;
import java.util.Set;

/**
 * IUserManager
 * Manejo de Usuarios
 */
public interface IUserManager extends Remote {
    public boolean signIn(IUser user, IPassword passwd) throws RemoteException;
    public boolean logIn(IUser user, IPassword passwd) throws RemoteException;
    public boolean logOut(IUser user, IPassword passwd) throws RemoteException;
    public boolean isRegistered(IUser user) throws RemoteException;
    public Set<String> getUsersRegistered() throws RemoteException;
    public void sendFriendRequest(String requester, String requested) throws RemoteException;
    public void acceptFriendRequest(String accepter, String requester) throws RemoteException;
    public void declineFriendRequest(String accepter, String requester) throws RemoteException;
}

