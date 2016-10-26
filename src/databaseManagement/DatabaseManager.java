/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseManagement;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author S132063
 */
public class DatabaseManager  {
    
    String connectionUrl = "jdbc:sqlserver://hitsql-db.hb.se:56077;" +
			"databaseName=oomuht1603;user=oomuht1603; password=bagg66";
    // Declare the JDBC objects.
    //DatabaseMetaData dbMetaData = null;
   
    
    
    public void initateDatabas(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        try {
            // Establish the connection.
            connection = getConnection();
            if(!doesTableExist(connection, "ActivePlayers")){
                System.out.println("FAN!");
                createTables(connection);
                //insertPlayer(1,"skynet","192.168.20.1:22222", "hej");     
            }               
        }     
        catch (Exception e) {
            //e.printStackTrace();
        }

        finally {
            closeConnection(connection,statement,resultset);
        }
    }
    
    public void closeConnection(Connection connected, Statement statement, ResultSet resultset ){
            if (resultset != null) try { resultset.close(); } catch(Exception e) {}
            if (statement != null) try { statement.close(); } catch(Exception e) {}
            if (connected != null) try { connected.close(); } catch(Exception e) {}
        
    }
    private Connection getConnection() throws SQLException{
            
            Connection connected = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to DB");
            return connected;
    } 
    
    public ResultSet getPlayerData() throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        connection = getConnection();
        statement = connection.createStatement();
        String SQL = "select * from ActivePlayers";
        resultset = statement.executeQuery(SQL);
        //closeConnection(connection,statement, resultset);

        return resultset;
        
    }
    
    private boolean doesTableExist(Connection connection, String TableName ) throws SQLException{
        ResultSet resultset = null;
        DatabaseMetaData dbMetaData = connection.getMetaData();
        resultset = dbMetaData.getTables("oomuht1603", "dbo", "%", new String[] {"TABLE"});
            while (resultset.next()) {
                //System.out.println(rs.getString(3));
                if( resultset.getString(3).equals(TableName)){
                    return true;
                }
            }
        return false;
    }
    public void printResult(ResultSet rs) throws SQLException{
        
        while (rs.next()) {
               System.out.println("Player ID: " + rs.getString(1) + " PlayerName: " + rs.getString(2)+ " IPV4: "+rs.getString(3)+ " Color: " + rs.getString(4));
            }
    }
    
    public void insertPlayer(int playerID, String playerName, String ipv4, String color) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        connection = getConnection();
        System.out.println("fan fan fan");
        statement = connection.createStatement();
        String values = "values(" + playerID +",'" +playerName + "','"+ ipv4 +"','"+ color +"')";
        
        statement.addBatch("insert into ActivePlayers(playerID,playerName, ipv4, color)\n" + values);
        statement.executeBatch();
        System.out.println("Player Added to the DB..");
        closeConnection(connection,statement, resultset);
    }
    
    private void createTables(Connection connection) throws SQLException{
        Statement statement = null;
        statement = connection.createStatement();
        statement.addBatch("create table ActivePlayers(\n" +
                      "playerID int Primary key,\n" +
                      "playerName varchar(40) not null unique,\n" +
                      "ipv4 varchar(30) NOT NULL,\n" +
                      "color varchar(30) NOT NULL, \n" +
                      ")"
                        );
        statement.executeBatch();
        System.out.println("Database Tabels have been created");
    }
    
}
