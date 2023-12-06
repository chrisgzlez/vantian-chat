package com.vantian.bd;
/**
 * PostgresInitializer
 */
public class PostgresInitializer {
    private String database;
    private boolean intialized;

    public PostgresInitializer(String database) {
        this.database = database;
    }
}
