package gameServer.open.message.handler.strategies;

import gameServer.gameEntities.PlayerDetails;
import gameServer.message.handler.IMessageHandler;
import gameServer.open.game.IOpenGame;
import gameServer.open.game.OpenGameDirectory;
import gameServer.message.compiler.MessageCompiler;
import gameServer.open.message.handler.MessageHandler;
import gameServer.open.message.parser.strategies.JoinGameMessageParser;
import javafx.util.Pair;

import java.io.OutputStream;

/**
 * Created by August on 2017-01-21.
 */
public class JoinGameMessageHandler extends MessageHandler<JoinGameMessageParser> {

    public JoinGameMessageHandler(OpenGameDirectory gameDirectory) {
        super(gameDirectory);
    }

    @Override
    public void handle(OutputStream output, JoinGameMessageParser inputParser) {
        Pair<String, PlayerDetails> messageObjects = inputParser.parse();

        String gameId = messageObjects.getKey();
        PlayerDetails player = messageObjects.getValue();

        IOpenGame game = getGameDirectory().getGame(gameId);

        if(game.isFull())
            IMessageHandler.sendMessage(
                    output,
                    MessageCompiler.gameFullMessage()
            );
        else{
            getGameDirectory().joinGame(player, gameId);
            IMessageHandler.sendMessage(
                    output,
                    MessageCompiler.serverDetailsMessage(
                            game.getIP(), game.getPort()
                    )
            );
        }
    }
}
