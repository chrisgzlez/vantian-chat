package com.vantian.bd;

import java.sql.ResultSet;

public interface IDataBaseConnection {

    //Metodo para ejecutar consultas en PSQL
    ResultSet executeQuery(String consulta);

    //Metodo para cerrar la conexión de forma segura
    void cerrarConexion();
}
