package gameServer.gameEntities;

import hostDetails.Host;

/**
 * Created by August on 2017-01-02.
 */
public class PlayerDetails {

    public Player player = new Player();
    public Host connectionDetails = new Host("0.0.0.0", 0);
}
