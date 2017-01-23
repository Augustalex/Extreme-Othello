package gameServer.open.message.handler.strategies;

import gameServer.gameEntities.PlayerDetails;
import gameServer.open.game.OpenGameDirectory;
import gameServer.open.message.handler.MessageHandler;
import gameServer.open.message.parser.strategies.LeaveGameMessageParser;
import javafx.util.Pair;

import java.io.OutputStream;

/**
 * Created by August on 2017-01-21.
 */
public class LeaveGameMessageHandler extends MessageHandler<LeaveGameMessageParser> {

    public LeaveGameMessageHandler(OpenGameDirectory gameDirectory) {
        super(gameDirectory);
    }

    @Override
    public void handle(OutputStream output, LeaveGameMessageParser parser) {
        Pair<String, PlayerDetails> leaveGameDetails = parser.parse();
        getGameDirectory()
                .getGame(leaveGameDetails.getKey())
                .removePlayer(leaveGameDetails.getValue().connectionDetails.address);
    }
}
