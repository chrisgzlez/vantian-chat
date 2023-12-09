package com.vantian.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;

public interface IDataBaseConnection {

    //Metodo para ejecutar consultas en PSQL
    public ResultSet executeQuery(PreparedStatement statement);

    public PreparedStatement preparedStatement(String query);

    //Metodo para cerrar la conexión de forma segura
    public void closeConnection();
}
