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
    
    public final static String connectionUrl = "jdbc:sqlserver://hitsql-db.hb.se:56077;" +
			"databaseName=oomuht1603;user=oomuht1603; password=bagg66";

    private TableManager tableManager;
    private SQLConnection sqlConnection;

    // Declare the JDBC objects.
    //DatabaseMetaData dbMetaData = null;
    public DatabaseManager(){
        try {
            this.sqlConnection = new SQLConnection(DatabaseManager.connectionUrl);
            this.tableManager = new TableManager(this.sqlConnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void initateDatabas() throws SQLException {
        // Establish the connection.
        if(!this.tableManager.doesTableExist("ActivePlayers"))
            createTables();

    }
    
    public ResultSet getPlayerData() throws SQLException, NotConnectedException {
        return this.sqlConnection
                .connect()
                .ifConnected()
                .getStatement()
                .executeQuery("select * from ActivePlayers");
    }

    
    public void insertPlayer(String playerName, String ipv4, String color) throws SQLException{
        //Den raden under här hade i strängen sånna enkel quotes -> '  i början och slutet.. Tog bort dem!
        String values = "values(" +playerName + "','"+ ipv4 +"','"+ color +")";

        Statement statement = this.sqlConnection.getStatement();
        statement.addBatch("insert into ActivePlayers(playerID,playerName, ipv4, color)\n" + values);
        statement.executeBatch();

        System.out.println("Player Added to the DB..");
    }
    
    private void createTables() throws SQLException{
        this.tableManager.createTable("ActivePlayers");
    }

    public void printResultset(ResultSet resultSet) throws SQLException{
        while (resultSet.next()) {
            System.out.println("Player ID: " + resultSet.getString(1) + " PlayerName: " + resultSet.getString(2)+ " IPV4: "+resultSet.getString(3)+ " Color: " + resultSet.getString(4));
        }
    }
}
