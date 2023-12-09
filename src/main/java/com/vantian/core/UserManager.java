package com.vantian.core;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
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
                System.err.println(" [x] Client " + user.getUserName() + " not signed in");
                return false;
            }
            if(!this.validateCredentials(user, passwd)) {
                System.err.println(" [x] Client " + user.getUserName() + " wrong password");
                return false;
            }
            String username = user.getUserName();

            user.updateFriends(this.loggedUsers);
            this.loggedUsers.put(username, user);

            for (Entry<String,IUser> entry : this.loggedUsers.entrySet()) {
                String loggedUserName =  entry.getKey();
                if (loggedUserName != username) {
                    entry.getValue().notifyLogin(user);
                }
                
            }

            System.out.println(" [v] Client " + user.getUserName() + " succesfully logged in and notified");
            System.out.println("----Connected Clients-----");
            for (Entry<String, IUser> e : this.loggedUsers.entrySet()) {
                System.out.println(" - " + e.getKey());
            }
            System.out.println("--------------------------");
            return true;
        } catch (Exception e) {
            System.out.println(" [x] Could not reach user to register it... " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        }
    }

    public boolean logOut(IUser user, IPassword passwd) throws RemoteException {
        boolean success = this.loggedUsers.remove(user.getUserName()) != null;
        if (!success) {
            return false;
        }

        System.out.println(" [v] Client " + user.getUserName() + " succesfully logged out and notified");
        for (Entry<String,IUser> entry : user.getFriends().entrySet()) {
            entry.getValue().notifyLogout(user);
        }

        return true;
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

    public boolean validateCredentials(IUser user, IPassword passwd) {
        PreparedStatement statement = this.dbManager.isValidCredentials();
        try {
            statement.setString(1, user.getUserName());
            statement.setString(2, passwd.get());
            ResultSet rs = statement.executeQuery();

            // If valid credentials, returns true else false
            return rs.next();
        } catch (Exception e) {
            System.out.println(" [x] Could not reach user to check if its registered... " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        }

    }

    public Set<String> getUsersRegistered() throws RemoteException {
        Set<String> users = new HashSet<>();
        PreparedStatement stmt = this.dbManager.getAllUsersStmt();
        try {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                users.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(" [x] Could not reach user to check if its registered... " + e.getMessage());
            e.printStackTrace(System.err);
            return null;
        }
        return users;
    }


}



