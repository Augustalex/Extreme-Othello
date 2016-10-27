package databaseManagement;

import boardGameLibrary.players.Player;

/**
 * Created by August on 2016-10-27.
 */
public abstract class ActivePlayersManager {

    public final static String connectionUrl = "jdbc:sqlserver://hitsql-db.hb.se:56077;" +
            "databaseName=oomuht1603;user=oomuht1603; password=bagg66";

    private DatabaseManager databaseManager;

    public ActivePlayersManager(){
        this.databaseManager = new DatabaseManager(ActivePlayersManager.connectionUrl);
    }

    public void addActivePlayer(Player player) {

    }

    public void removeActivePlayer(Player player) {
        String values ="\'" + playerName + "\'";
        statement.addBatch("Delete FROM ActivePlayers where playerName like " + values);
        statement.executeBatch();
        System.out.println("Player Removed from the DB..");
    }

    public Player[] getActivePlayers(){

    }

}
