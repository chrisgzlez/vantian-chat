package com.vantian.bd;

import java.sql.Connection;

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
        if (password == null) {
            throw new InvalidAttributesException("Not valid passwordname for db connection");
        }
        this.dbPasswd = password;

        this.conn = new PostgresConnection(this.connectionUrl, this.dbUser, this.dbPasswd);
    }

    public PostgresConnection getConnection() {
        return this.conn;
    }
}
