package gameServer.open.game;

import gameServer.closed.ClosedGameServer;
import gameServer.gameEntities.PlayerDetails;

/**
 * Created by August on 2017-01-02.
 */
public interface IOpenGame {

    PlayerDetails[] getPlayerDetails();

    IOpenGame addPlayer(PlayerDetails playerDetails);

    IOpenGame removePlayer(String playerIP);

    ClosedGameServer start();

    IOpenGame setId(String id);

    String getId();

    int getPort();

    String getIP();

    boolean isFull();
}
