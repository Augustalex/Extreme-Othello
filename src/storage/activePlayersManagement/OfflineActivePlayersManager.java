package storage.activePlayersManagement;

import boardGameLibrary.players.Player;
import boardGamePlugins.othello.players.GreedyAI;
import boardGamePlugins.othello.players.NaturalAI;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Offline storage and management of active players.
 *
 * Mostly used in testing purposes.
 */
public class OfflineActivePlayersManager implements IActivePlayersManagement {

    private List<Player> activePlayers = new ArrayList<>();

    public OfflineActivePlayersManager(){
        this.activePlayers.add(new NaturalAI("Natural", Color.PINK));
        this.activePlayers.add(new GreedyAI("Greedy Bill", Color.DARKMAGENTA));
    }

    @Override
    public void addActivePlayer(Player player) {
        this.activePlayers.add(player);
    }

    @Override
    public void removeActivePlayer(Player player) {
        this.activePlayers.remove(player);
    }

    @Override
    public Player[] getActivePlayers() {
        return this.activePlayers.parallelStream().toArray(Player[]::new);
    }
}
