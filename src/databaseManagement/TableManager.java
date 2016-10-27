package databaseManagement;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by August on 2016-10-27.
 */
public class TableManager {

    private SQLConnection sql;
    private ResultSet resultSet = null;

    public TableManager(SQLConnection sqlConnection) {
        this.sql = sqlConnection;
    }

    public boolean doesTableExist(String tableName) throws SQLException {
        connectIfNotConnected();

        this.resultSet = this.sql.getConnection()
                .getMetaData()
                .getTables("oomuht1603", "dbo", tableName, new String[]{"TABLE"});

        return this.resultSet.next();
    }

    public void createActivePlayersTables() throws SQLException {

        Statement statement = this.sql.getStatement();

        statement.addBatch(
                "create table ActivePlayers( \n" +
                "playerName varchar(40) not null Primary key,\n" +
                "ipv4 varchar(30) NOT NULL,\n" +
                "color varchar(30) NOT NULL,\n" +
                ")"
        );

        statement.executeBatch();

        System.out.println("Database Tabels have been created");
    }

    public void close(){
        try {
            if (this.resultSet != null)
                this.resultSet.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void connectIfNotConnected() throws SQLException {
        if(!this.sql.isConnected)
            this.sql.connect();
    }

    /*

    public boolean doesTableExist(String tableName) throws SQLException{
        ResultSet resultset = null;
        DatabaseMetaData dbMetaData = this.sql.getConnection().getMetaData();
        resultset = dbMetaData.getTables("oomuht1603", "dbo", "%", new String[] {"TABLE"});
        while (resultset.next()) {
            if( resultset.getString(3).equals(tableName)){
                return true;
            }
        }
        return false;
    }
     */
}
