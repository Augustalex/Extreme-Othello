package gameServer.closed.message.handler.strategies;

import gameServer.closed.ClosedGame;
import gameServer.closed.message.handler.MessageHandler;
import gameServer.closed.message.parser.strategies.MoveMessageParser;
import gameServer.message.compiler.MessageCompiler;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by August on 2017-01-21.
 */
public class MoveMessageHandler extends MessageHandler<MoveMessageParser> {

    public MoveMessageHandler(ClosedGame game) {
        super(game);
    }

    @Override
    public void handle(OutputStream output, MoveMessageParser parser) {
        int[][] actions = parser.parse().actions;

        List<String> outputMessage = MessageCompiler.moveMessage(actions);
        for(String messageBit : outputMessage)
            getGame().getBroadcastChannel().exclusiveBroadcast(messageBit, output);

    }
}
