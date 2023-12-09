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


    public void send(ICommunicate sender, IMessage mssg) {
        String senderId = sender.getId();
        Queue<IMessage> q = this.mssgQueue.get(senderId);
        if (q == null) {
            q = new LinkedList<>();
        }
        q.add(mssg);
        this.mssgQueue.put(senderId, q);
        return;
	}

    // Returns first element in queue.
    // It returns only ONE IMessage or Null if queue is empty
    public IMessage receive(ICommunicate sender){
        return this.mssgQueue.get(sender.getId()).poll();
	}

    public String getId() {
        return this.getUserName();
    } 
}
