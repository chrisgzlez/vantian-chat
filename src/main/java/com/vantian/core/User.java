package com.vantian.core;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map.Entry;
import com.vantian.core.communication.*;

/**
 * User
 */
public class User extends UnicastRemoteObject implements IUser, ICommunicate {
    private String userName;
    private HashMap<String, IUser> loggedFriends;
    private HashMap<String,Queue<IMessage>> mssgQueue;
    public User(String name) throws RemoteException {
        super();

        this.userName = name;
        this.loggedFriends = new HashMap<>();
        this.mssgQueue = new HashMap<String, Queue<IMessage>>();
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


    public void sendTo(User dest, IMessage mssg){
        if (dest == null) {
            return;
        }


        return;
	}

    public IMessage receiveFrom(User sender){
        return null;
	}
}
