package com.vantian.core;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * User
 */
public class User extends UnicastRemoteObject implements IUser, ICommunicate {
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
        System.out.println("----Friends (Notifying) of " + this.userName  + "-----");
        for (Entry<String, IUser> e : this.loggedFriends.entrySet()) {
            System.out.println(" - " + e.getKey());
        }
    }

    public void updateFriends(HashMap<String, IUser> friends) throws RemoteException {
        this.loggedFriends = friends;
        System.out.println("----Friends (Updating)" + this.userName  + "-----");
        for (Entry<String, IUser> e : this.loggedFriends.entrySet()) {
            System.out.println(" - " + e.getKey());
        }
    }


    public void sendTo(ICommunicate dest, IMessage mssg){
        return;
	}

    public IMessage receiveFrom(ICommunicate sender){
        return null;
	}
}
