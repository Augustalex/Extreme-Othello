package gameServer.closed.message.handler.strategies;

import gameServer.BroadcastChannel;
import gameServer.closed.ClosedGame;
import gameServer.closed.message.handler.MessageHandler;
import gameServer.closed.message.parser.strategies.StopMessageParser;
import gameServer.message.parser.IMessageParser;
import javafx.beans.property.BooleanProperty;

import java.io.OutputStream;

/**
 * Created by August on 2017-01-21.
 */
public class StopGameMessageHandler extends MessageHandler<StopMessageParser>{

    public StopGameMessageHandler(ClosedGame game) {
        super(game);
    }

    @Override
    public void handle(OutputStream output, StopMessageParser parser) {
        getGame().gameOnProperty().set(false);
    }
}
