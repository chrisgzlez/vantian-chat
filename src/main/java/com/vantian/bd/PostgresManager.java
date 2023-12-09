package com.vantian.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.directory.InvalidAttributesException;

/**
 * PostgresManager
 */
public class PostgresManager implements IDBManager {
    private PostgresConnection conn;
    private String connectionUrl, dbUser, dbPasswd;
    private Integer dbPort;

    public PostgresManager(String host, Integer port, String database, String user, String password)
        throws InvalidAttributesException {
        if (host == null) {
            throw new InvalidAttributesException("Not valid hostname for db connection");
        }
        if (database == null) {
            throw new InvalidAttributesException("Not valid databasename for db connection");
        }
        if (port == null) {
            throw new InvalidAttributesException("Not valid portname for db connection");
        }

        this.connectionUrl = host + ":" + port + "/" + database;

        if (user == null) {
            throw new InvalidAttributesException("Not valid username for db connection");
        }
        this.dbUser = user;
        this.dbPasswd = password;

        this.conn = new PostgresConnection(this.connectionUrl, this.dbUser, this.dbPasswd);
    }

    public PostgresConnection getConnection() {
        return this.conn;
    }

    public PreparedStatement isRegisteredStmt() {
        return this.conn.preparedStatement(
            "SELECT 1 FROM users "
            + "WHERE username = ?"
        );
    }

    public PreparedStatement isValidCredentials() {
        return this.conn.preparedStatement(
            "SELECT 1 FROM users "
            + "WHERE username = ? "
            + "AND password = ?"
        );
    }

    public PreparedStatement createUserStmt() {
        return this.conn.preparedStatement(
            "INSERT INTO users " + 
            "VALUES (?, ?, ?, ?)"
        );
    }
    
    public PreparedStatement getAllUsersStmt() {
        return this.conn.preparedStatement(
            "SELECT username FROM users"
        );
    }

    public PreparedStatement getFriends() {
        return this.conn.preparedStatement(
                "SELECT user_requester as u1, user_accepter as u2 " 
                + "FROM requests WHERE "
                + "(user_requester = ? and request_status = 'Active') "
                + "or "
                + "(user_accepter = ? and request_status = 'Active') "
        );

	}
    public PreparedStatement requestFriend() {
        return this.conn.preparedStatement(
            "INSERT INTO requests " + 
            "VALUES (?, ?, 'Pending', ?, ?)"
        );

	}
    public PreparedStatement acceptFriendRequest() {
        return this.conn.preparedStatement(
            "UPDATE requests " + 
            "SET request_status = 'Active' " +
            "WHERE user_accepter = ? and user_requester = ?"
        );

	}
    public PreparedStatement declineFriendRequest() {

	}

}
