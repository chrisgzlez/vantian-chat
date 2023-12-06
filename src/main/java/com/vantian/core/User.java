package com.vantian.core;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * User
 */
public class User extends UnicastRemoteObject implements IUser {
    private String userName;
    private HashMap<String, IUser> loggedFriends;
    public User(String name) throws RemoteException {
        super();

        this.userName = name;
        this.loggedFriends = new HashMap<>();
        return;
    }

    public String getUserName() {
        return this.userName;
    } 

    public void notifyLogin(IUser user) throws RemoteException {
        this.loggedFriends.put(user.getUserName(), user);
    }

    public void updateFriends(HashMap<String, IUser> friends) throws RemoteException {
        this.loggedFriends = friends;
    }
}
