package gameServer.closed;

import gameServer.BroadcastChannel;
import gameServer.gameEntities.PlayerDetails;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by August on 2017-01-21.
 */
public class ClosedGame {

    private final List<PlayerDetails> playersDetails;
    private final BroadcastChannel broadcastChannel;

    private final BooleanProperty gameOn = new SimpleBooleanProperty(true);

    public ClosedGame(List<PlayerDetails> playerDetailsList, OutputStream[] playerConnections){
        this.playersDetails = playerDetailsList;
        this.broadcastChannel = new BroadcastChannel().addOutputs(playerConnections);
    }

    public boolean isGameOn() {
        return gameOn.get();
    }

    public BooleanProperty gameOnProperty() {
        return gameOn;
    }

    public BroadcastChannel getBroadcastChannel(){
        return this.broadcastChannel;
    }
}
