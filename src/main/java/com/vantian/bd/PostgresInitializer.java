package com.vantian.bd;
/**
 * PostgresInitializer
 */
public class PostgresInitializer {
    private String database;
    private boolean intialized = false;

    public PostgresInitializer(String database) {
        this.database = database;
        this.init();
    }

    public boolean hasBeenInitialized() {
        return this.intialized;
    }

    //TODO: Implement Initialization
    // Create Database if not exists
    // Create Tables if not exists
    public void init() {
        return;
    }
}
