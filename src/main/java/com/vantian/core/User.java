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
public class User extends UnicastRemoteObject implements IUser {
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
    }

    public void updateFriends(HashMap<String, IUser> friends) throws RemoteException {
        this.loggedFriends = friends;
    }


    public void send(ICommunicate sender, IMessage mssg) throws RemoteException {
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
    public IMessage receive(ICommunicate sender) throws RemoteException {
        Queue<IMessage> q = this.mssgQueue.get(sender.getId());
        if (q == null) {
            return null;
        }
        return this.mssgQueue.get(sender.getId()).poll();
	}

    public String getId() throws RemoteException {
        return this.getUserName();
    } 
}
