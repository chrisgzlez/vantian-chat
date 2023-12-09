package com.vantian.bd;

import java.sql.PreparedStatement;

/**
 * IDBManager
 */
public interface IDBManager {

    public IDataBaseConnection getConnection();

    public PreparedStatement isRegisteredStmt();

    public PreparedStatement isValidCredentials();

    public PreparedStatement createUserStmt();

    public PreparedStatement getAllUsersStmt();
    
}
