package com.vantian.core;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.time.Instant;

import com.vantian.bd.IDBManager;
import com.vantian.bd.IDataBaseConnection;

/**
 * UserManager
 */
public class UserManager extends UnicastRemoteObject implements IUserManager {
    private IDBManager dbManager;
    public UserManager(IDBManager dbManager) throws RemoteException {
        super();
        this.dbManager = dbManager;
        return;
    }

    public boolean signIn(IUser user, IPassword passwd) {
        try {
            System.out.println("Sign in called by: " + user.getUserName() + " password: " + passwd.get());
            System.out.println("Reference: " + user.toString());
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
        if(!this.isRegistered(user)) {
            return false;
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


}



