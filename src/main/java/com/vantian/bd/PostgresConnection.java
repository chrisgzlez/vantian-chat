package com.vantian.bd;

import java.sql.Connection;
import java.sql.DriverManager;
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
        }
    }
    public PostgresConnection(String connectionUrl, String user, String password) {
        try {
            this.conexion = DriverManager.getConnection(this.protocol + "://" + connectionUrl, user, password);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    //Tipo de consulta a emplear

    public ResultSet executeQuery(String query) {
        try {
            Statement statement = conexion.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            return null;
        }
    }

    // Otros métodos relacionados con la base de datos

    public static void main(String[] args) {
        // Parámetros de conexión

        //URL:jdbc:postgresql://tu_host:tu_puerto/tu_bd
        String url = "jdbc:postgresql://localhost:5432/vantianchat";
        String user = "vantian";
        String password = "vantian";

        // Crear una instancia de ConexionBD
        PostgresConnection conexionBD = new PostgresConnection(url, user, password);

        System.out.println("Conexion a la BD establecida...");

        // Llamar a métodos relacionados con la base de datos
        ResultSet resultadoConsulta = conexionBD.executeQuery("SELECT * FROM tu_tabla");

        // Procesar el resultado o realizar otras operaciones

        // Cerrar la conexión al finalizar
        conexionBD.cerrarConexion();
    }
}

