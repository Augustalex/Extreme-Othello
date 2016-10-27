package databaseManagement;

import boardGameLibrary.players.Player;
import boardGameLibrary.players.RemotePlayer;
import databaseManagement.exceptions.NotConnectedException;
import javafx.scene.paint.Color;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by August on 2016-10-27.
 */
public class ActivePlayersDatabaseManager {

    public final static String connectionUrl = "jdbc:sqlserver://hitsql-db.hb.se:56077;" +
            "databaseName=oomuht1603;user=oomuht1603; password=bagg66";

    public final static String tableName = "ActivePlayers";
    public final static String[] tableColumnNames = {
            "playerName",
            "ipv4",
            "color"
    };

    private DatabaseManager databaseManager;

    public ActivePlayersDatabaseManager() throws SQLException {
        this.databaseManager = new DatabaseManager(ActivePlayersManager.connectionUrl);
        this.createActivePlayersTable();
    }

    public static String toRGBCode(Color color ) {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }

    public void addActivePlayer(Player player, String ipv4) throws SQLException {

        String sqlTableIdentifier = ActivePlayersDatabaseManager.tableName + getColumnNamesTuple();

        String[] values = new String[]{
                player.getName(),
                ipv4,
                ActivePlayersDatabaseManager.toRGBCode(player.getColor())
        };

        this.databaseManager.insertInto(sqlTableIdentifier, values);
        System.out.println("Player Added to the DB..");
    }

    public void removeActivePlayer(Player player) {

    }

    public Player[] getActivePlayers() throws NotConnectedException, SQLException {
        ResultSet resultSet = this.databaseManager.getTableData(ActivePlayersDatabaseManager.tableName);
        ResultSetMetaData metaData = resultSet.getMetaData();

        List<Player> players = new ArrayList<>();

        while(resultSet.next()) {
            List<Object> columns = new ArrayList<>();
            for(int i = 0; i < metaData.getColumnCount(); i++)
                columns.add(resultSet.getObject(0));

            players.add(createPlayerFromRow(columns.stream().toArray(Object[]::new)));
        }

        return players.stream().toArray(Player[]::new);
    }

    private Player createPlayerFromRow(Object[] row){
        if(row.length < 3)
            throw new IllegalArgumentException();
        else
            try {
                return new RemotePlayer((String) row[0], Color.web((String) row[2])).setIP((String) row[1]);
            }
            catch(Exception e){
                System.out.println("Illegal columns in row.");
                e.printStackTrace();
                throw new IllegalArgumentException();
            }
    }

    private void createActivePlayersTable() throws SQLException {
        this.databaseManager.createTable(
                ActivePlayersDatabaseManager.tableName,
                "create table ActivePlayers( \n" +
                "playerName varchar(40) not null Primary key,\n" +
                "ipv4 varchar(30) NOT NULL,\n" +
                "color varchar(30) NOT NULL,\n" +
                ")"
        );

        System.out.println("Database Tables have been created");
    }

    private String getColumnNamesTuple(){
        String tuple = "(";
        for(String columnName : ActivePlayersDatabaseManager.tableColumnNames)
            tuple += columnName;
        tuple += ")";

        return tuple;
    }

}
