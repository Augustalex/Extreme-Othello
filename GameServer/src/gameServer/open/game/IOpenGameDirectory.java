package gameServer.open.game;

import gameServer.gameEntities.PlayerDetails;

import java.util.List;

/**
 * Created by August on 2017-01-20.
 */
public interface IOpenGameDirectory {

    IOpenGameDirectory joinGame(PlayerDetails player, String gameId);

    IOpenGame getGame(String gameId);

    IOpenGameDirectory logOnPlayer(PlayerDetails playerDetails);

    IOpenGameDirectory registerGame(PlayerDetails gameOwner, int playerSpots);

    List<PlayerDetails> getAllPlayersOnline();

}
