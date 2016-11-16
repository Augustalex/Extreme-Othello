package storage.activePlayersManagement;

import boardGameLibrary.players.Player;

/**
 * Interface for handling active players.
 */
public interface IActivePlayersManagement {

    void addActivePlayer(Player player);

    void removeActivePlayer(Player player);

    Player[] getActivePlayers();
}
