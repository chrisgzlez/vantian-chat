package com.vantian.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class PostgresConnection implements IDataBaseConnection {

    private Connection conexion;
    private final String protocol = "jdbc:postgresql";

    public PostgresConnection(String host, Integer port, String database, String user, String password) {
        try {
            String connectionString = this.protocol + "://" + host + ":" + port + "/" + database;
            this.conexion = DriverManager.getConnection(connectionString, user, password);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            System.exit(1);
        }
    }
    public PostgresConnection(String connectionUrl, String user, String password) {
        try {
            this.conexion = DriverManager.getConnection(this.protocol + "://" + connectionUrl, user, password);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            System.exit(1);
        }
    }

    public void closeConnection() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public PreparedStatement preparedStatement(String query) {
        try {
            return this.conexion.prepareStatement(query);
        } catch (SQLException e) {
            System.err.println("Exception on prepare statment: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }
    //Tipo de consulta a emplear

    public ResultSet executeQuery(PreparedStatement statement) {
        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            return null;
        }
    }
}

