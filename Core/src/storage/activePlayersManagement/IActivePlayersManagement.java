package storage.activePlayersManagement;

import boardGameLibrary.players.Player;
import communication.ConnectionDetails;

import java.util.Map;

/**
 * Interface for handling active players.
 */
public interface IActivePlayersManagement {

    void addActivePlayer(Player player, ConnectionDetails connectionDetails);

    void removeActivePlayer(Player player);

    ConnectionDetails getActivePlayerConnectionDetails(Player player);

    Player[] getActivePlayers();

    Map<Player, ConnectionDetails> getAllActivePlayerConnectionDetails();
}
