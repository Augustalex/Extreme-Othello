package storage.activePlayersManagement;

import boardGameLibrary.players.Player;
import boardGamePlugins.othello.players.GreedyAI;
import communication.ConnectionDetails;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * Offline storage and management of active players.
 *
 * Mostly used in testing purposes.
 */
public class OfflineActivePlayersManager implements IActivePlayersManagement {

    private Map<Player, ConnectionDetails> activePlayers = new HashMap<>();

    public OfflineActivePlayersManager(){
        this.activePlayers.put(
                new GreedyAI("Greedy Bill", Color.DARKMAGENTA),
                new ConnectionDetails("192.168.1.3")
        );
    }

    @Override
    public void addActivePlayer(Player player, ConnectionDetails connectionDetails) {
        this.activePlayers.put(player, connectionDetails);
    }

    @Override
    public void removeActivePlayer(Player player) {
        this.activePlayers.remove(player);
    }

    @Override
    public ConnectionDetails getActivePlayerConnectionDetails(Player player) {
        return this.activePlayers.get(player);
    }

    @Override
    public Player[] getActivePlayers() {
        return this.activePlayers.keySet().parallelStream().toArray(Player[]::new);
    }

    @Override
    public Map<Player, ConnectionDetails> getAllActivePlayerConnectionDetails() {
        return this.activePlayers;
    }
}
