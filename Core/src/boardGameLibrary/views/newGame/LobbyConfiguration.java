package boardGameLibrary.views.newGame;

import boardGameLibrary.players.Player;
import hostDetails.Host;

/**
 * When the local host has picked an amount
 * of open player spots this configuration will
 * be setup so that if the host decides not to
 * join another hosts game, it can be used to establish
 * the local game in which other players will join.
 */
public class LobbyConfiguration {

    private final int numberOfPlayers;
    private final Host connectionDetails;
    private final Player hostPlayer;

    public LobbyConfiguration(Host connectionDetails, Player hostPlayer, int numberOfPlayers){
        this.connectionDetails = connectionDetails;
        this.hostPlayer = hostPlayer;
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Host getConnectionDetails() {
        return connectionDetails;
    }

    public Player getHostPlayer() {
        return hostPlayer;
    }
}
