package com.vantian.core;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map.Entry;

import com.vantian.bd.IDBManager;

/**
 * UserManager
 */
public class UserManager extends UnicastRemoteObject implements IUserManager {
    private IDBManager dbManager;
    private HashMap<String, IUser> loggedUsers;
    public UserManager(IDBManager dbManager) throws RemoteException {
        super();
        this.dbManager = dbManager;
        this.loggedUsers = new HashMap<>();
        return;
    }

    public boolean signIn(IUser user, IPassword passwd) {
        try {
            if(this.isRegistered(user)) {
                System.out.println(" [v] Client " + user.getUserName() + " already signed in");
                return true;
            }

            PreparedStatement statement = this.dbManager.createUserStmt();
            statement.setString(1, user.getUserName());
            statement.setString(2, passwd.get());
            statement.setTimestamp(3, Timestamp.from(Instant.now()));
            statement.setTimestamp(4, Timestamp.from(Instant.now()));
            if (statement.executeUpdate() == 1) {
                System.out.println(" [v] Client " + user.getUserName() + " succesfully signed in");
                return true;
            }
            return false;
            
        } catch (Exception e) {
            System.out.println(" [x] Could not reach user to register it... " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        }
    }

    public boolean logIn(IUser user, IPassword passwd) {
        try {
            if(!this.isRegistered(user)){
                System.err.println(" [v] Client " + user.getUserName() + " already signed in");
                return false;
            }
            String username = user.getUserName();
            this.loggedUsers.put(username, user);
            user.updateFriends(this.loggedUsers);

            for (Entry<String,IUser> entry : this.loggedUsers.entrySet()) {
                String loggedUserName =  entry.getKey();
                if (loggedUserName != username) {
                    entry.getValue().notifyLogin(user);
                }
                
            }

            return true;
        } catch (Exception e) {
            System.out.println(" [x] Could not reach user to register it... " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        }
    }

    public boolean isRegistered(IUser user) {
        PreparedStatement statement = this.dbManager.isRegisteredStmt();
        try {
            statement.setString(1, user.getUserName());
            ResultSet rs = statement.executeQuery();

            // If registered, returns true else false
            return rs.next();
        } catch (Exception e) {
            System.out.println(" [x] Could not reach user to check if its registered... " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        }
    }


}



