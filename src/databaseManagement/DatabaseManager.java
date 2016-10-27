/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseManagement;

import databaseManagement.exceptions.NotConnectedException;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author S132063
 */
public class DatabaseManager  {

    private TableManager tableManager;
    private SQLConnection sqlConnection;

    // Declare the JDBC objects.
    //DatabaseMetaData dbMetaData = null;

    public DatabaseManager(String connectionUrl){
        try {
            this.sqlConnection = new SQLConnection(connectionUrl);
            this.tableManager = new TableManager(this.sqlConnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SQLConnection getSqlConnection(){
        return this.sqlConnection;
    }
    
    public ResultSet getTableData(String tableName) throws SQLException, NotConnectedException {
        return this.sqlConnection
                .connect()
                .ifConnected()
                .getStatement()
                .executeQuery("select * from " + tableName);
    }

    public void runStatement(String sqlStatement) throws SQLException {
        Statement statement = this.sqlConnection.getStatement();

        statement.addBatch(sqlStatement);
        statement.executeBatch();
    }

    public void createTable(String tableName, String columns) throws SQLException {
        this.tableManager.createTable(tableName, columns);
    }

    public void insertInto(String sqlTableIdentifier, String[] values) throws SQLException {
        Statement statement = this.sqlConnection.getStatement();

        statement.addBatch(
                "insert into " + sqlTableIdentifier + "\n"
                + this.createSQLValueStringFromArray(values)
        );

        statement.executeBatch();
    }

    public void printResultset(ResultSet resultSet) throws SQLException{
        while (resultSet.next()) {
            System.out.println(" PlayerName: " + resultSet.getString(1)+ " IPV4: "+resultSet.getString(2)+ " Color: " + resultSet.getString(3));
        }
    }

    private String createSQLValueStringFromArray(String[] values){
        String valuesString = "values(\'";
        for(String value : values)
            valuesString += value + ", ";
        valuesString += "\')";

        return valuesString;
    }
}
