package boardGameLibrary.playerProfileStore;

import boardGameLibrary.playerProfileStore.exceptions.ProfileNotFoundException;
import boardGameLibrary.players.LocalPlayer;
import boardGameLibrary.players.Player;
import utilities.dataStore.DataStore;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stores Player profiles.
 * Is thread safe as all operations are done by first copying the store.
 *
 * Is not suitable for storing large amounts of data, as operations will
 * be very expansive.
 */
public class PlayerProfileStore extends DataStore<PlayerProfile>{

    /**
     * Copies the store and filters out the matching profiles.
     * Returns the first profile matched to the profile name.
     * @param profileName
     * @return
     */
    @Override
    public PlayerProfile get(String profileName) {
        List<PlayerProfile> result = this.getStoreCopy().parallelStream()
                .filter(profile -> profile.getName().equals(profileName))
                .collect(Collectors.toList());

        if(result.size() <= 0)
            throw new ProfileNotFoundException();
        else
            return result.get(0);
    }

    /**
     * Copies the store and returns all new copied data as an array.
     * @return
     */
    public Player[] toPlayers() {
        return this.getStoreCopy().parallelStream()
                .map(profile -> new LocalPlayer(profile.getName(), profile.getColor()))
                .toArray(Player[]::new);
    }

}
