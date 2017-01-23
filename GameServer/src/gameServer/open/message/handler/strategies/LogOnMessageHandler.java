package gameServer.open.message.handler.strategies;

import gameServer.gameEntities.PlayerDetails;
import gameServer.message.handler.IMessageHandler;
import gameServer.open.game.OpenGameDirectory;
import gameServer.message.compiler.MessageCompiler;
import gameServer.open.message.handler.MessageHandler;
import gameServer.open.message.parser.strategies.LogOnMessageParser;
import javafx.util.Pair;

import java.io.OutputStream;

/**
 * Created by August on 2017-01-21.
 */
public class LogOnMessageHandler extends MessageHandler<LogOnMessageParser> {

    public LogOnMessageHandler(OpenGameDirectory gameDirectory) {
        super(gameDirectory);
    }

    private void sendOnlineList(OutputStream outputStream){
        System.out.println("Now sending online players list.");
        IMessageHandler.sendMessage(
                outputStream,
                MessageCompiler.onlinePlayersList(
                        getGameDirectory().getAllPlayersOnline()
                ));
    }

    @Override
    public void handle(OutputStream output, LogOnMessageParser parser) {
        System.out.println("LogOnMessageHandler is handling message.");
        Pair<PlayerDetails, Integer> logOnDetails = parser.parse();
        System.out.println("Shit is parsed.");
        int playerSpots = logOnDetails.getValue();
        PlayerDetails player = logOnDetails.getKey();
        System.out.println("Here.");
        getGameDirectory().logOnPlayer(player);
        System.out.println("Player logged in.");
        getGameDirectory().registerGame(player, playerSpots);
        System.out.println( "New game is registered.");

        sendOnlineList(output);
    }
}
