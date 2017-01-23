package gameServer.closed.message.handler;

import gameServer.BroadcastChannel;
import gameServer.closed.ClosedGame;
import gameServer.message.handler.IMessageHandler;
import gameServer.message.parser.IMessageParser;
import javafx.beans.property.BooleanProperty;

/**
 * Created by August on 2017-01-21.
 */
public abstract class MessageHandler<T extends IMessageParser> implements IMessageHandler<T> {

    private ClosedGame game;

    public MessageHandler(ClosedGame game){
        this.game = game;
    }

    public ClosedGame getGame() {
        return game;
    }
}
