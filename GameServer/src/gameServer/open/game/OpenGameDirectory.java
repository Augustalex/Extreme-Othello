package gameServer.open.game;

import gameServer.gameEntities.PlayerDetails;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Data model for OpenGameServer. Here all open games are stored as well as a list
 * of players that are online.
 */
public class OpenGameDirectory implements IOpenGameDirectory{

    private final Object key = new Object();

    private Map<String, IOpenGame> openGames = new HashMap<>();
    private Map<String, PlayerDetails> playersOnline = new HashMap<>();

    @Override
    public IOpenGameDirectory joinGame(PlayerDetails player, String gameId) {
        openGames.get(gameId).addPlayer(player);
        return this;
    }

    @Override
    public IOpenGame getGame(String gameId){
        return openGames.get(gameId);
    }

    @Override
    public OpenGameDirectory logOnPlayer(PlayerDetails playerDetails){
        synchronized (this.key) {
            System.out.println("ADDING ONLINE PLAYER AT " + playerDetails.connectionDetails.address+playerDetails.connectionDetails.port);
            playersOnline.put(playerDetails.connectionDetails.address+":"+playerDetails.connectionDetails.port, playerDetails);
            return this;
        }
    }

    @Override
    public IOpenGameDirectory registerGame(PlayerDetails gameOwner, int playerSpots) {
        openGames.put(
                gameOwner.connectionDetails.address+":"+gameOwner.connectionDetails.port,
                new OpenGame(playerSpots).addPlayer(gameOwner)
        );

        return this;
    }

    @Override
    public List<PlayerDetails> getAllPlayersOnline(){
        try {
            return Arrays.stream(
                    playersOnline.values().stream().toArray(PlayerDetails[]::new)
            ).collect(Collectors.toList());
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
